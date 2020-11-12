package vilnius.tech.utils;

import org.hibernate.Session;
import vilnius.tech.hibernate.FinancialCategory;
import vilnius.tech.hibernate.service.FinancialCategoryService;
import vilnius.tech.hibernate.service.IncomeService;

public class IncomeCalculator {

    private final Session session;
    private final IncomeService expensesService;
    private final FinancialCategoryService financialCategoryService;

    public IncomeCalculator(FinancialCategory category, Session session) {
        this.category = category;
        this.session = session;
        this.expensesService = new IncomeService(session);
        this.financialCategoryService = new FinancialCategoryService(session);
    }

    public long getTotal() {
        long total = 0;

        for(var income : expensesService.find_Category(category)) {
            total += income.getSum();
        }

        for(var childCategory : financialCategoryService.find_Parent(category)) {
            total += new IncomeCalculator(childCategory, session).getTotal();
        }

        return total;
    }

    private final FinancialCategory category;

}
