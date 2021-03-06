package vilnius.tech.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.hibernate.Session;
import vilnius.tech.error.DatabaseExceptionPolicy;
import vilnius.tech.hibernate.FinancialCategory;
import vilnius.tech.hibernate.User;
import vilnius.tech.hibernate.service.FinancialCategoryService;
import vilnius.tech.view.controller.modal.CategoryModalController;
import vilnius.tech.view.controller.modal.result.CategoryModalResult;
import vilnius.tech.utils.TreeViewCache;
import vilnius.tech.view.Modal;
import vilnius.tech.view.View;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class CategoryController extends SessionController {

    public CategoryController(View previousView, User user, Session session) {
        super(session);
        this.user = user;
        this.previousView = previousView;
        this.treeRoot = new TreeItem<>();
        this.financialCategoryController = new FinancialCategoryService(session);
    }

    @FXML
    public void initialize() {
        categoryTree.setShowRoot(false);
        categoryTree.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue == null)
                return;

            var category = newValue.getValue();
            updateFields(category);
        });

        this.categoryTree.setRoot(treeRoot);
        updateTree();

        updateFields(getSelectedItem());
    }

    private void updateFields(FinancialCategory category) {
        var categoryAvailable = category != null;

        labelOwner.setVisible(categoryAvailable);
        buttonResponsible.setVisible(categoryAvailable);
        buttonExpense.setVisible(categoryAvailable);
        buttonIncome.setVisible(categoryAvailable);

        if(categoryAvailable) {
            labelOwner.setText(String.format("Owner: %s", category.getOwner().getUsername()));
        }
    }

    @FXML
    private void onAddNew() throws IOException {
        var node = getSelectedNode();
        if(node == null)
            return;

        var parentCategory = node.getValue();

        var modal = new Modal<>(new CategoryModalController(), getView(), "Add new Category", "categoryModal.fxml");
        var result = modal.render();
        if(result == null)
            return;

        try (var cache = new TreeViewCache<>(categoryTree); cache) {
            var category = financialCategoryController.create(parentCategory, result.getName(), user);
            cache.setLastSelected(category);
        } catch (Exception e) {
            var error = DatabaseExceptionPolicy.apply(e);
            if (error == null)
                throw e;

            getView().getErrorRouter().route(error);
        } finally {
            updateTree();
        }

    }


    @FXML
    private void onUpdate() throws IOException {
        var category = getSelectedItem();
        if(category == null)
            return;

        var modal = new Modal<>(new CategoryModalController(getInitialResult(category)), getView(), "Update Category", "categoryModal.fxml");
        var result = modal.render();

        if(result == null)
            return;

        try (var cache = new TreeViewCache<>(categoryTree)) {
            financialCategoryController.update(category, category.getParent(), result.getName(), user);
            updateTree();
        }
    }

    @FXML
    public void onDelete() {
        var selectedNode = categoryTree.getSelectionModel().getSelectedItem();
        if(selectedNode == null || selectedNode == treeRoot)
            return;

        var category = selectedNode.getValue();

        try (var cache = new TreeViewCache<>(categoryTree)) {
            financialCategoryController.remove(category);
            updateTree();
        } catch (Exception e) {
            var error = DatabaseExceptionPolicy.apply(e);
            if (error == null)
                throw e;

            getView().getErrorRouter().route(error);
        } finally {
            updateTree();
        }
    }

    @FXML
    public void onBack() throws IOException {
        this.previousView.render();
    }

    @FXML
    public void onExpand() {
        setExpansion(treeRoot, true);
    }

    @FXML
    public void onContract() {
        setExpansion(treeRoot, false);
    }

    private void setExpansion(TreeItem<FinancialCategory> item, boolean isExpanded) {
        if(item == null)
            return;

        if(item.getParent() != null)
            item.setExpanded(isExpanded);

        for(var child: item.getChildren()){
            setExpansion(child, isExpanded);
        }
    }

    private void updateTree() {
        var categoryMap = new HashMap<Integer, TreeItem<FinancialCategory>>();
        var categories = financialCategoryController.find_User(user);

        treeRoot.getChildren().clear();

        for (var category : categories) {
            var categoryNode = new TreeItem<>(category);

            if(category.getParent() == null || !categoryMap.containsKey(category.getParent().getId())) {
                treeRoot.getChildren().add(categoryNode);
            } else {
                var parentCategoryNode = categoryMap.get(category.getParent().getId());
                parentCategoryNode.getChildren().add(categoryNode);
            }

            categoryMap.put(category.getId(), categoryNode);
        }

    }


    private boolean filterUserOwnedCategories(FinancialCategory category) {
        return Objects.equals(category.getOwner(), user) || category.getResponsibleUsers().contains(user);
    }

    private TreeItem<FinancialCategory> getSelectedNode() {
        return categoryTree.getSelectionModel().getSelectedItem();
    }

    private FinancialCategory getSelectedItem() {
        var node = getSelectedNode();
        if(node == null)
            return null;
        return node.getValue();
    }

    private CategoryModalResult getInitialResult(FinancialCategory item) {
        if(item == null)
            return null;

        var initialResult = new CategoryModalResult();
        initialResult.setName(item.getName());
        return initialResult;
    }

    public void onResponsible() throws IOException {
        var selected = getSelectedItem();
        if(selected == null)
            return;

        var controller = new ResponsibleUserCRUDListController(getView(), selected, user, getSession());
        var view = new View(controller, getStage(), "Responsible Users", "listcrud.fxml");
        view.setErrorRouter(getView().getErrorRouter());
        view.render();
    }

    public void onExpense() throws IOException {
        var selected = getSelectedItem();
        if(selected == null)
            return;

        var controller = new ExpenseCRUDListController(getView(), user,  selected, getSession());
        var view = new View(controller, getStage(), "Expenses", "flowcrud.fxml");
        view.setErrorRouter(getView().getErrorRouter());
        view.render();
    }

    public void onIncome() throws IOException {
        var selected = getSelectedItem();
        if(selected == null)
            return;

        var controller = new IncomeCRUDListController(getView(), user, selected, getSession());
        var view = new View(controller, getStage(), "Incomes", "flowcrud.fxml");
        view.setErrorRouter(getView().getErrorRouter());
        view.render();
    }

    private final User user;
    private final View previousView;
    private final TreeItem<FinancialCategory> treeRoot;
    private final FinancialCategoryService financialCategoryController;

    @FXML
    private TreeView<FinancialCategory> categoryTree;

    @FXML
    private Label labelOwner;

    @FXML
    private Button buttonResponsible;

    @FXML
    private Button buttonExpense;

    @FXML
    private Button buttonIncome;
}
