package vilnius.tech.controller;

import vilnius.tech.dal.Income;
import vilnius.tech.dal.IncomeType;
import vilnius.tech.dal.Session;
import vilnius.tech.utils.UserInput;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class IncomeTypeController extends CRUDManager<IncomeType> implements CRUD<IncomeType> {

    public IncomeTypeController(Session session) {
        super(session);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected void initializeReferenceMap(Map<String, Reference> referenceMap) {
        referenceMap.put("incomes", new Reference(Income.class, (Function<Income, Integer>) income -> income.getType().getOid()));
    }

    @Override
    public IncomeType create(Scanner scanner) {
        String name = UserInput.getString(scanner, "Income name");
        String code = UserInput.getString(scanner, "Income code");

        IncomeType income = new IncomeType(getSession());

        income.setName(name);
        income.setCode(code);
        return income;
    }

    @Override
    public List<IncomeType> readAll() {
        return getSession().get(IncomeType.class);
    }

    @Override
    public IncomeType update(Scanner scanner) {
        IncomeType incomeType = read(scanner);

        if(UserInput.getUserConfirmation(scanner, "Update Income name")) {
            incomeType.setName(UserInput.getString(scanner, "Income name"));
        }

        if(UserInput.getUserConfirmation(scanner, "Update Income code")) {
            incomeType.setCode(UserInput.getString(scanner, "Income code"));
        }

        return incomeType;
    }

    @Override
    public void delete(Scanner scanner) {
        IncomeType incomeType = read(scanner);
        if(incomeType == null || incomeType.isDeleted())
            return;

        List<Income> iRefs = getSession().query(Income.class, e -> e.getType().getOid() == incomeType.getOid());
        if(!iRefs.isEmpty() && !UserInput.getDeleteConfirmation(scanner, getManagedObjectName(), "Income", iRefs.size())) {
            return;
        }

        for(Income e: iRefs) {
            e.setType(null);
        }

        incomeType.setDeleted(true);
    }


    @Override
    protected String getManagedObjectName() {
        return "Income Type";
    }
}
