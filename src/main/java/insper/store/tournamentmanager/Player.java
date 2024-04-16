package insper.store.tournamentmanager;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "players")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Player {

    @Id
    @Column(name = "player_id", nullable = false)
    private String id;

    @Column(name = "player_name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id")
    private TournamentManagerModel tournament;  // Usando TournamentManagerModel consistentemente
}

