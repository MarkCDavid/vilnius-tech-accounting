package vilnius.tech.utils;

import vilnius.tech.hibernate.FinancialCategory;

public class IncomeCalculator {

    public IncomeCalculator(FinancialCategory category) {
        this.category = category;
    }

    public long getTotal() {
        long total = 0;

        for(var income : category.getIncomes()) {
            total += income.getSum();
        }

        for(var childCategory : category.getChildren()) {
            total += new IncomeCalculator(childCategory).getTotal();
        }

        return total;
    }

    private final FinancialCategory category;

}
