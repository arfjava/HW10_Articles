package ir.maktab32.java.homeworks.hw10articles.entities.db2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String nationalCode;
    private String birthday;

    @ManyToMany
    private List<Role> roles;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", birthday='" + birthday + '\'' +
                ", roles=" + roles +
                '}';
    }
}
