package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * A ui for the module details bar that is displayed at the top of the modules tab.
 */
public class TaskDetailsPanel extends UiPart<Region> {

    private static final String FXML = "TaskDetailsPanel.fxml";

    @FXML
    private Label setTaskDetails;

    public TaskDetailsPanel(String string) {
        super(FXML);
        setTaskDetails.setText(string);
        GridPane.setHalignment(setTaskDetails, HPos.CENTER);
    }

    public void changeDisplayModule(String string) {
        setTaskDetails.setText(string);
    }

}
