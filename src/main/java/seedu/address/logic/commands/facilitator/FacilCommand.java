package seedu.address.logic.commands.facilitator;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class FacilCommand extends Command {

    public static final List<String> ALL_COMMAND_WORDS = List.of(
            COMMAND_WORD_ADD, COMMAND_WORD_LIST, COMMAND_WORD_FIND, COMMAND_WORD_EDIT, COMMAND_WORD_DELETE);

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}
