package insper.store.tournamentmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tournaments") // Define o caminho base para este controlador
public class TournamentManagerResource {

    @Autowired
    private TournamentManagerService tournamentService;

    // Endpoint para criar um novo torneio
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<TournamentManagerOut> createTournament(@RequestBody TournamentManagerIn tournamentIn) {
        TournamentManagerModel tournamentModel = tournamentService.createTournament(tournamentIn);
        TournamentManagerOut tournamentOut = TournamentManagerParser.toOut(tournamentModel);
        return ResponseEntity.ok(tournamentOut);
    }

    // Endpoint para obter detalhes de um torneio específico
    @GetMapping("/{tournamentId}")
    public ResponseEntity<TournamentManagerOut> getTournament(@PathVariable String tournamentId) {
        TournamentManagerModel tournament = tournamentService.getTournament(tournamentId);
        if (tournament == null) {
            return ResponseEntity.notFound().build();
        }
        TournamentManagerOut tournamentOut = TournamentManagerParser.toOut(tournament);
        return ResponseEntity.ok(tournamentOut);
    }

    // Endpoint para listar todos os torneios
    @GetMapping
    public ResponseEntity<List<TournamentManagerOut>> listTournaments() {
        List<TournamentManagerModel> tournaments = tournamentService.listAllTournaments();
        List<TournamentManagerOut> tournamentOuts = tournaments.stream()
                                                               .map(TournamentManagerParser::toOut)
                                                               .collect(Collectors.toList());
        return ResponseEntity.ok(tournamentOuts);
    }

    // Endpoint para atualizar um torneio existente
    @PutMapping("/{tournamentId}")
    public ResponseEntity<TournamentManagerOut> updateTournament(@PathVariable String tournamentId, @RequestBody TournamentManagerIn tournamentIn) {
        TournamentManagerModel tournamentModel = tournamentService.updateTournament(tournamentId, tournamentIn);
        if (tournamentModel == null) {
            return ResponseEntity.notFound().build();
        }
        TournamentManagerOut tournamentOut = TournamentManagerParser.toOut(tournamentModel);
        return ResponseEntity.ok(tournamentOut);
    }

    // Endpoint para deletar um torneio
    @DeleteMapping("/{tournamentId}")
    public ResponseEntity<Void> deleteTournament(@PathVariable String tournamentId) {
        boolean isDeleted = tournamentService.deleteTournament(tournamentId);
        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{tournamentId}/players")
    public ResponseEntity<Void> addPlayerToTournament(
        @PathVariable String tournamentId,
        @RequestBody PlayerIn playerIn) {

        if (!tournamentService.checkPlayerExists(playerIn.playerId())) {
            return ResponseEntity.badRequest().build();  // ou outra resposta apropriada
        }
        
        boolean added = tournamentService.addPlayerToTournament(tournamentId, playerIn.playerId());
        if (!added) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

}