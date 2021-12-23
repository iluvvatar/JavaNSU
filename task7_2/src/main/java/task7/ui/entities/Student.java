package task7.ui.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @Getter
    @Setter
    @Column(name = "second_name")
    private String secondName;

    @Getter
    @Setter
    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @Getter
    @Setter
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthday_name")
    private Date birthdayDate;

    public String toString() {
        return lastName + " " + firstName + " " + secondName + " " + birthdayDate;
    }
}
