package vilnius.tech.view.controller.modal.result;

public class ChoiceBoxModalResult<T> {

    public T getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(T selectedItem) {
        this.selectedItem = selectedItem;
    }

    private T selectedItem;
}
