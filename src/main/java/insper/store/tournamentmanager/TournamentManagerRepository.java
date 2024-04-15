package insper.store.tournamentmanager;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface TournamentManagerRepository extends CrudRepository<TournamentManagerModel, String> {

    // Encontrar torneios por nome
    List<TournamentManagerModel> findByName(String name);

    // Encontrar torneios que começam em uma determinada data
    List<TournamentManagerModel> findByStartTime(LocalDateTime startTime);
}
