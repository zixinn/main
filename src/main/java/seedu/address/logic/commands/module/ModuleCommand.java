package seedu.address.logic.commands.module;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a module command with hidden internal logic and the ability to be executed.
 */
public abstract class ModuleCommand extends Command {
    public static final List<String> ALL_COMMAND_WORDS = List.of(
            COMMAND_WORD_ADD, COMMAND_WORD_DELETE, COMMAND_WORD_EDIT, COMMAND_WORD_LIST, COMMAND_WORD_VIEW);

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}
