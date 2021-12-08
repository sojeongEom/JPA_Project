import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntityCreateUpdateTime {
    @Column(name = "CREATE_DATE")
    private LocalDateTime create;
    @Column(name = "UPDATE_DATE")
    private LocalDateTime update;

    public BaseEntityCreateUpdateTime() {
        this.create = LocalDateTime.now();
    }

    protected void update() {
        this.update = LocalDateTime.now();
    }
}
