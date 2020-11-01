package vilnius.tech.hibernate;


import java.util.ArrayList;
import java.util.List;

public class IncomeType extends FlowType {

    private List<Income> incomes = new ArrayList<>();

    public IncomeType() {
    }

    public IncomeType(Integer id, String name, String code, List<Income> incomes) {
        super(id, name, code);
        this.incomes = incomes;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }


}
