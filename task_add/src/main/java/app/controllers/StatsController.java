/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package app.controllers;

import app.entities.Category;
import app.entities.Transaction;
import app.services.StatsService;
import app.services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.*;

@Controller
public class StatsController {
    StatsService statsService;

    @Autowired
    public void setStatsService(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/")
    public ModelAndView list() {
        List<LocalDate> sortedDates = statsService.getSortedDatesList();
        List<Category> sortedCategories = TransactionsService.getCategories();
        HashMap<LocalDate, Integer> sumsByDates = statsService.getSumsByDates(sortedDates);
        HashMap<LocalDate, HashMap<Category, Integer>> sumsByDatesAndCategories =
                statsService.getSumsByDatesAndCategories(sortedDates, sortedCategories);
        HashMap<LocalDate, HashMap<Category, List<Pair<String, Integer>>>> transactionsByDatesAndCategories =
                statsService.getTransactionsByDatesAndCategories(sortedDates, sortedCategories);

        HashMap<String, Object> params = new HashMap<>();
        params.put("sortedDates", sortedDates);
        params.put("sortedCategories", sortedCategories);
        params.put("sumsByDates", sumsByDates);
        params.put("sumsByDatesAndCategories", sumsByDatesAndCategories);
        params.put("transactionsByDatesAndCategories", transactionsByDatesAndCategories);
        return new ModelAndView("list", params);
    }

    @GetMapping("/stats")
    public ModelAndView stats(@RequestParam(value = "period", defaultValue = "day") String period,
                              RedirectAttributes redirect) {
        String[] acceptablePeriods = {"day", "week", "month"};
        List<String> acceptablePeriodsList = Arrays.asList(acceptablePeriods);
        if (!acceptablePeriodsList.contains(period)) {
            redirect.addFlashAttribute("error", "Некорректный период");
            return new ModelAndView("redirect:/stats");
        }

        List<Category> sortedCategories = TransactionsService.getCategories();
        HashMap<Category, Integer> spendingsByCategories = statsService.getSpendingByCategoriesForPeriod(sortedCategories, period);
//        HashMap<>
        Integer totalSpendings = 0;
        for (Category category : sortedCategories){
            if (spendingsByCategories.containsKey(category)) {
                totalSpendings += spendingsByCategories.get(category);
            }
        }

        HashMap<String, Object> params = new HashMap<>();
        params.put("sortedCategories", sortedCategories);
        params.put("spendingsByCategories", spendingsByCategories);
        params.put("totalSpendings", totalSpendings);
        return new ModelAndView("stats", params);
    }
}
