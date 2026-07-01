package gr.aueb.cf.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "teachers")
@NoArgsConstructor
@Getter
@Setter
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", length = 255, nullable = false, unique = false)
    private String firstname;

    @Column( nullable = false)
    private String lastname;


    public Teacher(String lastname, String firstname) {
        this.lastname = lastname;
        this.firstname = firstname;
    }

    @Override
    public String toString() {
        return String.format("%d %s, %s",  id, firstname, lastname);
    }
}
