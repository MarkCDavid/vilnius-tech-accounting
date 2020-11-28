package vilnius.tech.web.controller.proxy.controller;

import org.springframework.http.ResponseEntity;
import vilnius.tech.hibernate.*;
import vilnius.tech.hibernate.service.*;
import vilnius.tech.utils.ExpenseCalculator;
import vilnius.tech.utils.IncomeCalculator;
import vilnius.tech.utils.TimeUtils;
import vilnius.tech.web.controller.proxy.model.FlowData;
import vilnius.tech.web.controller.proxy.model.FlowParameters;
import vilnius.tech.web.utils.HibernateUtils;
import vilnius.tech.web.utils.JsonResponseUtils;
import vilnius.tech.web.utils.Messages;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;

public class FlowProxy  {


    public ResponseEntity<String> get_FlowData(Integer id, FlowParameters flowParameters) {

        try(var categoryService = new FinancialCategoryService(HibernateUtils.getSession());
        var incomeService = new IncomeService(HibernateUtils.getSession());
        var expenseService = new ExpenseService(HibernateUtils.getSession())) {

            var from = parseDate(flowParameters.getFrom(), TimeUtils.yearStart());
            if (from == null)
                return JsonResponseUtils.BAD(Messages.invalidDateFormat("from"));

            var to = parseDate(flowParameters.getTo(), TimeUtils.yearEnd());
            if (to == null)
                return JsonResponseUtils.BAD(Messages.invalidDateFormat("to"));

            var category = categoryService.find(id);
            if (category == null)
                return JsonResponseUtils.BAD(Messages.itemNotFound(FinancialCategoryProxy.ENTITY_NAME, id));

            var result = new FlowData();

            result.setIncomes(incomeService.find_Category(category, from, to));
            result.setExpenses(expenseService.find_Category(category, from, to));

            var incomeCalculator = new IncomeCalculator(category, HibernateUtils.getSession());
            var expenseCalculator = new ExpenseCalculator(category, HibernateUtils.getSession());

            result.setTotalIncome(incomeCalculator.getTotal(from, to));
            result.setTotalIncomeWithChildren(incomeCalculator.getTotal_IncludeChildren(from, to));

            result.setTotalExpenses(expenseCalculator.getTotal(from, to));
            result.setTotalExpensesWithChildren(expenseCalculator.getTotal_IncludeChildren(from, to));

            return JsonResponseUtils.OK(result);
        }
    }

    private final static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static Timestamp parseDate(String value, Timestamp _default) {

        try {
            return value != null && !value.isBlank() ? Timestamp.from(format.parse(value).toInstant()) : _default;
        } catch (ParseException exception) {
            return null;
        }
    }



}
