import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Actor extends Person{
    private int height;
    private String instagram;

    public Actor(String name, LocalDate birth, int height, String instagram) {
        super(name, birth);
        this.height = height;
        this.instagram = instagram;
    }

    public String getInfo() {
        return super.getInfo() + " " + height + " " + instagram;
    }
}
