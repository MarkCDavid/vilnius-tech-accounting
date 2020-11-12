package vilnius.tech.utils;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class GUIUtils {

    public static <T> void autoResizeColumns(TableView<T> table)
    {
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        table.getColumns().forEach(column -> {
            var text = new Text(column.getText());
            var maxWidth = text.getLayoutBounds().getWidth();
            for (int i = 0; i < table.getItems().size(); i++)
            {
                if (column.getCellData( i ) != null)
                {
                    text = new Text(column.getCellData( i ).toString());
                    var calculatedWidth = text.getLayoutBounds().getWidth();
                    if (calculatedWidth > maxWidth)
                    {
                        maxWidth = calculatedWidth;
                    }
                }
            }
            column.setPrefWidth(maxWidth + 10.0d);
        });
    }

    public static <T> TableColumn<T, String> createColumn(String header, String fieldName) {
        var tableColumn = new TableColumn<T, String>();
        tableColumn.setText(header);
        tableColumn.setCellValueFactory(row -> {
            var value = row.getValue();
            var field = ReflectionUtils.extractField(value, fieldName);
            if(field == null) {
                return new ReadOnlyStringWrapper("");
            }

            return new ReadOnlyStringWrapper(ReflectionUtils.extractString(value, field));
        });
        return tableColumn;
    }

    private GUIUtils() { }
}