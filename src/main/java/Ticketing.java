import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Ticketing {
    @Id
    @Column(name = "TICKETING_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "SCREENING_ID")
    private Screening screening;

    @Column()
    private String state;

    @OneToMany(mappedBy = "ticketing")
    private List<ScreenSeat> screenSeatList = new ArrayList<ScreenSeat>();

    public Ticketing(User user, Screening screening, String state) {
        this.user = user;
        this.screening = screening;
        this.state = state;
    }

    public Ticketing(User user, Screening screening) {
        this.user = user;
        this.screening = screening;
    }

}
