import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Screening {
    @Id
    @Column(name = "SCREENING_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "THEATER_ID")
    private Theater theater;
    @Column
    private LocalDateTime start;
    @Column
    private LocalDateTime end;
    @OneToMany(mappedBy = "screening")
    private List<Sheet> sheetList = new ArrayList<>();

    public Screening(Movie movie, Theater theater, LocalDateTime start, LocalDateTime end) {
        this.movie = movie;
        this.theater = theater;
        this.start = start;
        this.end = end;
    }
    public Screening(LocalDateTime start, LocalDateTime end) {
        this.movie = movie;
        this.theater = theater;
        this.start = start;
        this.end = end;
    }

    public void setMovie(Movie movie){
        if(this.movie !=null){
            this.movie.getScreensList().remove(this);
        }
        this.movie = movie;
        movie.getScreensList().add(this);
    }
    public void setTheater(Theater theater){
        if(this.theater !=null){
            this.theater.getScreensList().remove(this);
        }
        this.theater = theater;
        theater.getScreensList().add(this);
    }
}
