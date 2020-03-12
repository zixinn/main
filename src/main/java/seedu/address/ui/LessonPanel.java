package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;


/**
 * Panel containing the list of lessons.
 */
public class LessonPanel extends UiPart<Region> {
    private static final String FXML = "LessonPanel.fxml";

    @FXML
    private Label title;

    @FXML
    private Label lessons;

    public LessonPanel() {
        super(FXML);
        title.setText("Classes: ");
        title.setPadding(new Insets(5, 5, 5, 20));
        lessons.setPadding(new Insets(0, 5, 10, 50));
    }

    public void updateLessonPanel() {
        lessons.setText("\u2022 lessons .... ");
    }
}
