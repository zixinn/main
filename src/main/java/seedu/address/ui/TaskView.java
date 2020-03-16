package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;


/**
 * Panel containing the list of lessons.
 */
public class TaskView extends UiPart<Region> {
    private static final String FXML = "TaskView.fxml";

    @FXML
    private Label description;

    public TaskView() {
        super(FXML);
        description.setText("Classes: ");
        description.setPadding(new Insets(5, 5, 5, 20));
    }

    public void updateTaskView() {
        description.setText("\u2022 lessons .... ");
    }
}
