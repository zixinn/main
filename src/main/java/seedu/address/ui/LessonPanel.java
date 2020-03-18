package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.lesson.Lesson;


/**
 * Panel containing the list of lessons.
 */
public class LessonPanel extends UiPart<Region> {
    private static final String FXML = "LessonPanel.fxml";

    @FXML
    private Label title;

    @FXML
    private ListView<Lesson> lessons;

    public LessonPanel() {
        super(FXML);
    }

    public void updateLessonPanel() {
        //lessons.setText("\u2022 lessons .... ");
    }
}
