package insper.store.tournamentmanager;

import lombok.*;
import lombok.experimental.Accessors;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tournaments")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true, fluent = true)
public class TournamentManagerModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "tournament", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Player> players = new HashSet<>();

    public Set<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
        player.setTournament(this);
    }
}
