package insper.store.tournamentmanager;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Entity
@Table(name = "tournaments")
@Getter @Setter
@NoArgsConstructor
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

    // Se você decidir adicionar jogadores diretamente ao torneio,
    // você pode adicionar um campo como este:
    // @OneToMany(mappedBy = "tournament")
    // private Set<PlayerModel> players;

    // Aproveitando a funcionalidade do Lombok para criar o Builder
    @Builder
    public TournamentManagerModel(String id, String name, LocalDateTime startTime, String description) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.description = description;
    }
}