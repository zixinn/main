package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.task.Task;
import seedu.address.model.util.DailySchedulableInterface;

/**
 * An UI component that displays information of a class or task.
 */
public class CalendarCard extends UiPart<Region> {

    private static final String FXML = "CalendarCard.fxml";

    @FXML
    private StackPane cardPane;

    @FXML
    private VBox card;

    @FXML
    private Label description;

    @FXML
    private Label time;

    /**
     * Constructs a card for Schedulable item.
     */
    public CalendarCard(DailySchedulableInterface item) {
        super(FXML);

        if (item instanceof Task) {
            initTaskCard((Task) item);
        }

        if (item instanceof Lesson) {
            initLessonCard((Lesson) item);
        }
    }

    /**
     * Initializes a Calendar Card for a task
     */
    private void initTaskCard(Task task) {
        description.setText(task.getDescription());
        if (!task.getTimeOutput().equals("")) {
            time.setText(task.getTimeOutput());
        }
        card.setStyle("-fx-background-color: #ffffd1; -fx-background-radius: 8;");
    }

    /**
     * Initializes a Calendar Card for a lesson
     */
    private void initLessonCard(Lesson lesson) {
        String code = lesson.getModuleCode().toString();
        switch (lesson.getType()) {
        case LAB:
            description.setText(String.format("%s LAB", code));
            break;
        case REC:
            description.setText(String.format("%s REC", code));
            break;
        case LEC:
            description.setText(String.format("%s LEC", code));
            break;
        case SEC:
            description.setText(String.format("%s SEC", code));
            break;
        case TUT:
            description.setText(String.format("%s TUT", code));
            break;
        default:
            break;
        }
        time.setText(lesson.getStartTime().toString() + " - " + lesson.getEndTime().toString());
    }
    //
    //    /**
    //     * Constructs a card for task.
    //     */
    //    public CalendarCard(Task task) {
    //        super(FXML);
    //        description.setText(task.getDescription());
    //        if (!task.getTimeOutput().equals("")) {
    //            time.setText(task.getTimeOutput());
    //        }
    //        card.setStyle("-fx-background-color: #ffffd1; -fx-background-radius: 8;");
    //    }
    //
    //    /**
    //     * Constructs a card for lesson.
    //     */
    //    public CalendarCard(Lesson lesson) {
    //        super(FXML);
    //        String code = lesson.getModuleCode().toString();
    //        switch (lesson.getType()) {
    //        case LAB:
    //            description.setText(String.format("%s LAB", code));
    //            break;
    //        case REC:
    //            description.setText(String.format("%s REC", code));
    //            break;
    //        case LEC:
    //            description.setText(String.format("%s LEC", code));
    //            break;
    //        case SEC:
    //            description.setText(String.format("%s SEC", code));
    //            break;
    //        case TUT:
    //            description.setText(String.format("%s TUT", code));
    //            break;
    //        default:
    //            break;
    //        }
    //        time.setText(lesson.getStartTime().toString() + " - " + lesson.getEndTime().toString());
    //    }


}
