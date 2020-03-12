package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * A ui for the module details bar that is displayed at the top of the modules tab.
 */
public class ModuleDetailsPanel extends UiPart<Region> {

    private static final String FXML = "ModuleDetailsPanel.fxml";

    @FXML
    private Label setModuleDetails;


    public ModuleDetailsPanel(String string) {
        super(FXML);
        setModuleDetails.setText(string);
        GridPane.setHalignment(setModuleDetails, HPos.CENTER);
    }

    public void changeDisplayModule(String string) {
        setModuleDetails.setText(string);
    }

}
