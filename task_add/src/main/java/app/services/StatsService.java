package app.services;

import app.entities.Category;
import app.entities.Transaction;
import app.repositories.CategoriesRepository;
import app.repositories.TransactionsRepository;
import lombok.Getter;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.*;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatsService {

    private final TransactionsRepository transactionsRepository;

    public StatsService(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    public List<LocalDate> getSortedDatesList() {
        return transactionsRepository.getAllDates().stream().map(Date::toLocalDate).collect(Collectors.toList());
    }

    public HashMap<LocalDate, Integer> getSumsByDates(List<LocalDate> dates) {
        HashMap<LocalDate, Integer> sumsByDates = new HashMap<>();
        for (LocalDate date : dates) {
            sumsByDates.put(date, transactionsRepository.getSumByDate(date));
        }
        return sumsByDates;
    }

    public HashMap<LocalDate, HashMap<Category, Integer>> getSumsByDatesAndCategories(
            List<LocalDate> sortedDates, List<Category> sortedCategories) {
        HashMap<LocalDate, HashMap<Category, Integer>> sumsByDates = new HashMap<>();
        for (LocalDate date : sortedDates) {
            HashMap<Category, Integer> sumsByCategories = new HashMap<>();
            for (Category category : sortedCategories) {
                sumsByCategories.put(
                        category,
                        transactionsRepository.getSumByDateAndCategoryId(date, category.getId())
                );
            }
            sumsByDates.put(date, sumsByCategories);
        }
        return sumsByDates;
    }

    public HashMap<LocalDate, HashMap<Category, List<Pair<String, Integer>>>> getTransactionsByDatesAndCategories(
            List<LocalDate> sortedDates, List<Category> sortedCategories) {
        HashMap<LocalDate, HashMap<Category, List<Pair<String, Integer>>>> transactionsByDates = new HashMap<>();
        for (LocalDate date : sortedDates) {
            HashMap<Category, List<Pair<String, Integer>>> transactionsByCategories = new HashMap<>();
            for (Category category : sortedCategories) {
                List<Pair<String, Integer>> transactions = getTransactionsByDateAndCategory(date, category);
                if (!transactions.isEmpty()) {
                    transactionsByCategories.put(category, transactions);
                }
            }
            if (!transactionsByCategories.isEmpty()) {
                transactionsByDates.put(date, transactionsByCategories);
            }
        }
        return transactionsByDates;
    }

    private List<Pair<String, Integer>> getTransactionsByDateAndCategory(
            LocalDate date, Category category) {
        List<Pair<String, Integer>> transactions = new ArrayList<>();
        List<String> names = transactionsRepository.getAllNamesByDateAndCategoryId(date, category.getId());
        for (String name : names) {
            transactions.add(Pair.of(name, transactionsRepository.getSumByDateAndCategoryIdAndName(date, category.getId(), name)));
        }
        return transactions;
    }

    public HashMap<Category, Integer> getSpendingByCategoriesForPeriod(List<Category> categories, String period) {
        LocalDate dateTo = LocalDate.now();
        LocalDate dateFrom = switch (period) {
            case ("day"):
                yield dateTo;
            case ("week"):
                yield dateTo.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            case ("month"):
                yield dateTo.with(TemporalAdjusters.firstDayOfMonth());
            default:
                throw new RuntimeException("period should be in [\"day\", \"week\", \"month\"]");
        };
        HashMap<Category, Integer> spendingsByCategory = new HashMap<>();
        for (Category category : categories) {
            Integer spendings = transactionsRepository.getSpendingsByCategoryIdBetween(category.getId(), dateFrom, dateTo);
            if (spendings != null){
                spendingsByCategory.put(category, -spendings);
            }
        }
        return spendingsByCategory;
        }
    }
