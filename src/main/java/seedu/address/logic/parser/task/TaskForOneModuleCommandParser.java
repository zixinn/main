package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.task.TaskFindCommand;
import seedu.address.logic.commands.task.TaskForOneModuleCommand;
import seedu.address.logic.commands.task.TaskSearchCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskContainsKeywordsPredicate;
import seedu.address.model.task.TaskSearchPredicate;

/**
 * Parses input arguments and creates a new TaskFindCommand objecNt
 */
public class TaskForOneModuleCommandParser implements Parser<TaskForOneModuleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TaskFindCommand
     * and returns a TaskFindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TaskForOneModuleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_MODULE_CODE);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskForOneModuleCommand.MESSAGE_USAGE));
        }
        ModuleCode moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE_CODE));
        return new TaskForOneModuleCommand(moduleCode);
    }
}
