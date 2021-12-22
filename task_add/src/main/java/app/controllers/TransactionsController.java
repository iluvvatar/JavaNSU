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

import javax.validation.Valid;

import app.exceptions.CategoryNotFound;
import app.services.TransactionsService;
import org.hibernate.TransientPropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import app.entities.Category;
import app.entities.Transaction;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @author Rob Winch
 */
@Controller
public class TransactionsController {
    TransactionsService transactionsService;

    @Autowired
    public void setTransactionsService(TransactionsService transactionsService){
        this.transactionsService = transactionsService;
    }

    @GetMapping("/add")
    public ModelAndView addPage(@ModelAttribute Category category, @ModelAttribute Transaction transaction) {
        return new ModelAndView("add", "categoryNames", TransactionsService.getCategoryNames());
    }

    @PostMapping("/add")
    public ModelAndView add(@Valid Category category,
                            @Valid Transaction transaction,
                            BindingResult result,
                            RedirectAttributes redirect) {
        LocalDate today = LocalDate.now();
        if (transaction.getDate().isAfter(today)){
            result.addError(new FieldError("transaction", "date", "Дата должна быть не больше текущей"));
        }
        if (result.hasErrors()) {
            HashMap<String, Object> params = new HashMap<>();
            params.put("errors", result.getAllErrors());
            params.put("categoryNames", TransactionsService.getCategoryNames());
            return new ModelAndView("add", params);
        }

        try {
            transactionsService.save(transaction, category);
        } catch (CategoryNotFound e){
            result.addError(new FieldError("category", "categoryName", "Некорректная категория"));
        }
        if (result.hasErrors()) {
            HashMap<String, Object> params = new HashMap<>();
            params.put("errors", result.getAllErrors());
            params.put("categoryNames", TransactionsService.getCategoryNames());
            return new ModelAndView("add", params);
        }

        redirect.addFlashAttribute("successMessage", "Successfully added a new transaction");
        return new ModelAndView("redirect:/");
    }
}
