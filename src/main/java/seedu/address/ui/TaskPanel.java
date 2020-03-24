package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.task.Task;

/**
 * Panel containing the list of tasks for a module.
 */
public class TaskPanel extends UiPart<Region> {

    private static final String FXML = "TaskPanel.fxml";

    @FXML
    private ListView<Task> tasksView;

    public TaskPanel(ObservableList<Task> taskList) {
        super(FXML);
        tasksView.setItems(taskList);
        tasksView.setCellFactory(listView -> new TaskPanel.TaskListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code TaskCard}.
     */
    class TaskListViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TaskCard(task, getIndex() + 1).getRoot());
            }
        }
    }

}
