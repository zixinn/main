package seedu.address.logic.parser.facil;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_FACILITATOR_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.facil.FacilAddCommand;
import seedu.address.logic.commands.facil.FacilCommand;
import seedu.address.logic.commands.facil.FacilDeleteCommand;
import seedu.address.logic.commands.facil.FacilEditCommand;
import seedu.address.logic.commands.facil.FacilFindCommand;
import seedu.address.logic.commands.facil.FacilListCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FacilAddCommand object
 */
public class FacilCommandParser implements Parser<FacilCommand> {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_FACIL_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    @Override
    public FacilCommand parse(String userInput) throws ParseException {
        final Matcher matcher = BASIC_FACIL_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case FacilAddCommand.COMMAND_WORD:
            return new FacilAddCommandParser().parse(arguments);

        case FacilEditCommand.COMMAND_WORD:
            return new FacilEditCommandParser().parse(arguments);

        case FacilDeleteCommand.COMMAND_WORD:
            return new FacilDeleteCommandParser().parse(arguments);

        case FacilFindCommand.COMMAND_WORD:
            return new FacilFindCommandParser().parse(arguments);

        case FacilListCommand.COMMAND_WORD:
            return new FacilListCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_FACILITATOR_COMMAND);
        }
    }
}
