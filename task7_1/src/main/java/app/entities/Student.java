package app.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "students")
public class Student {
    @Getter
    @Id
    @SequenceGenerator(name = "students_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "students_id_seq")
    private int id;

    @Getter
    @Setter
    @ManyToOne(targetEntity = Group.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    private Group group;

    @Getter
    @Setter
    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @Getter
    @Setter
    @Column(name = "second_name")
    private String secondName;

    @Getter
    @Setter
    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @Getter
    @Setter
    @NotNull
    @Column(name = "birthday_name")
    private Date birthdayDate;

    public String toString() {
        return lastName + " " + firstName + " " + secondName + " " + birthdayDate;
    }
}
