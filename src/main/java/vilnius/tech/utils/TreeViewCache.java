package vilnius.tech.utils;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import vilnius.tech.hibernate.BaseEntity;

import java.util.HashMap;

public class TreeViewCache<T extends BaseEntity> implements AutoCloseable {

    public TreeViewCache() {
        this(null);
    }

    public TreeViewCache(TreeView<T> treeView) {
        this.treeView = treeView;
        if(this.treeView != null)
            save(this.treeView);
    }

    @Override
    public void close() {
        if(this.treeView != null)
            restore(this.treeView);
    }

    public void save(TreeView<T> treeView) {
        expansionCache.clear();

        saveCore(treeView.getRoot());
        saveLastSelected(treeView);
    }

    private void saveCore(TreeItem<T> item) {
        if(item.getValue() != null)
            expansionCache.put(getKey(item), item.isExpanded());

        for(var child: item.getChildren()){
            saveCore(child);
        }
    }


    public void restore(TreeView<T> treeView) {
        treeView.getRoot().setExpanded(true);
        restoreCore(treeView.getRoot());
        restoreLastSelected(treeView);
    }

    private void restoreCore(TreeItem<T> item) {
        if(expansionCache.containsKey(getKey(item)))
            item.setExpanded(expansionCache.get(getKey(item)));

        for(var child: item.getChildren()){
            restoreCore(child);
        }
    }

    private void restoreLastSelected(TreeView<T> treeView) {
        var lastSelectedNode = getLastSelected(treeView.getRoot());
        if(lastSelectedNode != null)
            treeView.getSelectionModel().select(lastSelectedNode);
    }

    private void saveLastSelected(TreeView<T> treeView) {
        var selectedNode = treeView.getSelectionModel().getSelectedItem();
        if(selectedNode == null)
            lastSelected = null;
        else
            lastSelected = selectedNode.getValue();
    }

    private TreeItem<T> getLastSelected(TreeItem<T> item) {
        if(item.getValue() == lastSelected)
            return item;

        for(var child: item.getChildren()){
             item = getLastSelected(child);
             if(item != null)
                 return item;
        }

        return null;
    }


    public void setLastSelected(T lastSelected) {
        this.lastSelected = lastSelected;
    }

    private String getKey(T item) {
        if(item == null)
            return "";
        return String.format("%s%s", item.getClass().getName(), item.getId());
    }

    private String getKey(TreeItem<T> item) {
        return getKey(item.getValue());
    }


    private T lastSelected = null;
    private final TreeView<T> treeView;
    private final HashMap<String, Boolean> expansionCache = new HashMap<>();
}
