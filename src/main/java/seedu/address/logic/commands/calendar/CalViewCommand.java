package seedu.address.logic.commands.calendar;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandResultUi;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Show calendar view to user.
 */
public class CalViewCommand extends CalCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD_CAL + " " + COMMAND_WORD_VIEW
            + ": Views the calendar for a week. "
            + "Parameters: "
            + PREFIX_WEEK + " this OR"
            + PREFIX_WEEK + " next";

    public static final String MESSAGE_SUCCESS = "Calendar can be viewed";

    private String week;

    /**
     * Creates a CalViewCommand to view the specified week.
     */
    public CalViewCommand(String week) {
        requireNonNull(week);
        this.week = week;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResultUi(MESSAGE_SUCCESS, CommandType.CALENDAR, week);
    }
}
