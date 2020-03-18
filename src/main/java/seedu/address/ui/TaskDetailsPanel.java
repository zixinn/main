package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.task.Task;

/**
 * A ui for the module details bar that is displayed at the top of the modules tab.
 */
public class TaskDetailsPanel extends UiPart<Region> {

    private static final String FXML = "TaskDetailsPanel.fxml";

    @FXML
    private Label title;

    @FXML
    private ListView<Task> tasksView;

    public TaskDetailsPanel() {
        super(FXML);
    }

    public void changeDisplayModule(String string) {
//        setTaskDetails.setText(string);
    }

}
