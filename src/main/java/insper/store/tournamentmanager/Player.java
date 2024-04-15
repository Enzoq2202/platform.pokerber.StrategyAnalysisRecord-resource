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
    private String id;  // ID do jogador gerenciado externamente

    @Column(name = "player_name", nullable = false)
    private String name;  // Nome do jogador

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;  // Torneio associado ao jogador

    
}
