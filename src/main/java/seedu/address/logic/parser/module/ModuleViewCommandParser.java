package seedu.address.logic.parser.module;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.module.ModuleViewCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;

/**
 * Parses input arguments and creates a new ModuleViewCommand object.
 */
public class ModuleViewCommandParser implements Parser<ModuleViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ModuleViewCommand
     * and returns a ModuleViewCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public ModuleViewCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModuleViewCommand.MESSAGE_USAGE));
        }

        try {
            Index index = ParserUtil.parseIndex(trimmedArgs);
            return new ModuleViewCommand(index);
        } catch (ParseException pe) {
            // intentionally left empty
        }

        try {
            ModuleCode moduleCode = ParserUtil.parseModuleCode(trimmedArgs);
            return new ModuleViewCommand(moduleCode);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModuleViewCommand.MESSAGE_USAGE));
        }
    }
}
