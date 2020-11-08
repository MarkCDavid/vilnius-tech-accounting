package vilnius.tech.view.controller.modal.result;

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
