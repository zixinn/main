package seedu.address.logic.parser.facilitator;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.facilitator.FacilDeleteCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FacilDeleteCommand object.
 */
public class FacilDeleteCommandParser implements Parser<FacilDeleteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FacilDeleteCommand
     * and returns a FacilDeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FacilDeleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new FacilDeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FacilDeleteCommand.MESSAGE_USAGE), pe);
        }
    }
}
