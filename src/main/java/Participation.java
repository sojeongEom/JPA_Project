import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Participation {
    @Id
    @Column(name = "PARTICIPATION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Actor actor;

    @Column
    private String rule;

    public Participation(Movie movie, Actor actor, String rule) {
        this.movie = movie;
        this.rule = rule;
        setActor(actor);
    }

    public void setActor(Actor actor) {
        if (this.actor != null) {
            movie.getActors().remove(this);
        }

        this.actor = actor;
        movie.getActors().add(this);
    }

    public String getInfo() {
        return actor.getInfo() + " " + rule;
    }
}
