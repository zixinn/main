package seedu.address.logic.commands.lesson;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import java.util.List;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXT;



public abstract class LessonCommand extends Command {

    public static final String ADD_FORMAT =
            String.format("%s %s %s MOD_CODE %s CLASS_TYPE %s DAY START_TIME END_TIME [%s VENUE] [%s FACILITATOR_NAME]",
                    COMMAND_GROUP_CLASS, COMMAND_WORD_ADD,
                    PREFIX_MODULE_CODE, PREFIX_TYPE, PREFIX_AT, PREFIX_VENUE, PREFIX_NAME);

    public static final String DELETE_FORMAT = String.format("%s %s %s MOD_CODE %s CLASS_TYPE",
            COMMAND_GROUP_CLASS, COMMAND_WORD_DELETE, PREFIX_MODULE_CODE, PREFIX_TYPE);

    public static final String EDIT_FORMAT =
            String.format("%s %s %s MOD_CODE %s CLASS_TYPE [%s DAY START_TIME END_TIME] [%s VENUE] " +
                            "[%s FACILITATOR_NAME]",
                    COMMAND_GROUP_CLASS, COMMAND_WORD_EDIT,
                    PREFIX_MODULE_CODE, PREFIX_TYPE, PREFIX_AT, PREFIX_VENUE, PREFIX_NAME);

    public static final String FIND_FORMAT =
            String.format("%s %s [%s] [%s DAY]", COMMAND_GROUP_CLASS, COMMAND_WORD_FIND, PREFIX_NEXT, PREFIX_AT);

    public static final String LIST_FORMAT = String.format("%s %s", COMMAND_GROUP_CLASS, COMMAND_WORD_LIST);

    public static final List<String> ALL_COMMAND_WORDS = List.of(
            COMMAND_WORD_ADD, COMMAND_WORD_EDIT, COMMAND_WORD_DELETE, COMMAND_WORD_FIND, COMMAND_WORD_LIST);

    public static final List<String> ALL_COMMAND_FORMATS = List.of(
            ADD_FORMAT, DELETE_FORMAT, EDIT_FORMAT, FIND_FORMAT, LIST_FORMAT);

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}
