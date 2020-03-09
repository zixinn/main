package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_FACILITATOR_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.facilitator.Facilitator;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of class word, command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<classWord>\\S+)(\\s*)(?<commandWord>\\S*)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        //eg facil add /name john...
        //facil is classWord, add is commandWord, the rest is arguments
        //for single word command, the command word will be classWord too
        final String classWord = matcher.group("classWord");
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch(classWord) {

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case Facilitator.CLASS_WORD:
            return parseFacilitatorCommand(commandWord, arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses command word and arguments into facilitator command for execution.
     * @param commandWord the type of command
     * @param arguments the arguments for the command
     * @return the command based on the command word and arguments
     * @throws ParseException if the input does not conform the expected format
     */
    public Command parseFacilitatorCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_FACILITATOR_COMMAND);
        }
    }
}
