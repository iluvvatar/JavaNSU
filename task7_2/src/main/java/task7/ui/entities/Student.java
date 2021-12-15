package task7.ui.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    private String firstName;

    @Getter
    @Setter
    private String secondName;

    @Getter
    @Setter
    @NotNull
    private String lastName;

    @Getter
    @Setter
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdayDate;

    public String toString() {
        return lastName + " " + firstName + " " + secondName + " " + birthdayDate;
    }
}
