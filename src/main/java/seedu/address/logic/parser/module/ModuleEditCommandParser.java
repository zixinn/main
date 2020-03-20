package seedu.address.logic.parser.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.module.ModuleEditCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ModuleEditCommand object.
 */
public class ModuleEditCommandParser implements Parser<ModuleEditCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ModuleEditCommand
     * and returns an ModuleEditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public ModuleEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULE_CODE, PREFIX_DESCRIPTION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ModuleEditCommand.MESSAGE_USAGE), pe);
        }

        ModuleEditCommand.EditModuleDescriptor editModuleDescriptor = new ModuleEditCommand.EditModuleDescriptor();
        if (argMultimap.getValue(PREFIX_MODULE_CODE) != null) {
            editModuleDescriptor.setModuleCode(ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE_CODE)));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION) != null) {
            editModuleDescriptor.setDescription(ParserUtil.parseDescription(
                    parseFieldForEdit(argMultimap.getValue(PREFIX_DESCRIPTION))));
        }

        if (!editModuleDescriptor.isAnyFieldEdited()) {
            throw new ParseException(ModuleEditCommand.MESSAGE_NOT_EDITED);
        }

        return new ModuleEditCommand(index, editModuleDescriptor);
    }

    /**
     * Parses the given {@code String} of arguments and returns the {@code String} for the field for execution.
     * If the given {@code String} of arguments is an empty string, it will be parsed into a null Object.
     *
     * @param args the arguments for the field.
     * @return the {@code String} for the field.
     */
    private String parseFieldForEdit(String args) {
        if (args.equals("")) {
            return null;
        } else {
            return args;
        }
    }
}
