package seedu.address.logic.parser.lesson;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.lesson.LessonEditCommand;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonType;
import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.EditLessonDescriptorBuilder;
import seedu.address.testutil.LessonBuilder;

public class LessonEditCommandParserTest {
    private LessonEditCommandParser parser = new LessonEditCommandParser();

    @Test
    public void parse_missingCompulsoryFields_throwsException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, LessonEditCommand.MESSAGE_USAGE);

        // missing index
        String userInput = " " + PREFIX_MODULE_CODE + " " + LessonBuilder.DEFAULT_MODULE_CODE + " "
                + PREFIX_MODULE_CODE + " " + "GEQ1000" + " "
                + PREFIX_TYPE + " " + LessonBuilder.DEFAULT_LESSON_TYPE + " ";
        assertParseFailure(parser, userInput, expectedMessage);

        //missing module code
        userInput = " 1 " + PREFIX_TYPE + " " + "SEC" + " ";
        assertParseFailure(parser, userInput, expectedMessage);

        // missing all optional parts
        userInput = " 1 " + PREFIX_MODULE_CODE + " " + LessonBuilder.DEFAULT_MODULE_CODE + " ";
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_invalidPreamble_throwsException() {
        String expectedMessage = MESSAGE_INVALID_INDEX;
        String userInput = " 0 " + PREFIX_MODULE_CODE + " " + LessonBuilder.DEFAULT_MODULE_CODE + " "
                + PREFIX_MODULE_CODE + " " + "GEQ1000" + " "
                + PREFIX_TYPE + " " + LessonBuilder.DEFAULT_LESSON_TYPE + " ";
        assertParseFailure(parser, userInput, expectedMessage);

        // negative index
        userInput = " -5 " + PREFIX_MODULE_CODE + " " + LessonBuilder.DEFAULT_MODULE_CODE + " "
                + PREFIX_MODULE_CODE + " " + "GEQ1000" + " "
                + PREFIX_TYPE + " " + LessonBuilder.DEFAULT_LESSON_TYPE + " ";
        assertParseFailure(parser, userInput, expectedMessage);

        // additional invalid arguments in index
        userInput = " 1 some random string " + PREFIX_MODULE_CODE + " " + LessonBuilder.DEFAULT_MODULE_CODE + " "
                + PREFIX_MODULE_CODE + " " + "GEQ1000" + " "
                + PREFIX_TYPE + " " + LessonBuilder.DEFAULT_LESSON_TYPE + " ";
        assertParseFailure(parser, userInput, expectedMessage);

        // invalid prefix
        userInput = " 1 /weird some random string " + PREFIX_MODULE_CODE + " " + LessonBuilder.DEFAULT_MODULE_CODE + " "
                + PREFIX_MODULE_CODE + " " + "GEQ1000" + " "
                + PREFIX_TYPE + " " + LessonBuilder.DEFAULT_LESSON_TYPE + " ";
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_invalidValue_throwsException() {
        // invalid module code
        String userInput = " 1 " + PREFIX_MODULE_CODE + " " + "CODE123" + " "
                + PREFIX_MODULE_CODE + " " + "GEQ1000" + " "
                + PREFIX_TYPE + " " + LessonBuilder.DEFAULT_LESSON_TYPE + " ";
        assertParseFailure(parser, userInput, ModuleCode.MESSAGE_CONSTRAINTS);

        // invalid type
        userInput = " 1 " + PREFIX_MODULE_CODE + " " + LessonBuilder.DEFAULT_MODULE_CODE + " "
                + PREFIX_MODULE_CODE + " " + "GEQ1000" + " "
                + PREFIX_TYPE + " " + "FREE" + " ";
        assertParseFailure(parser, userInput, LessonType.MESSAGE_CONSTRAINTS);

        // invalid day and time
        userInput = " 1 " + PREFIX_MODULE_CODE + " " + LessonBuilder.DEFAULT_MODULE_CODE + " "
                + PREFIX_MODULE_CODE + " " + "GEQ1000" + " "
                + PREFIX_AT + " " + LessonBuilder.DEFAULT_DAY + " " + "2:00"
                + " " + "3:00" + " ";
        assertParseFailure(parser, userInput, Lesson.MESSAGE_CONSTRAINTS_TIME);
        userInput = " 1 " + PREFIX_MODULE_CODE + " " + LessonBuilder.DEFAULT_MODULE_CODE + " "
                + PREFIX_MODULE_CODE + " " + "GEQ1000" + " "
                + PREFIX_AT + " " + "FREEDAY" + " " + LessonBuilder.DEFAULT_START_TIME
                + " " + LessonBuilder.DEFAULT_END_TIME + " ";
        assertParseFailure(parser, userInput, Lesson.MESSAGE_CONSTRAINTS_DAY);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = " 1 " + PREFIX_MODULE_CODE + " " + LessonBuilder.DEFAULT_MODULE_CODE + " "
                + PREFIX_MODULE_CODE + " " + "GEQ1000" + " "
                + PREFIX_TYPE + " " + "REC" + " "
                + PREFIX_AT + " SUNDAY 04:00 05:00 "
                + PREFIX_VENUE + " Home ";
        LessonEditCommand.EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder().withModuleCode("GEQ1000")
                .withLessonType("REC")
                .withDay("SUNDAY")
                .withStartTime("04:00")
                .withEndTime("05:00")
                .withVenue("Home").build();
        LessonEditCommand command = new LessonEditCommand(new ModuleCode(LessonBuilder.DEFAULT_MODULE_CODE),
                INDEX_FIRST, descriptor);
        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // module only
        String userInput = " 1 " + PREFIX_MODULE_CODE + " " + LessonBuilder.DEFAULT_MODULE_CODE + " "
                + PREFIX_MODULE_CODE + " " + "GEQ1000" + " ";

        LessonEditCommand.EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder().withModuleCode("GEQ1000")
                .build();

        LessonEditCommand command = new LessonEditCommand(new ModuleCode(LessonBuilder.DEFAULT_MODULE_CODE),
                INDEX_FIRST, descriptor);
        assertParseSuccess(parser, userInput, command);

        // lesson type only
        userInput = " 1 " + PREFIX_MODULE_CODE + " " + LessonBuilder.DEFAULT_MODULE_CODE + " "
                + PREFIX_TYPE + " " + "REC" + " ";

        descriptor = new EditLessonDescriptorBuilder()
                .withLessonType("REC").build();

        command = new LessonEditCommand(new ModuleCode(LessonBuilder.DEFAULT_MODULE_CODE),
                INDEX_FIRST, descriptor);
        assertParseSuccess(parser, userInput, command);

        // day and time only
        userInput = " 1 " + PREFIX_MODULE_CODE + " " + LessonBuilder.DEFAULT_MODULE_CODE + " "
                + PREFIX_AT + " SATURDAY 06:00 07:00 ";

        descriptor = new EditLessonDescriptorBuilder()
                .withDay("SATURDAY").withStartTime("06:00").withEndTime("07:00").build();

        command = new LessonEditCommand(new ModuleCode(LessonBuilder.DEFAULT_MODULE_CODE),
                INDEX_FIRST, descriptor);
        assertParseSuccess(parser, userInput, command);

        // venue only
        userInput = " 1 " + PREFIX_MODULE_CODE + " " + LessonBuilder.DEFAULT_MODULE_CODE + " "
                + PREFIX_VENUE + " somewhere ";

        descriptor = new EditLessonDescriptorBuilder()
                .withVenue("somewhere").build();

        command = new LessonEditCommand(new ModuleCode(LessonBuilder.DEFAULT_MODULE_CODE),
                INDEX_FIRST, descriptor);
        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = " 1 " + PREFIX_MODULE_CODE + " " + LessonBuilder.DEFAULT_MODULE_CODE + " "
                + PREFIX_MODULE_CODE + " " + "GEQ1000" + " " + PREFIX_TYPE + " SEC ";

        LessonEditCommand.EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder().withModuleCode("GEQ1000")
                .withLessonType("SEC").build();

        LessonEditCommand command = new LessonEditCommand(new ModuleCode(LessonBuilder.DEFAULT_MODULE_CODE),
                INDEX_FIRST, descriptor);
        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_multipleRepeatedFieldsExcludingModuleCode_acceptsLast() {
        String userInput = " 1 " + PREFIX_MODULE_CODE + " " + LessonBuilder.DEFAULT_MODULE_CODE + " "
                + PREFIX_TYPE + " REC "
                + PREFIX_TYPE + " SEC ";

        LessonEditCommand.EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder()
                .withLessonType("SEC").build();

        LessonEditCommand command = new LessonEditCommand(new ModuleCode(LessonBuilder.DEFAULT_MODULE_CODE),
                INDEX_FIRST, descriptor);
        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        String userInput = " 1 " + PREFIX_MODULE_CODE + " " + LessonBuilder.DEFAULT_MODULE_CODE + " "
                + PREFIX_TYPE + " FREE "
                + PREFIX_TYPE + " SEC ";

        LessonEditCommand.EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder()
                .withLessonType("SEC").build();

        LessonEditCommand command = new LessonEditCommand(new ModuleCode(LessonBuilder.DEFAULT_MODULE_CODE),
                INDEX_FIRST, descriptor);
        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_resetVenue_success() {
        String userInput = " 1 " + PREFIX_MODULE_CODE + " " + LessonBuilder.DEFAULT_MODULE_CODE + " " + PREFIX_VENUE
                + " ";
        LessonEditCommand.EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder().withVenue("").build();
        LessonEditCommand command = new LessonEditCommand(new ModuleCode(LessonBuilder.DEFAULT_MODULE_CODE),
                INDEX_FIRST, descriptor);
        assertParseSuccess(parser, userInput, command);
    }
}
