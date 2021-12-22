package app.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import app.entities.Category;
import app.entities.Transaction;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TransactionsRepository extends CrudRepository<Transaction, Integer> {
    List<Transaction> findAllByDate(LocalDate date);

    @Query(value = "select distinct `date` from `transactions` order by `date` desc", nativeQuery = true)
    List<Date> getAllDates();

    @Query(value = "select sum(`sum`) from `transactions` where `date`=:date", nativeQuery = true)
    Integer getSumByDate(@Param("date") LocalDate date);

    @Query(value = "select distinct `name` from `transactions`" +
            "where `date`=:date and `category_id`=:categoryId order by `name`", nativeQuery = true)
    List<String> getAllNamesByDateAndCategoryId(@Param("date") LocalDate date,
                                                @Param("categoryId") Integer categoryId);

    @Query(value = "select sum(`sum`) from `transactions`" +
            "where `date`=:date and `category_id`=:categoryId", nativeQuery = true)
    Integer getSumByDateAndCategoryId(@Param("date") LocalDate date,
                                      @Param("categoryId") Integer categoryId);

    @Query(value = "select sum(`sum`) from `transactions`" +
            "where `date`=:date and `category_id`=:categoryId and `name`=:name", nativeQuery = true)
    Integer getSumByDateAndCategoryIdAndName(@Param("date") LocalDate date,
                                             @Param("categoryId") Integer categoryId,
                                             @Param("name") String name);

    @Query(value = "select sum(`sum`) from `transactions`" +
            "where `category_id`=:categoryId and `date` between :dateFrom and :dateTo and `sum` < 0", nativeQuery = true)
    Integer getSpendingsByCategoryIdBetween(@Param("categoryId") Integer categoryId,
                                            @Param("dateFrom") LocalDate dateFrom,
                                            @Param("dateTo") LocalDate dateTo);
}
