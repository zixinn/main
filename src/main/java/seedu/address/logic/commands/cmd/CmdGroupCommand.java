package seedu.address.logic.commands.cmd;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all commands from a specific command group.
 */
public class CmdGroupCommand extends CmdCommand {
    private final String commandGroup;

    public CmdGroupCommand(String commandGroup) {
        this.commandGroup = commandGroup;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // To implement: Returns the correct command group
        // in a try-catch block, throws Exception when not found group.
        return null;
    }
}
