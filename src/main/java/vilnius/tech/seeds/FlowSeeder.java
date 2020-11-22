package vilnius.tech.seeds;

import org.hibernate.Session;
import vilnius.tech.hibernate.FinancialCategory;
import vilnius.tech.hibernate.IncomeType;
import vilnius.tech.hibernate.service.*;
import vilnius.tech.utils.TimeUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;

public class FlowSeeder implements Seeder {

    private ExpenseService expenseService;
    private IncomeService incomeService;
    private ExpenseTypeService expenseTypeService;
    private IncomeTypeService incomeTypeService;
    private FinancialCategoryService financialCategoryService;
    private UserService userService;
    private Random prng;

    @Override
    public void seed(Session session) {

        prng = new Random();

        userService = new UserService(session);
        financialCategoryService = new FinancialCategoryService(session);

        expenseTypeService = new ExpenseTypeService(session);
        incomeTypeService = new IncomeTypeService(session);

        expenseService = new ExpenseService(session);
        incomeService = new IncomeService(session);

        for(var category: financialCategoryService.find()) {
            seedExpenses(category);
            seedIncomes(category);
        }

    }

    private void seedExpenses(FinancialCategory category) {
        var expenseTypes = expenseTypeService.find();
        var users = userService.find();

        var expenses = random(20, 500);

        for(int i = 0; i < expenses; i++) {
            expenseService.create(
                    users.get(random(users.size())),
                    random(10, 2000),
                    randomDateAroundNow(365, 12, 2),
                    category,
                    expenseTypes.get(random(expenseTypes.size()))
            );
        }
    }

    private void seedIncomes(FinancialCategory category) {
        var incomeTypes = incomeTypeService.find();
        var users = userService.find();

        var incomes = random(20, 500);

        for(int i = 0; i < incomes; i++) {
            incomeService.create(
                    users.get(random(users.size())),
                    random_long(10, 2000),
                    randomDateAroundNow(365, 12, 2),
                    category,
                    incomeTypes.get(random(incomeTypes.size()))
            );
        }
    }

    private int random(int from, int to) {
        return prng.nextInt(to - from) + from;
    }

    private Long random_long(int from, int to) {
        return (long) (prng.nextInt(to - from) + from);
    }

    private int random(int to) {
        return prng.nextInt(to);
    }

    private Timestamp randomDateAroundNow(int dayDiff, int monthDiff, int yearDiff) {

        var now = TimeUtils.nowZoned();

        return Timestamp.from(
                now.plusDays(random(-dayDiff, dayDiff))
                        .plusMonths(random(-monthDiff, monthDiff))
                        .plusYears(random(-yearDiff, yearDiff)).toInstant());

//        var dayDelta = random(-dayDiff, dayDiff);
//        var monthDelta = random(-monthDiff, monthDiff);
//        var yearDelta = random(-yearDiff, yearDiff);
//
//        now = dayDelta > 0 ? now.plusDays(dayDelta) : now.minusDays(-dayDelta);
//        now = monthDelta > 0 ? now.plusMonths(monthDelta) : now.minusMonths(-monthDelta);
//        now = yearDelta > 0 ? now.plusYears(yearDelta) : now.minusYears(-yearDelta);
//
//        return Timestamp.from(now.toInstant());
    }

}
