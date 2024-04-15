package insper.store.tournamentmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;



@Service
public class TournamentManagerService {

    @Autowired
    private TournamentManagerRepository tournamentRepository;

    @Autowired
    private TournamentManagerClient accountClient;      

    // Criar um novo torneio
    @Transactional
    public TournamentManagerModel createTournament(TournamentManagerIn tournamentIn) {
        TournamentManagerModel tournament = TournamentManagerParser.toModel(tournamentIn);
        return tournamentRepository.save(tournament);
    }

    // Obter um torneio pelo ID
    @Transactional(readOnly = true)
    public TournamentManagerModel getTournament(String tournamentId) {
        return tournamentRepository.findById(tournamentId).orElse(null);
    }

    // Listar todos os torneios
    @Transactional(readOnly = true)
    public List<TournamentManagerModel> listAllTournaments() {
        return (List<TournamentManagerModel>) tournamentRepository.findAll();
    }

    // Atualizar um torneio existente
    @Transactional
    public TournamentManagerModel updateTournament(String tournamentId, TournamentManagerIn tournamentIn) {
        Optional<TournamentManagerModel> existingTournament = tournamentRepository.findById(tournamentId);
        if (existingTournament.isPresent()) {
            TournamentManagerModel updatedTournament = existingTournament.get()
                    .name(tournamentIn.name())
                    .startTime(tournamentIn.startTime())
                    .description(tournamentIn.description());
            return tournamentRepository.save(updatedTournament);
        }
        return null;
    }

    // Deletar um torneio
    @Transactional
    public boolean deleteTournament(String tournamentId) {
        if (tournamentRepository.existsById(tournamentId)) {
            tournamentRepository.deleteById(tournamentId);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean addPlayerToTournament(String tournamentId, String playerId) {
        Optional<TournamentManagerModel> tournamentOpt = tournamentRepository.findById(tournamentId);
        if (tournamentOpt.isPresent()) {
            TournamentManagerModel tournament = tournamentOpt.get();
            // Adicione o jogador ao torneio, assumindo que você tem uma coleção de IDs de jogadores ou entidades
            // tournament.addPlayer(playerId); ou algo similar
            tournamentRepository.save(tournament);
            return true;
        }
        return false;
    }


    // Verificar se um jogador existe
    public boolean checkPlayerExists(String playerId) {
        ResponseEntity<Void> response = accountClient.checkAccountExists(playerId);
        return response.getStatusCode().is2xxSuccessful();
    }
}