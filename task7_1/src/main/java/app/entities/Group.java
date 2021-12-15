package app.entities;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`groups`")
public class Group {
    @Getter
    @Id
    @SequenceGenerator(name = "groups_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groups_id_seq")
    private int id;

    @Getter
    @Setter
    @NotNull
    @Column(unique = true)
    private String name;

    @Getter
    @Setter
    @OneToMany(targetEntity = Student.class, fetch = FetchType.LAZY, mappedBy = "group")
    private List<Student> students = new ArrayList<>();

    public String toString(){
        String title =  "гр." + this.name;
        StringBuilder builder = new StringBuilder();
        for (Student student : this.getStudents()){
            System.out.println(123);
            builder.append("\n- " + student.toString());
        }
        return title + builder;
    }
}
