package task7.ui.repositories;

import org.springframework.data.repository.CrudRepository;
import task7.ui.entities.Group;

import java.util.Optional;

public interface GroupRepository extends CrudRepository<Group, Integer> {
    Optional<Group> findByName(String name);
}
