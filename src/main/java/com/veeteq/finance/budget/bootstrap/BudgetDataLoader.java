package com.veeteq.finance.budget.bootstrap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.veeteq.finance.budget.model.Account;
import com.veeteq.finance.budget.model.Category;
import com.veeteq.finance.budget.model.CategoryType;
import com.veeteq.finance.budget.model.Item;
import com.veeteq.finance.budget.service.AccountService;
import com.veeteq.finance.budget.service.CategoryService;
import com.veeteq.finance.budget.service.ItemService;

@Component
@Profile(value = {"default"})
public class BudgetDataLoader implements CommandLineRunner {
    private final static Logger LOG = LoggerFactory.getLogger(BudgetDataLoader.class);

    @Value(value = "${dataloader.basedirectory}")
    private Path baseDirectory;

    private final CategoryService categoryService;
    private final ItemService itemService;
    private final AccountService userService;
    //private final IncomeService incomeService;
    //private final ExpenseService expenseService;

    @Autowired
    public BudgetDataLoader(CategoryService categoryService, ItemService itemService, AccountService userService) {
        this.categoryService = categoryService;
        this.itemService = itemService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (Files.exists(baseDirectory)) {
            loadCategories(true);
            loadItems(true);
            loadUsers(true);
            //loadIncomes(true);
            //loadExpenses(true);
        }
    }

    private void loadCategories(boolean toBeLoaded) {
        if (toBeLoaded) {
            LOG.info("Start loading categories.....");
            Path path = baseDirectory.resolve("categories.txt");
            getStreamFromFile(path).forEach(line -> {
                String[] values = line.split("\t");
                Category category = Category.builder()
                        .id(Long.valueOf(values[0]))
                        .name(values[1])
                        .categoryType(CategoryType.valueOf(values[2]))
                        .build();
                categoryService.save(category);
            });
            LOG.info("{} Categories loaded...", categoryService.count());
        }
    }

    private void loadItems(boolean toBeLoaded) {
        if (toBeLoaded) {
            LOG.info("Start loading items.....");
            Path path = baseDirectory.resolve("items.txt");
            getStreamFromFile(path).forEach(line -> {
                String[] values = line.split("\t");
                Item item = Item.builder()
                        .id(Long.valueOf(values[0]))
                        .category(categoryService.findById(Long.parseLong(values[1])))
                        .name(values[2])
                        .build();
                itemService.save(item);
            });
            LOG.info("{} Items loaded...", itemService.count());
        }
    }

    private void loadUsers(boolean toBeLoaded) {
        if (toBeLoaded) {
            LOG.info("Start loading accounts.....");
            Path path = baseDirectory.resolve("users.txt");
            getStreamFromFile(path).forEach(line -> {
                String[] values = line.split("\t");
                Account user = Account.builder()
                        .id(Long.valueOf(values[0]))
                        .name(values[1])
                        .build();
                userService.save(user);
            });
            LOG.info("{} Accounts loaded...", userService.count());
        }
    }
/*
    private void loadIncomes(boolean toBeLoaded) {
        if (toBeLoaded) {
            Path path = baseDirectory.resolve("incomes.txt");
            getStreamFromFile(path).forEach(line -> {
                String[] values = line.split("\t");
                String comment = values[4].equals("null") ? null : values[4];
                Income income = Income.builder()
                        .operationDate(DateUtil.parse(values[1]))
                        .item(itemService.findById(Long.parseLong(values[2])))
                        .amount(new BigDecimal(values[3]))
                        .comment(comment)
                        .user(userService.findById(Long.parseLong(values[5])))
                        .build();
                income.setId(Long.valueOf(values[0]));
                incomeService.save(income);
            });
            log.debug("Incomes loaded...");
        }
    }
*/
/*
    private void loadExpenses(boolean toBeLoaded) {
        if (toBeLoaded) {
            Path path = baseDirectory.resolve("expences.txt");
            getStreamFromFile(path).forEach(line -> {
                String[] values = line.split("\t");
                String comment = values[5].equals("null") ? null : values[5];
                Expense expense = Expense.builder()
                        .operationDate(DateUtil.parse(values[1]))
                        .item(itemService.findById(Long.parseLong(values[2])))
                        .count(new BigDecimal(values[3]))
                        .price(new BigDecimal(values[4]))
                        .comment(comment)
                        .user(userService.findById(Long.parseLong(values[6])))
                        .build();
                expense.setId(Long.valueOf(values[0]));
                try {
                    expenseService.save(expense);
                } catch (DataAccessException exc) {
                    log.error("Error during loading record: " + line);
                }
            });
            log.debug("Incomes loaded...");
        }
    }
*/

    private Stream<String> getStreamFromFile(Path path) {
        try {
            return Files.lines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}