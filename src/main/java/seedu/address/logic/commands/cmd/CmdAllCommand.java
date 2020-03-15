package seedu.address.logic.commands.cmd;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all possible commands.
 */
public class CmdAllCommand extends CmdCommand {

    @Override
    public CommandResult execute(Model model) throws CommandException {
        StringBuilder builder = new StringBuilder();

        Command.ALL_COMMAND_GROUPS.forEach(str -> builder.append(str + " "));

        return new CommandResult(builder.toString(), CommandType.CMD);
    }
}
