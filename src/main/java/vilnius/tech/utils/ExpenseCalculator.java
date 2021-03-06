package vilnius.tech.utils;

import org.hibernate.Session;
import vilnius.tech.hibernate.FinancialCategory;
import vilnius.tech.hibernate.service.ExpenseService;
import vilnius.tech.hibernate.service.FinancialCategoryService;

import java.sql.Timestamp;

public class ExpenseCalculator {

    private final Session session;
    private final ExpenseService expensesService;
    private final FinancialCategoryService financialCategoryService;

    public ExpenseCalculator(FinancialCategory category, Session session) {
        this.category = category;
        this.session = session;
        this.expensesService = new ExpenseService(session);
        this.financialCategoryService = new FinancialCategoryService(session);
    }

    public long getTotal_IncludeChildren() {
        return getTotal_IncludeChildren(null, null);
    }

    public long getTotal_IncludeChildren(Timestamp from, Timestamp to) {
        long total = getTotal(from, to);

        for(var childCategory : financialCategoryService.find_Parent(category)) {
            total += new ExpenseCalculator(childCategory, session).getTotal_IncludeChildren(from, to);
        }

        return total;
    }

    public long getTotal() {
        return getTotal(null, null);
    }

    public long getTotal(Timestamp from, Timestamp to) {
        long total = 0;

        for(var expense : expensesService.find_Category(category, from, to)) {
            total += expense.getSum();
        }

        return total;
    }

    private final FinancialCategory category;

}
