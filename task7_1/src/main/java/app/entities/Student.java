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
    @ManyToOne(targetEntity = Group.class, fetch = FetchType.LAZY)
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
    private Date birthdayDate;

    public String toString() {
        return this.lastName + " " + this.firstName + " " + this.secondName;
    }
}
