package app.services;

import app.entities.Category;
import app.entities.Transaction;
import app.exceptions.CategoryNotFound;
import app.repositories.CategoriesRepository;
import app.repositories.TransactionsRepository;
import lombok.Getter;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransactionsService {

    private final TransactionsRepository transactionsRepository;
    private final CategoriesRepository categoriesRepository;
    @Getter
    private final static String[] categoryNames = {
            "Продукты", "Транспорт", "Лекарства", "Зарплата", "Шопинг", "Кафе", "Досуг"
    };
    @Getter
    private final static List<Category> categories = new ArrayList<>();

    public TransactionsService(TransactionsRepository transactionsRepository, CategoriesRepository categoryRepository){
        this.transactionsRepository = transactionsRepository;
        this.categoriesRepository = categoryRepository;

        Arrays.sort(categoryNames);
        for (String categoryName : categoryNames){
            this.categoriesRepository.insertIgnore(categoryName);
            categories.add(this.categoriesRepository.findByCategoryName(categoryName).orElse(null));
        }
    }

    public void save(Transaction transaction, Category category) throws CategoryNotFound{
        category = categoriesRepository.findByCategoryName(category.getCategoryName()).orElse(null);
        if (category == null){
            throw new CategoryNotFound();
        }
        transaction.setCategory(category);
        transactionsRepository.save(transaction);
    }
}
