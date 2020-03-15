package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    public static final String COMMAND_WORD_ADD = "add";
    public static final String COMMAND_WORD_CAL = "cal";
    public static final String COMMAND_WORD_CLASS = "class";
    public static final String COMMAND_WORD_CLEAR = "clear";
    public static final String COMMAND_WORD_COMMAND = "command";
    public static final String COMMAND_WORD_DELETE = "delete";
    public static final String COMMAND_WORD_EDIT = "edit";
    public static final String COMMAND_WORD_EXIT = "exit";
    public static final String COMMAND_WORD_FACIL = "facil";
    public static final String COMMAND_WORD_FIND = "find";
    public static final String COMMAND_WORD_HELP = "help";
    public static final String COMMAND_WORD_LIST = "list";
    public static final String COMMAND_WORD_MOD = "mod";
    public static final String COMMAND_WORD_TASK = "task";
    public static final String COMMAND_WORD_VIEW = "view";

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

}
