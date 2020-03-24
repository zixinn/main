package seedu.address.logic.commands.cmd;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all possible command groups.
 */
public class CmdAllCommand extends CmdCommand {

    public static final String MESSAGE_USAGE = COMMAND_GROUP_CMD + " " + COMMAND_WORD_ALL
            + ": Lists all the command groups available in the system.";

    private final String instruction = "Use " + COMMAND_GROUP_CMD + " " + COMMAND_WORD_GROUP
            + " COMMAND_GROUP to find out about a particular command group.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        StringBuilder builder = new StringBuilder();
        builder.append("Here are all available command groups: \n");
        Command.ALL_COMMAND_GROUPS.forEach(str -> builder.append(str).append(" "));
        builder.append("\n").append(instruction);

        return new CommandResult(builder.toString(), CommandType.CMD);
    }
}
