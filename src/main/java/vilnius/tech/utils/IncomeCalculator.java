package vilnius.tech.utils;

import org.hibernate.Session;
import vilnius.tech.hibernate.FinancialCategory;
import vilnius.tech.hibernate.service.FinancialCategoryService;
import vilnius.tech.hibernate.service.IncomeService;

import java.sql.Timestamp;

public class IncomeCalculator {

    private final Session session;
    private final IncomeService incomeService;
    private final FinancialCategoryService financialCategoryService;

    public IncomeCalculator(FinancialCategory category, Session session) {
        this.category = category;
        this.session = session;
        this.incomeService = new IncomeService(session);
        this.financialCategoryService = new FinancialCategoryService(session);
    }

    public long getTotal_IncludeChildren() {
        return getTotal_IncludeChildren(null, null);
    }

    public long getTotal_IncludeChildren(Timestamp from, Timestamp to) {
        long total = getTotal(from, to);

        for(var childCategory : financialCategoryService.find_Parent(category)) {
            total += new IncomeCalculator(childCategory, session).getTotal_IncludeChildren(from, to);
        }

        return total;
    }

    public long getTotal() {
        return getTotal(null, null);
    }

    public long getTotal(Timestamp from, Timestamp to) {
        long total = 0;

        for(var expense : incomeService.find_Category(category, from, to)) {
            total += expense.getSum();
        }

        return total;
    }

    private final FinancialCategory category;

}
