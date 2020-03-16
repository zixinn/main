package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Panel to contain the list of cards of tasks and classes.
 */
public class CalendarCardPanel extends UiPart<Region> {

    private static final String FXML = "CalendarCardPanel.fxml";

    @FXML
    private VBox cardHolder;

    private int num = 0;

    /**
     * Constructs the CalendarCardPanel.
     */
    public CalendarCardPanel() {
        super(FXML);
    }

    /**
     * Adds a class or task card to the panel.
     *
     * @param card the class or task card.
     */
    public void addCard(CalendarCard card) {
        cardHolder.getChildren().add(card.getRoot());
        num++;
    }

    /**
     * Clears the cards for a new view.
     */
    public void clearCards() {
        cardHolder.getChildren().clear();
    }

    public double getHeight() {
        return num * 80;
    }
}
