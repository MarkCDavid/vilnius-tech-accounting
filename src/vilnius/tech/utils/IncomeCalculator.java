package vilnius.tech.utils;

import vilnius.tech.dal.FinancialCategory;

public class IncomeCalculator {

    public IncomeCalculator(FinancialCategory category) {
        this.category = category;
    }

    public long getTotal() {
        long total = 0;

        var childCategories = category.getSession().query(FinancialCategory.class, category -> category.getParent() == this.category);

        for(var income : category.getIncomes()) {
            total += income.getSum();
        }

        for(var childCategory : childCategories) {
            total += new IncomeCalculator(childCategory).getTotal();
        }

        return total;
    }

    private final FinancialCategory category;

}
