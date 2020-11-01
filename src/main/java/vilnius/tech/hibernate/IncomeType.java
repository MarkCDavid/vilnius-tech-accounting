package vilnius.tech.hibernate;


import java.util.HashSet;
import java.util.Set;

public class IncomeType extends FlowType {

    private Set<Income> incomes = new HashSet<>();

    public IncomeType() {
    }

    public IncomeType(String name, String code) {
        super(name, code);
    }

    public Set<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(Set<Income> incomes) {
        this.incomes = incomes;
    }


}
