package seedu.address.logic.parser.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_MODULE_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.module.ModuleAddCommand;
import seedu.address.logic.commands.module.ModuleListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.ModuleUtil;

public class ModuleCommandParserTest {
    private ModuleCommandParser parser = new ModuleCommandParser();

    @Test
    public void parse_add() throws Exception {
        Module module = new ModuleBuilder().build();
        ModuleAddCommand command = (ModuleAddCommand) parser.parse(ModuleUtil.getModuleAddCommand(module));

        assertEquals(new ModuleAddCommand(module), command);
    }

    @Test
    public void parse_list() throws Exception {
        assertTrue(parser.parse(Command.COMMAND_WORD_LIST)
                instanceof ModuleListCommand);
        assertTrue(parser.parse(Command.COMMAND_WORD_LIST + " 3")
                instanceof ModuleListCommand);
    }

    @Test
    public void parse_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_UNKNOWN_MODULE_COMMAND,
                HelpCommand.MESSAGE_USAGE), () -> parser.parse(""));
    }

    @Test
    public void parse_unknownModuleCommand_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_UNKNOWN_MODULE_COMMAND,
                HelpCommand.MESSAGE_USAGE), () -> parser
                .parse("mod unknownCommand"));
    }
}
