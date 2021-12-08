import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

@Setter
@NoArgsConstructor
@Entity
@Getter
public class Movie {
    @Id
    @Column(name = "MOVIE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String title;
    @Column
    private LocalDate openDate;
    @Column
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Column
    private int time;
    @ManyToOne
    private Director director;
    @OneToMany
    @JoinColumn(name="PARTICIPATION_ID")
    private List<Participation> actors = new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    private List<Screening> screensList = new ArrayList<Screening>();


    public Movie(String title, LocalDate openDate, Genre genre, int time, Director director) {
        this.title = title;
        this.openDate = openDate;
        this.genre = genre;
        this.time = time;
        this.director = director;
    }

    public Movie(String title, LocalDate openDate, Genre genre, int time) {
        this.title = title;
        this.openDate = openDate;
        this.genre = genre;
        this.time = time;
    }

    private String getActorsInfo() {
        StringBuilder sb = new StringBuilder();
        actors.forEach((actor) -> {
            sb.append(actor.getInfo());
            sb.append("\n");
        });

        return sb.toString();
    }

    public String getInfo() {
        return "제목 : " + title + "\n"
                + "개봉일 : " + openDate + "\n"
                + "장르 : " + genre + "\n"
                + "러닝타임 : " + time + "\n"
                + "감독 : " + director.getInfo() + "\n"
                + getActorsInfo();
    }
}
