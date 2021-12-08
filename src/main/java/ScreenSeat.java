import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "SCREEN_SEAT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScreenSeat {
    @Id
    @GeneratedValue
    @Column(name = "SCREEN_SEAT_ID")
    private long id;
    @ManyToOne
    @JoinColumn(name ="SHEET_ID")
    private Sheet sheet;
    @ManyToOne
    @JoinColumn(name ="TICKETING_ID")
    private Ticketing ticketing;

    public void setSheet(Sheet sheet){
        if(this.sheet !=null){
            this.sheet.getScreenSeatList().remove(this);
        }
        this.sheet = sheet;
        sheet.getScreenSeatList().add(this);
    }

    public void setTicketing(Ticketing ticketing){
        if(this.ticketing !=null){
            this.ticketing.getScreenSeatList().remove(this);
        }
        this.ticketing = ticketing;
        ticketing.getScreenSeatList().add(this);
    }

}
