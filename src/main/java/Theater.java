import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Theater {
    @Id
    @Column(name = "THEATER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private int floor;
    @OneToMany(mappedBy = "theater")
    private List<Sheet> sheetList = new ArrayList<Sheet>();
    @OneToMany(mappedBy = "theater")
    private List<Screening> screensList = new ArrayList<Screening>();

    public Theater(String name, int floor) {
        this.name = name;
        this.floor = floor;
    }
}
