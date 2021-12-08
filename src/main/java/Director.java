import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Director extends Person{
    @Column
    private String birthPlace;

    public Director(String name, LocalDate birth, String birthPlace) {
        super(name, birth);
        this.birthPlace = birthPlace;
    }

    public String getInfo() {
        return super.getInfo() + " " + birthPlace;
    }
}
