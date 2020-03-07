package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.facilitator.Facilitator;

/**
 * An UI component that displays information of a {@code Facilitator}.
 */
public class FacilitatorCard extends UiPart<Region> {

    private static final String FXML = "FacilitatorListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Facilitator facilitator;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;

    public FacilitatorCard(Facilitator facilitator, int displayedIndex) {
        super(FXML);
        this.facilitator = facilitator;
        id.setText(displayedIndex + ". ");
        name.setText(facilitator.getName().fullName);
        phone.setText(facilitator.getPhone().value);
        address.setText(facilitator.getAddress().value);
        email.setText(facilitator.getEmail().value);
        facilitator.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FacilitatorCard)) {
            return false;
        }

        // state check
        FacilitatorCard card = (FacilitatorCard) other;
        return id.getText().equals(card.id.getText())
                && facilitator.equals(card.facilitator);
    }
}
