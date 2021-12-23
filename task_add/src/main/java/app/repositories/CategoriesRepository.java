package app.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import app.entities.Category;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public interface CategoriesRepository extends CrudRepository<Category, Integer> {
    Optional<Category> findByCategoryName(String name);

    @Transactional
    @Modifying
    @Query(value = "insert ignore into `categories` (`name`) values (:categoryName)", nativeQuery = true)
    void insertIgnore(@Param("categoryName") String categoryName);
}
