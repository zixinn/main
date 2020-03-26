package seedu.address.logic.commands.module;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;

/**
 * Deletes a module identified using it's displayed index from Mod Manager.
 */
public class ModuleDeleteCommand extends ModuleCommand {

    public static final String MESSAGE_USAGE = COMMAND_GROUP_MOD + " " + COMMAND_WORD_DELETE
            + ": Deletes the module identified by the index number used in the displayed module list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_GROUP_MOD + " " + COMMAND_WORD_DELETE + " 1\n"
            + "Parameters: MODULE_CODE (must be a valid module code)\n"
            + "Example: " + COMMAND_GROUP_MOD + " " + COMMAND_WORD_DELETE + " CS4215";

    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted module: %1$s";

    public static final String MESSAGE_DELETE_NON_EXISTENT_MODULE = "%s does not exist.";

    private final Index targetIndex;
    private final ModuleCode moduleCode;

    /**
     * Creates a ModuleDeleteCommand to delete the module the specified {@code index}.
     */
    public ModuleDeleteCommand(Index targetIndex) {
        requireNonNull(targetIndex);

        this.targetIndex = targetIndex;
        this.moduleCode = null;
    }

    public ModuleDeleteCommand(ModuleCode moduleCode) {
        requireNonNull(moduleCode);

        this.moduleCode = moduleCode;
        this.targetIndex = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();
        Module moduleToDelete = null;

        if (targetIndex != null && targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        } else if (targetIndex != null) {
            moduleToDelete = lastShownList.get(targetIndex.getZeroBased());
        } else { // index == null
            assert moduleCode != null;
            for (Module mod : lastShownList) {
                if (mod.getModuleCode().equals(moduleCode)) {
                    moduleToDelete = mod;
                    break;
                }
            }
        }

        if (moduleToDelete == null) {
            throw new CommandException(String.format(MESSAGE_DELETE_NON_EXISTENT_MODULE, moduleCode));
        }

        model.deleteModule(moduleToDelete);
        model.deleteModuleCodeFromFacilitatorList(moduleToDelete.getModuleCode());
        model.deleteTasksWithModuleCode(moduleToDelete.getModuleCode());

        if (model.getModule().isPresent() && model.getModule().get().equals(moduleToDelete)) {
            model.updateModule(Optional.empty());
            model.removeLessonFromModule(moduleToDelete.getModuleCode());
        }

        return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete),
                CommandType.MODULE);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ModuleDeleteCommand)) {
            return false;
        }

        ModuleDeleteCommand e = (ModuleDeleteCommand) other;

        // similar nullability check
        boolean indexNull = (targetIndex == null && e.targetIndex == null)
                || (targetIndex != null && e.targetIndex != null);
        boolean moduleCodeNull = (moduleCode == null && e.moduleCode == null)
                || (moduleCode != null && e.moduleCode != null);

        return indexNull && moduleCodeNull
                && (targetIndex == null || targetIndex.equals(e.targetIndex))
                && (moduleCode == null || moduleCode.equals(e.moduleCode));
    }
}
