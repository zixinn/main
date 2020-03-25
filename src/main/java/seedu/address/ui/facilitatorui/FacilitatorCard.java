package seedu.address.ui.facilitatorui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.ui.UiPart;

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
    private FlowPane phone;
    @FXML
    private FlowPane email;
    @FXML
    private FlowPane office;
    @FXML
    private FlowPane moduleCodes;

    public FacilitatorCard(Facilitator facilitator, int displayedIndex) {
        super(FXML);
        this.facilitator = facilitator;
        id.setText(displayedIndex + ". ");
        name.setText(facilitator.getName().fullName);

        if (facilitator.getPhone().value != null) {
            phone.getChildren().add(new Label(facilitator.getPhone().value));
        }

        if (facilitator.getEmail().value != null) {
            email.getChildren().add(new Label(facilitator.getEmail().value));
        }

        if (facilitator.getOffice().value != null) {
            office.getChildren().add(new Label(facilitator.getOffice().value));
        }

        facilitator.getModuleCodes().stream()
                .sorted(Comparator.comparing(moduleCode -> moduleCode.value))
                .forEach(moduleCode -> moduleCodes.getChildren().add(new Label(moduleCode.value)));
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
