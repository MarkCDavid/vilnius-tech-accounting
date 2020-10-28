package vilnius.tech.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import vilnius.tech.controller.crud.FinancialCategoryCU;
import vilnius.tech.controller.modal.CategoryModalController;
import vilnius.tech.controller.modal.result.CategoryModalResult;
import vilnius.tech.dal.FinancialCategory;
import vilnius.tech.dal.User;
import vilnius.tech.session.Session;
import vilnius.tech.utils.TreeViewCache;
import vilnius.tech.view.Modal;
import vilnius.tech.view.View;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;

public class CategoryController extends SessionController {

    public CategoryController(View previousView, User user, Session session) {
        super(session);
        this.user = user;
        this.previousView = previousView;
        this.treeRoot = new TreeItem<>();
    }

    @FXML
    public void initialize() {
        categoryTree.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            System.out.println(newValue);
        });

        this.categoryTree.setRoot(treeRoot);
        updateTree();
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

        FinancialCategoryCU.create(getSession(), parentCategory, result.getName(), user);
        updateTree();
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

        FinancialCategoryCU.update(category, category.getParent(), result.getName(), user);
        updateTree();
    }

    @FXML
    public void onDelete() {
        var selectedNode = categoryTree.getSelectionModel().getSelectedItem();
        if(selectedNode == null || selectedNode == treeRoot)
            return;

        var category = selectedNode.getValue();
        category.delete();
        updateTree();
    }

    @FXML
    public void onBack() throws IOException {
        this.previousView.render();
    }

    private void updateTree() {
        var categoryMap = new HashMap<Integer, TreeItem<FinancialCategory>>();
        var categories = getSession().query(FinancialCategory.class, this::filterUserOwnedCategories);
        categories.sort(Comparator.comparing(FinancialCategory::getOid));

        var expansionCache = new TreeViewCache<FinancialCategory>();
        expansionCache.save(categoryTree);

        treeRoot.getChildren().clear();

        for (var category : categories) {
            var categoryNode = new TreeItem<>(category);

            if(category.getParent() == null || !categoryMap.containsKey(category.getParent().getOid())) {
                treeRoot.getChildren().add(categoryNode);
            } else {
                var parentCategoryNode = categoryMap.get(category.getParent().getOid());
                parentCategoryNode.getChildren().add(categoryNode);
            }

            categoryMap.put(category.getOid(), categoryNode);
        }

        expansionCache.restore(categoryTree);
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

    private final User user;
    private final View previousView;
    private final TreeItem<FinancialCategory> treeRoot;

    @FXML
    private TreeView<FinancialCategory> categoryTree;
}
