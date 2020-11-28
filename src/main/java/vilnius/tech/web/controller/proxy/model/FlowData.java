package vilnius.tech.web.controller.proxy.model;

import com.google.gson.annotations.Expose;
import vilnius.tech.hibernate.Expense;
import vilnius.tech.hibernate.Income;

import java.util.List;

public class FlowData {

    @Expose
    private List<Income> incomes;

    @Expose
    private List<Expense> expenses;

    @Expose
    private Long totalIncome;

    @Expose
    private Long totalExpenses;

    @Expose
    private Long totalIncomeWithChildren;

    @Expose
    private Long totalExpensesWithChildren;

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public Long getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Long totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Long getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(Long totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public Long getTotalIncomeWithChildren() {
        return totalIncomeWithChildren;
    }

    public void setTotalIncomeWithChildren(Long totalIncomeWithChildren) {
        this.totalIncomeWithChildren = totalIncomeWithChildren;
    }

    public Long getTotalExpensesWithChildren() {
        return totalExpensesWithChildren;
    }

    public void setTotalExpensesWithChildren(Long totalExpensesWithChildren) {
        this.totalExpensesWithChildren = totalExpensesWithChildren;
    }
}
