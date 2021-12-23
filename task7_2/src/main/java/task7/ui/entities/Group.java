package task7.ui.entities;

import javax.validation.constraints.NotBlank;
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
    @NotBlank
    @Column(name = "name", unique = true)
    private String name;

    @Getter
    @Setter
    @OneToMany(targetEntity = Student.class, fetch = FetchType.EAGER, mappedBy = "group")
    private List<Student> students = new ArrayList<>();

    public String toString(){
        String title =  "гр." + name;
        StringBuilder builder = new StringBuilder();
        for (Student student : students){
            builder.append("\n- " + student);
        }
        return title + builder;
    }
}
