import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Sheet {
    @Id
    @Column(name = "SHEET_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ROW_IDX")
    private int row;

    @Column(name = "COLUMN_IDX")
    private int column;

    @Column(name = "STATE")
    private String condition;

    @ManyToOne
    @JoinColumn(name = "THEATER_ID")
    private Theater theater;

//    @ManyToOne
//    @JoinColumn(name = "TICKETING_ID")
//    private Ticketing ticketing;

    @OneToMany(mappedBy = "sheet")
    private List<ScreenSeat> screenSeatList = new ArrayList<ScreenSeat>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCREENING_ID")
    private Screening screening;

    public Sheet(int row, int column, String condition) {
        this.row = row;
        this.column = column;
        this.condition = condition;
    }
    public void setTheater(Theater theater){
        if(this.theater !=null){
            this.theater.getSheetList().remove(this);
        }
        this.theater = theater;
        theater.getSheetList().add(this);
    }


}
