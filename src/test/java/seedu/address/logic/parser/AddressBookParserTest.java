package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_FACILITATOR_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFacilitators.AMY;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_FACILITATOR;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.facil.FacilAdd;
import seedu.address.logic.commands.facil.FacilDelete;
import seedu.address.logic.commands.facil.FacilEdit;
import seedu.address.logic.commands.facil.FacilFind;
import seedu.address.logic.commands.facil.FacilList;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.model.facilitator.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditFacilitatorDescriptorBuilder;
import seedu.address.testutil.FacilitatorBuilder;
import seedu.address.testutil.FacilitatorUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Facilitator facilitator = new FacilitatorBuilder(AMY).build();
        FacilAdd command = (FacilAdd) parser.parseCommand(Facilitator.CLASS_WORD + " "
                + FacilitatorUtil.getFacilAdd(facilitator));
        assertEquals(new FacilAdd(facilitator), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        FacilDelete command = (FacilDelete) parser.parseCommand(
                Facilitator.CLASS_WORD + " " + FacilDelete.COMMAND_WORD + " "
                        + INDEX_FIRST_FACILITATOR.getOneBased());
        assertEquals(new FacilDelete(INDEX_FIRST_FACILITATOR), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Facilitator facilitator = new FacilitatorBuilder(AMY).build();
        FacilEdit.EditFacilitatorDescriptor descriptor = new EditFacilitatorDescriptorBuilder(facilitator).build();
        FacilEdit command = (FacilEdit) parser.parseCommand(Facilitator.CLASS_WORD + " "
                + FacilEdit.COMMAND_WORD + " "
                + INDEX_FIRST_FACILITATOR.getOneBased() + " "
                + FacilitatorUtil.getEditFacilitatorDescriptorDetails(descriptor));
        assertEquals(new FacilEdit(INDEX_FIRST_FACILITATOR, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FacilFind command = (FacilFind) parser.parseCommand(Facilitator.CLASS_WORD + " "
                + FacilFind.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FacilFind(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(Facilitator.CLASS_WORD + " " + FacilList.COMMAND_WORD)
                instanceof FacilList);
        assertTrue(parser.parseCommand(Facilitator.CLASS_WORD + " " + FacilList.COMMAND_WORD + " 3")
                instanceof FacilList);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_unknownFacilitatorCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_FACILITATOR_COMMAND, () -> parser
                .parseCommand("facil unknownCommand"));
    }
}
