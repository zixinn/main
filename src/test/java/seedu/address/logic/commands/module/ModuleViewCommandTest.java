package seedu.address.logic.commands.module;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFacilitators.ALICE;
import static seedu.address.testutil.TypicalModules.CS2103T;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.ModelStub;

public class ModuleViewCommandTest {

    @Test
    public void constructor_nullModuleCode_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleViewCommand(null));
    }

    @Test
    public void execute_moduleCodeExist_success() throws Exception {
        ModuleViewCommandTest.ModelStubWithModule modelStub =
                new ModuleViewCommandTest.ModelStubWithModule(CS2103T, ALICE);
        ModuleCode validModuleCode = CS2103T.getModuleCode();

        CommandResult commandResult = new ModuleViewCommand(validModuleCode).execute(modelStub);

        assertEquals(String.format(ModuleViewCommand.MESSAGE_SUCCESS, validModuleCode),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(ALICE), modelStub.facilitatorList);
    }

    @Test
    public void execute_moduleCodeDoesNotExist_throwsCommandException() {
        ModuleCode validModuleCode = CS2103T.getModuleCode();
        ModuleViewCommand moduleViewCommand = new ModuleViewCommand(validModuleCode);
        ModelStub modelStub = new ModuleViewCommandTest.ModelStubWithoutModule();

        assertThrows(CommandException.class,
                String.format(ModuleViewCommand.MESSAGE_MODULE_DOES_NOT_EXIST,
                CS2103T.getModuleCode().value), () -> moduleViewCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        ModuleCode cs2103t = new ModuleCode("CS2103T");
        ModuleCode cs2101 = new ModuleCode("CS2101");
        ModuleViewCommand viewCs2103tCommand = new ModuleViewCommand(cs2103t);
        ModuleViewCommand viewCs2101Command = new ModuleViewCommand(cs2101);

        // same object -> returns true
        assertTrue(viewCs2103tCommand.equals(viewCs2103tCommand));

        // same values -> returns true
        ModuleViewCommand viewCs2103tCommandCopy = new ModuleViewCommand(cs2103t);
        assertTrue(viewCs2103tCommand.equals(viewCs2103tCommandCopy));

        // different types -> returns false
        assertFalse(viewCs2103tCommand.equals(1));

        // null -> returns false
        assertFalse(viewCs2103tCommand.equals(null));

        // different facilitator -> returns false
        assertFalse(viewCs2103tCommand.equals(viewCs2101Command));
    }


    private class ModelStubWithModule extends ModelStub {
        private final ArrayList<Module> moduleList = new ArrayList<>();
        private final ArrayList<Facilitator> facilitatorList = new ArrayList<>();
        private Optional<Module> module;

        ModelStubWithModule(Module module, Facilitator facilitator) {
            requireAllNonNull(module, facilitator);
            this.moduleList.add(module);
            this.facilitatorList.add(facilitator);
            this.module = Optional.empty();
        }

        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return moduleList.stream().anyMatch(module::isSameModule);
        }

        @Override
        public Optional<Module> findModule(ModuleCode moduleCode) {
            for (Module module : moduleList) {
                if (module.getModuleCode().equals(moduleCode)) {
                    return Optional.of(module);
                }
            }
            return Optional.empty();
        }

        @Override
        public void updateModule(Optional<Module> module) {
            this.module = module;
        }

        @Override
        public void updateFacilitatorListForModule(Predicate<Facilitator> predicate) {
            ModuleCode toCheck;
            if (module.isEmpty()) {
                facilitatorList.clear();
            } else {
                toCheck = module.get().getModuleCode();
                facilitatorList.removeIf(facilitator -> !facilitator.getModuleCodes().contains(toCheck));
            }
        }
    }

    private class ModelStubWithoutModule extends ModelStub {
        private final ArrayList<Module> moduleList = new ArrayList<>();
        private Optional<Module> module;

        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return moduleList.stream().anyMatch(module::isSameModule);
        }

        @Override
        public Optional<Module> findModule(ModuleCode moduleCode) {
            for (Module module : moduleList) {
                if (module.getModuleCode().equals(moduleCode)) {
                    return Optional.of(module);
                }
            }
            return Optional.empty();
        }
    }
}
