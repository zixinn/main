package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.facil.FacilCommand;
import seedu.address.logic.commands.facil.FacilFind;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.facilitator.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FacilFind object
 */
public class FindCommandParser implements Parser<FacilFind> {

    /**
     * Parses the given {@code String} of arguments in the context of the FacilFind
     * and returns a FacilFind object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FacilFind parse(String args, String s) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FacilFind.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FacilFind(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
