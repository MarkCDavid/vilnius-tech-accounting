package vilnius.tech.utils;

import vilnius.tech.hibernate.FinancialCategory;

public class ExpenseCalculator {

    public ExpenseCalculator(FinancialCategory category) {
        this.category = category;
    }

    public long getTotal() {
        long total = 0;

        for(var expense : category.getExpenses()) {
            total += expense.getSum();
        }

        for(var childCategory : category.getChildren()) {
            total += new ExpenseCalculator(childCategory).getTotal();
        }

        return total;
    }

    private final FinancialCategory category;

}
