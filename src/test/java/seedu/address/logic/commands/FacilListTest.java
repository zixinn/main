package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showFacilitatorAtIndex;
import static seedu.address.testutil.TypicalFacilitators.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_FACILITATOR;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.facil.FacilList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class FacilListTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new FacilList(), model, FacilList.MESSAGE_SUCCESS, CommandType.FACILITATOR,
                expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showFacilitatorAtIndex(model, INDEX_FIRST_FACILITATOR);
        assertCommandSuccess(new FacilList(), model, FacilList.MESSAGE_SUCCESS, CommandType.FACILITATOR,
                expectedModel);
    }
}
