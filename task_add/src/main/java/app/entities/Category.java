package app.entities;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @NotNull
    @Column(name = "name", unique = true)
    private String categoryName;

    public String toString(){
        return categoryName;
    }
}
