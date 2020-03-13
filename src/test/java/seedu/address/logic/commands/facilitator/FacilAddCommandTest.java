package seedu.address.logic.commands.facilitator;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.model.module.Module;
import seedu.address.testutil.FacilitatorBuilder;

public class FacilAddCommandTest {

    @Test
    public void constructor_nullFacilitator_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FacilAddCommand(null));
    }

    @Test
    public void execute_facilitatorAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingFacilitatorAdded modelStub = new ModelStubAcceptingFacilitatorAdded();
        Facilitator validFacilitator = new FacilitatorBuilder().build();

        CommandResult commandResult = new FacilAddCommand(validFacilitator).execute(modelStub);

        assertEquals(String.format(FacilAddCommand.MESSAGE_SUCCESS, validFacilitator),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validFacilitator), modelStub.facilitatorsAdded);
    }

    @Test
    public void execute_duplicateFacilitator_throwsCommandException() {
        Facilitator validFacilitator = new FacilitatorBuilder().build();
        FacilAddCommand facilAddCommand = new FacilAddCommand(validFacilitator);
        ModelStub modelStub = new ModelStubWithFacilitator(validFacilitator);

        assertThrows(CommandException.class,
                FacilAddCommand.MESSAGE_DUPLICATE_FACILITATOR, () -> facilAddCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Facilitator alice = new FacilitatorBuilder().withName("Alice").build();
        Facilitator bob = new FacilitatorBuilder().withName("Bob").build();
        FacilAddCommand addAliceCommand = new FacilAddCommand(alice);
        FacilAddCommand addBobCommand = new FacilAddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        FacilAddCommand addAliceCommandCopy = new FacilAddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different facilitator -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteModule(Module target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModule(Module target, Module editedModule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredModuleList(Predicate<Module> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasFacilitator(Facilitator facilitator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteFacilitator(Facilitator target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addFacilitator(Facilitator facilitator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFacilitator(Facilitator target, Facilitator editedFacilitator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Facilitator> getFilteredFacilitatorList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredFacilitatorList(Predicate<Facilitator> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single facilitator.
     */
    private class ModelStubWithFacilitator extends ModelStub {
        private final Facilitator facilitator;

        ModelStubWithFacilitator(Facilitator facilitator) {
            requireNonNull(facilitator);
            this.facilitator = facilitator;
        }

        @Override
        public boolean hasFacilitator(Facilitator facilitator) {
            requireNonNull(facilitator);
            return this.facilitator.isSameFacilitator(facilitator);
        }
    }

    /**
     * A Model stub that always accept the facilitator being added.
     */
    private class ModelStubAcceptingFacilitatorAdded extends ModelStub {
        final ArrayList<Facilitator> facilitatorsAdded = new ArrayList<>();

        @Override
        public boolean hasFacilitator(Facilitator facilitator) {
            requireNonNull(facilitator);
            return facilitatorsAdded.stream().anyMatch(facilitator::isSameFacilitator);
        }

        @Override
        public void addFacilitator(Facilitator facilitator) {
            requireNonNull(facilitator);
            facilitatorsAdded.add(facilitator);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
