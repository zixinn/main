package seedu.address.logic.parser.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_AT_WITHOUT_ON_ERROR;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ON;

import java.util.Optional;
import java.util.Scanner;

import javafx.util.Pair;
import seedu.address.logic.commands.task.TaskEditCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.task.Task;
import seedu.address.model.task.util.TaskDateTime;
import seedu.address.model.task.util.TaskIDManager;

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
        String taskIDString = argMultimap.getPreamble();

        if (taskIDString.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskEditCommand.MESSAGE_USAGE));
        }

        int present = 0;

        Pair<ModuleCode, Integer> pair = parseTaskIDString(taskIDString);
        ModuleCode moduleCode = pair.getKey();
        int taskID = pair.getValue();

        TaskEditCommand.EditTaskDescriptor editTaskDescriptor =
                new TaskEditCommand.EditTaskDescriptor();

        if (argMultimap.getValue(PREFIX_DESCRIPTION) != null) {
            present++;
            editTaskDescriptor.setDescription(
                    ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION)));
        }

        if (argMultimap.getValue(PREFIX_ON) != null) {
            present++;
            TaskDateTime taskDateTime;
            if (argMultimap.getValue(PREFIX_ON).trim().equals(TaskEditCommand.SPECIAL_VALUE_NON)) {
                taskDateTime = Task.tabooDateTime;
                if (argMultimap.getValue(PREFIX_AT) != null) {
                    throw new ParseException(TaskEditCommand.MESSAGE_NON_HAS_NO_TAILS);
                }
            } else if (argMultimap.getValue(PREFIX_AT) != null) {
                taskDateTime = new TaskDateTime(argMultimap.getValue(PREFIX_ON), argMultimap.getValue(PREFIX_AT));
            } else {
                taskDateTime = new TaskDateTime(argMultimap.getValue(PREFIX_ON));
            }
            editTaskDescriptor.setTaskDateTime(taskDateTime);
            assert editTaskDescriptor.getTaskDateTime().equals(Optional.ofNullable(taskDateTime));
        } else if (argMultimap.getValue(PREFIX_AT) != null) {
            throw new ParseException(MESSAGE_AT_WITHOUT_ON_ERROR);
        }

        if (present == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskEditCommand.MESSAGE_USAGE));
        }

        return new TaskEditCommand(moduleCode, taskID, editTaskDescriptor);
    }

    private Pair<ModuleCode, Integer> parseTaskIDString(String inp) throws ParseException {
        assert !inp.isEmpty();

        Scanner sc = new Scanner(inp);
        String modCodeString = sc.next();
        if (!ModuleCode.isValidModuleCode(modCodeString)) {
            throw new ParseException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        ModuleCode moduleCode = new ModuleCode(modCodeString);

        if (!sc.hasNextInt()) {
            throw new ParseException(TaskIDManager.MESSAGE_USAGE_CONSTRAINTS);
        }
        int taskID = sc.nextInt();

        if (sc.hasNext()) {
            throw new ParseException(TaskIDManager.MESSAGE_USAGE_CONSTRAINTS);
        }
        sc.close();

        return new Pair<ModuleCode, Integer>(moduleCode, taskID);
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
