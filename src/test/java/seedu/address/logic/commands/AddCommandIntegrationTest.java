package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFacilitators.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.testutil.FacilitatorBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newFacilitator_success() {
        Facilitator validFacilitator = new FacilitatorBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addFacilitator(validFacilitator);

        assertCommandSuccess(new AddCommand(validFacilitator), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validFacilitator), expectedModel);
    }

    @Test
    public void execute_duplicateFacilitator_throwsCommandException() {
        Facilitator facilitatorInList = model.getAddressBook().getFacilitatorList().get(0);
        assertCommandFailure(new AddCommand(facilitatorInList), model, AddCommand.MESSAGE_DUPLICATE_FACILITATOR);
    }

}
