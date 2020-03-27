package seedu.address.logic.parser.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ON;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.task.TaskEditCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;

/**
 * Parses input arguments and creates a new TaskEditCommand object
 */
public class TaskEditCommandParser implements Parser<TaskEditCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TaskEditCommand
     * and returns an TaskEditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TaskEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_DESCRIPTION, PREFIX_ON, PREFIX_AT);

        return new TaskEditCommand();
    }

    /**
     * Parses {@code Collection<String> moduleCodes} into a {@code Set<ModuleCode>} if {@code moduleCodes} is non-empty.
     * If {@code moduleCodes} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<ModuleCode>} containing zero module codes.
     */
    private Optional<Set<ModuleCode>> parseModuleCodesForEdit(Collection<String> moduleCodes) throws ParseException {
        assert moduleCodes != null;

        if (moduleCodes.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> moduleCodeSet = moduleCodes.size() == 1 && moduleCodes.contains("")
                ? Collections.emptySet() : moduleCodes;
        return Optional.of(ParserUtil.parseModuleCodes(moduleCodeSet));
    }

    /**
     * Parses the given {@code String} of arguments and returns the {@code String} for the field for execution.
     * If the given {@code String} of arguments is an empty string, it will be parsed into a null Object.
     *
     * @param args the arguments for the field
     * @return the {@code String} for the field
     */
    private String parseFieldForEdit(String args) {
        if (args.equals("")) {
            return null;
        } else {
            return args;
        }
    }
}
