package seedu.address.logic.commands.facilitator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OFFICE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showFacilitatorAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalFacilitators.IDA;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.facilitator.FacilEditCommand.EditFacilitatorDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.testutil.EditFacilitatorDescriptorBuilder;
import seedu.address.testutil.FacilitatorBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand)
 * and unit tests for {@code FacilEditCommand}.
 */
public class FacilEditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Facilitator editedFacilitator = new FacilitatorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withOffice(VALID_OFFICE_BOB).build();
        EditFacilitatorDescriptor descriptor = new EditFacilitatorDescriptorBuilder(editedFacilitator).build();
        FacilEditCommand editCommand = new FacilEditCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(FacilEditCommand.MESSAGE_EDIT_FACILITATOR_SUCCESS, editedFacilitator);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setFacilitator(model.getFilteredFacilitatorList().get(0), editedFacilitator);

        assertCommandSuccess(editCommand, model, expectedMessage, CommandType.FACILITATOR, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastFacilitator = Index.fromOneBased(model.getFilteredFacilitatorList().size());
        Facilitator lastFacilitator = model.getFilteredFacilitatorList().get(indexLastFacilitator.getZeroBased());

        FacilitatorBuilder facilitatorInList = new FacilitatorBuilder(lastFacilitator);
        Facilitator editedFacilitator = facilitatorInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withModuleCodes(VALID_MODULE_CODE_CS2103T).build();

        EditFacilitatorDescriptor descriptor = new EditFacilitatorDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withModuleCodes(VALID_MODULE_CODE_CS2103T).build();
        FacilEditCommand editCommand = new FacilEditCommand(indexLastFacilitator, descriptor);

        String expectedMessage = String.format(FacilEditCommand.MESSAGE_EDIT_FACILITATOR_SUCCESS, editedFacilitator);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setFacilitator(lastFacilitator, editedFacilitator);

        assertCommandSuccess(editCommand, model, expectedMessage, CommandType.FACILITATOR, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        FacilEditCommand editCommand = new FacilEditCommand(INDEX_FIRST,
                new FacilEditCommand.EditFacilitatorDescriptor());
        Facilitator editedFacilitator = model.getFilteredFacilitatorList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(FacilEditCommand.MESSAGE_EDIT_FACILITATOR_SUCCESS, editedFacilitator);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, CommandType.FACILITATOR, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showFacilitatorAtIndex(model, INDEX_FIRST);

        Facilitator facilitatorInFilteredList = model.getFilteredFacilitatorList()
                .get(INDEX_FIRST.getZeroBased());
        Facilitator editedFacilitator = new FacilitatorBuilder(facilitatorInFilteredList).withName(VALID_NAME_BOB)
                .build();
        FacilEditCommand editCommand = new FacilEditCommand(INDEX_FIRST,
                new EditFacilitatorDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(FacilEditCommand.MESSAGE_EDIT_FACILITATOR_SUCCESS, editedFacilitator);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setFacilitator(model.getFilteredFacilitatorList().get(0), editedFacilitator);

        assertCommandSuccess(editCommand, model, expectedMessage, CommandType.FACILITATOR, expectedModel);
    }

    @Test
    public void execute_invalidFacilitatorIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFacilitatorList().size() + 1);
        FacilEditCommand.EditFacilitatorDescriptor descriptor = new EditFacilitatorDescriptorBuilder()
                .withName(VALID_NAME_BOB).build();
        FacilEditCommand editCommand = new FacilEditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_FACILITATOR_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of facilitator list
     */
    @Test
    public void execute_invalidFacilitatorIndexFilteredList_failure() {
        showFacilitatorAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getFacilitatorList().size());

        FacilEditCommand editCommand = new FacilEditCommand(outOfBoundIndex,
                new EditFacilitatorDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_FACILITATOR_DISPLAYED_INDEX);
    }

    @Test
    public void execute_duplicateFacilitatorUnfilteredList_failure() {
        Facilitator firstFacilitator = model.getFilteredFacilitatorList().get(INDEX_FIRST.getZeroBased());
        EditFacilitatorDescriptor descriptor = new EditFacilitatorDescriptorBuilder(firstFacilitator).build();
        FacilEditCommand editCommand = new FacilEditCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editCommand, model, FacilEditCommand.MESSAGE_DUPLICATE_FACILITATOR);
    }

    @Test
    public void execute_duplicateFacilitatorFilteredList_failure() {
        showFacilitatorAtIndex(model, INDEX_FIRST);

        // edit facilitator in filtered list into a duplicate in mod manager
        Facilitator facilitatorInList = model.getAddressBook().getFacilitatorList().get(INDEX_SECOND
                .getZeroBased());
        FacilEditCommand editCommand = new FacilEditCommand(INDEX_FIRST,
                new EditFacilitatorDescriptorBuilder(facilitatorInList).build());

        assertCommandFailure(editCommand, model, FacilEditCommand.MESSAGE_DUPLICATE_FACILITATOR);
    }

    @Test
    public void execute_allOptionalFieldsDeletedUnfilteredList_failure() {
        Facilitator editedFacilitator = new FacilitatorBuilder().build();
        EditFacilitatorDescriptor descriptor = new EditFacilitatorDescriptorBuilder(editedFacilitator).build();
        FacilEditCommand editCommand = new FacilEditCommand(INDEX_FIRST, descriptor);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setFacilitator(model.getFilteredFacilitatorList().get(0), editedFacilitator);

        assertCommandFailure(editCommand, model, FacilEditCommand.MESSAGE_ALL_OPTIONAL_FIELDS_DELETED);
    }

    @Test
    public void execute_allOptionalFieldsDeletedFilteredList_failure() {
        showFacilitatorAtIndex(model, INDEX_FIRST);

        Facilitator facilitatorInFilteredList = model.getFilteredFacilitatorList()
                .get(INDEX_FIRST.getZeroBased());
        Facilitator editedFacilitator = new FacilitatorBuilder(facilitatorInFilteredList).withPhone(null)
                .withEmail(null).withOffice(null).withModuleCodes().build();
        FacilEditCommand editCommand = new FacilEditCommand(INDEX_FIRST,
                new EditFacilitatorDescriptorBuilder().withName(VALID_NAME_BOB).withPhone(null)
                        .withEmail(null).withOffice(null).withModuleCodes().build());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setFacilitator(model.getFilteredFacilitatorList().get(0), editedFacilitator);

        assertCommandFailure(editCommand, model, FacilEditCommand.MESSAGE_ALL_OPTIONAL_FIELDS_DELETED);
    }

    @Test
    public void execute_moduleDoesNotExistUnfilteredList_failure() {
        Facilitator editedFacilitator = new FacilitatorBuilder(IDA).withModuleCodes("CS1231").build();
        EditFacilitatorDescriptor descriptor = new EditFacilitatorDescriptorBuilder(editedFacilitator).build();
        FacilEditCommand editCommand = new FacilEditCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editCommand, model,
                String.format(FacilEditCommand.MESSAGE_MODULE_DOES_NOT_EXIST, "CS1231"));
    }

    @Test
    public void execute_moduleDoesNotExistFilteredList_failure() {
        showFacilitatorAtIndex(model, INDEX_FIRST);

        Facilitator editedFacilitator = new FacilitatorBuilder(IDA).withModuleCodes("CS1231").build();
        FacilEditCommand editCommand = new FacilEditCommand(INDEX_FIRST,
                new EditFacilitatorDescriptorBuilder(editedFacilitator).build());

        assertCommandFailure(editCommand, model,
                String.format(FacilEditCommand.MESSAGE_MODULE_DOES_NOT_EXIST, "CS1231"));
    }

    @Test
    public void equals() {
        final FacilEditCommand standardCommand = new FacilEditCommand(INDEX_FIRST, DESC_AMY);

        // same values -> returns true
        EditFacilitatorDescriptor copyDescriptor = new EditFacilitatorDescriptor(DESC_AMY);
        FacilEditCommand commandWithSameValues = new FacilEditCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new FacilEditCommand(INDEX_SECOND, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new FacilEditCommand(INDEX_FIRST, DESC_BOB)));
    }

}
