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

    public ModuleDetailsPanel(Optional<Module> module) {
        super(FXML);
        if (module.isEmpty()) {
            moduleDetails.setText("");
        } else {
            moduleDetails.setText(module.get().getModuleCode().moduleCode + " "
                    + module.get().getDescription().value);
        }
        GridPane.setHalignment(moduleDetails, HPos.CENTER);
    }

    public void changeDisplayModule(String string) {
        moduleDetails.setText(string);
    }
}
