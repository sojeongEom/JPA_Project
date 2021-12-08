import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseEntityCreateUpdateTime {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private int age;
    @Embedded
    private Address address;

    public User(String name, Integer age, String city, String street, String zipCode) {
        this.name = name;
        this.age = age;
        this.address = new Address(city, street, zipCode);
    }

    public void setName(String name) {
        this.name = name;
        update();
    }

    public void setAge(int age) {
        this.age = age;
        update();
    }

    public void setAddress(Address address) {
        this.address = address;
        update();
    }
}
