package seedu.address.logic.commands.module;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModManager;
import seedu.address.model.ReadOnlyModManager;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.model.module.Module;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.ModuleBuilder;

public class ModuleAddCommandTest {

    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleAddCommand(null));
    }

    @Test
    public void execute_moduleAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingModuleAdded modelStub = new ModelStubAcceptingModuleAdded();
        Module validModule = new ModuleBuilder().build();

        CommandResult commandResult = new ModuleAddCommand(validModule).execute(modelStub);

        assertEquals(String.format(ModuleAddCommand.MESSAGE_SUCCESS, validModule),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validModule), modelStub.modulesAdded);
    }

    @Test
    public void execute_duplicateModule_throwsCommandException() {
        Module validModule = new ModuleBuilder().build();
        ModuleAddCommand moduleAddCommand = new ModuleAddCommand(validModule);
        ModelStub modelStub = new ModelStubWithModule(validModule);

        assertThrows(CommandException.class,
                ModuleAddCommand.MESSAGE_DUPLICATE_MODULE, () -> moduleAddCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Module cs2103t = new ModuleBuilder().withModuleCode("CS2103T").build();
        Module cs2101 = new ModuleBuilder().withModuleCode("CS2101").build();
        ModuleAddCommand addCs2103tCommand = new ModuleAddCommand(cs2103t);
        ModuleAddCommand addCs2101Command = new ModuleAddCommand(cs2101);

        // same object -> returns true
        assertTrue(addCs2103tCommand.equals(addCs2103tCommand));

        // same values -> returns true
        ModuleAddCommand addCs2103tCommandCopy = new ModuleAddCommand(cs2103t);
        assertTrue(addCs2103tCommand.equals(addCs2103tCommandCopy));

        // different types -> returns false
        assertFalse(addCs2103tCommand.equals(1));

        // null -> returns false
        assertFalse(addCs2103tCommand.equals(null));

        // different module code -> returns false
        assertFalse(addCs2103tCommand.equals(addCs2101Command));
    }

    /**
     * A Model stub that contains a single module.
     */
    private class ModelStubWithModule extends ModelStub {
        private final Module module;

        ModelStubWithModule(Module module) {
            requireNonNull(module);
            this.module = module;
        }

        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return this.module.isSameModule(module);
        }
    }

    /**
     * A Model stub that always accept the module being added.
     */
    private class ModelStubAcceptingModuleAdded extends ModelStub {
        final ArrayList<Module> modulesAdded = new ArrayList<>();

        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return modulesAdded.stream().anyMatch(module::isSameModule);
        }

        @Override
        public void addModule(Module module) {
            requireNonNull(module);
            modulesAdded.add(module);
        }

        @Override
        public void updateFacilitatorListForModule(Predicate<Facilitator> predicate) {
            //empty method body
        }

        @Override
        public ReadOnlyModManager getModManager() {
            return new ModManager();
        }
    }
}
