package task7.ui.repositories;

import org.springframework.data.repository.CrudRepository;
import task7.ui.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {

}
