package vilnius.tech.controller;

import vilnius.tech.dal.*;
import vilnius.tech.utils.UserInput;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class IncomeController extends CRUDManager<Income> implements CRUD<Income> {



    public IncomeController(Session session, FinancialCategory parent) {
        super(session);
        this.parent = parent;
    }


    @Override
    public Income create(Scanner scanner) {
        System.out.println("Income type:");
        IncomeType type = new IncomeTypeController(getSession()).read(scanner);
        System.out.println("Owner:");
        User owner = new UserController(getSession()).read(scanner);
        int sum = UserInput.getInteger(scanner, "Income").orElse(0);
        ZonedDateTime dateTime = UserInput.getDate(scanner, "Date (empty for current time)");

        Income income = new Income(getSession());
        parent.getIncomes().add(income);
        income.setType(type);
        income.setOwner(owner);
        income.setSum(sum);
        income.setDateTime(dateTime);
        return income;
    }

    @Override
    public List<Income> readAll() {
        return getSession().get(Income.class);
    }

    @Override
    public Income update(Scanner scanner) {
        Income income = read(scanner);

        if(UserInput.getUserConfirmation(scanner, "Update Income Type")) {
            income.setType( new IncomeTypeController(getSession()).read(scanner));
        }

        if(UserInput.getUserConfirmation(scanner, "Update Owner")) {
            income.setOwner(new UserController(getSession()).read(scanner));
        }

        if(UserInput.getUserConfirmation(scanner, "Update sum")) {
            income.setSum(UserInput.getInteger(scanner, "Income").orElse(0));
        }

        if(UserInput.getUserConfirmation(scanner, "Update Owner")) {
            income.setDateTime(UserInput.getDate(scanner, "Date (empty for current time)"));
        }

        return income;
    }

    @Override
    public void delete(Scanner scanner) {
        Income income = read(scanner);
        if(income == null || income.isDeleted())
            return;

        parent.getIncomes().remove(income);
        income.setDeleted(true);
    }


    @Override
    protected String getManagedObjectName() {
        return "Income";
    }
    private final FinancialCategory parent;
}
