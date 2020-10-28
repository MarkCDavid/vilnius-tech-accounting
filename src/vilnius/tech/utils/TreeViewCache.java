package vilnius.tech.utils;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.HashMap;

public class TreeViewCache<T> {

    public void save(TreeView<T> treeView) {
        expansionCache.clear();

        rootExpanded = treeView.getRoot().isExpanded();
        saveCore(treeView.getRoot());
        saveLastSelected(treeView);
    }

    private void saveCore(TreeItem<T> item) {
        if(item.getValue() != null)
            expansionCache.put(item.getValue(), item.isExpanded());

        for(var child: item.getChildren()){
            saveCore(child);
        }
    }


    public void restore(TreeView<T> treeView) {
        treeView.getRoot().setExpanded(rootExpanded);
        restoreCore(treeView.getRoot());
        restoreLastSelected(treeView);
    }

    private void restoreCore(TreeItem<T> item) {
        if(expansionCache.containsKey(item.getValue()))
            item.setExpanded(expansionCache.get(item.getValue()));

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


    private boolean rootExpanded = false;
    private T lastSelected = null;

    private final HashMap<T, Boolean> expansionCache = new HashMap<>();
}
