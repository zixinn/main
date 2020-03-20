package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.module.Module;

/**
 * An UI component that displays information of a {@code Module}.
 */
public class ModuleCard extends UiPart<Region> {
    private static final String FXML = "ModuleListCard.fxml";

    public final Module module;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label moduleCode;
    @FXML
    private FlowPane description;

    public ModuleCard(Module module, int displayedIndex) {
        super(FXML);
        this.module = module;
        id.setText(displayedIndex + ". ");
        moduleCode.setText(module.getModuleCode().value);

        if (module.getDescription().value != null) {
            description.getChildren().add(new Label(module.getDescription().value));
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleCard)) {
            return false;
        }

        // state check
        ModuleCard card = (ModuleCard) other;
        return id.getText().equals(card.id.getText())
                && module.equals(card.module);
    }
}
