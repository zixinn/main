package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFacilitators.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.facil.FacilAdd;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.testutil.FacilitatorBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code FacilAdd}.
 */
public class FacilAddIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newFacilitator_success() {
        Facilitator validFacilitator = new FacilitatorBuilder().withName(VALID_NAME_AMY).build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addFacilitator(validFacilitator);

        assertCommandSuccess(new FacilAdd(validFacilitator), model,
                String.format(FacilAdd.MESSAGE_SUCCESS, validFacilitator), CommandType.FACILITATOR, expectedModel);
    }

    @Test
    public void execute_duplicateFacilitator_throwsCommandException() {
        Facilitator facilitatorInList = model.getAddressBook().getFacilitatorList().get(0);
        assertCommandFailure(new FacilAdd(facilitatorInList), model, FacilAdd.MESSAGE_DUPLICATE_FACILITATOR);
    }

}
