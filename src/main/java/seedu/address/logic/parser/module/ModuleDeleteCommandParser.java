package seedu.address.logic.parser.module;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.module.ModuleDeleteCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;

/**
 * Parses input arguments and creates a new ModuleDeleteCommand object.
 */
public class ModuleDeleteCommandParser implements Parser<ModuleDeleteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ModuleDeleteCommand
     * and returns a ModuleDeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public ModuleDeleteCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModuleDeleteCommand.MESSAGE_USAGE));
        }

        try {
            Index index = ParserUtil.parseIndex(args);
            return new ModuleDeleteCommand(index);
        } catch (ParseException pe) {
            /*
             This part is left empty intentionally
             Kinda hack-ish
             */
        }

        try {
            ModuleCode moduleCode = ParserUtil.parseModuleCode(trimmedArgs);
            return new ModuleDeleteCommand(moduleCode);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModuleDeleteCommand.MESSAGE_USAGE));
        }
    }
}
