package seedu.address.logic.commands.module;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Deletes a module identified using it's displayed index from Mod Manager.
 */
public class ModuleDeleteCommand extends ModuleCommand {

    public static final String MESSAGE_USAGE = COMMAND_GROUP_MOD + " " + COMMAND_WORD_DELETE
            + ": Deletes the module identified by the index number used in the displayed module list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_GROUP_MOD + " " + COMMAND_WORD_DELETE + " 1";

    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted module: %1$s";

    private final Index targetIndex;

    /**
     * Creates a ModuleDeleteCommand to delete the module the specified {@code index}.
     */
    public ModuleDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        Module moduleToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteModule(moduleToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete),
                CommandType.MODULE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((ModuleDeleteCommand) other).targetIndex)); // state check
    }
}
