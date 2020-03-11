package seedu.address.logic.commands.facil;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class FacilCommand extends Command {

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}
