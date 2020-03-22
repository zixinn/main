package seedu.address.logic.commands.calendar;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Represents a calendar command with hidden internal logic and the ability to be executed.
 */
public abstract class CalCommand extends Command {

    public static final List<String> ALL_COMMAND_WORDS = List.of(COMMAND_WORD_VIEW, COMMAND_WORD_FIND);
    public static final String CAL_VIEW_FORMAT = COMMAND_GROUP_CAL + " " + COMMAND_WORD_VIEW + " this/week";
    public static final List<String> ALL_COMMAND_FORMATS = List.of(CAL_VIEW_FORMAT);

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

}
