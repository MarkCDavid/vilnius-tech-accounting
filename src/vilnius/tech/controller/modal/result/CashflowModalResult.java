package vilnius.tech.controller.modal.result;

import vilnius.tech.dal.IncomeType;

public class CashflowModalResult<T> {

    public T getType() {
        return type;
    }

    public void setType(T type) {
        this.type = type;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    private T type;
    private Long sum;

}
