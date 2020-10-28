package vilnius.tech.utils;

import vilnius.tech.dal.FinancialCategory;

public class ExpenseCalculator {

    public ExpenseCalculator(FinancialCategory category) {
        this.category = category;
    }

    public long getTotal() {
        long total = 0;

        var childCategories = category.getSession().query(FinancialCategory.class, category -> category.getParent() == this.category);

        for(var expense : category.getExpenses()) {
            total += expense.getSum();
        }

        for(var childCategory : childCategories) {
            total += new ExpenseCalculator(childCategory).getTotal();
        }

        return total;
    }

    private final FinancialCategory category;

}
