package seedu.address.ui;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seedu.address.model.module.Module;

/**
 * A ui for the module details bar that is displayed at the top of the module tab.
 */
public class ModuleDetailsPanel extends UiPart<Region> {
    private static final String FXML = "ModuleDetailsPanel.fxml";

    @FXML
    private Label moduleDetails;

    public ModuleDetailsPanel() {
        super(FXML);
        moduleDetails.setText("");
        GridPane.setHalignment(moduleDetails, HPos.CENTER);
    }

    /**
     * Sets the module code and description of the module to be displayed.
     */
    public void changeDisplayModule(Optional<Module> module) {
        module.ifPresent(value -> moduleDetails.setText(value.getModuleCode().value + " "
                + value.getDescription().value));
    }
}
