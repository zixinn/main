package seedu.address.logic.parser.lesson;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.lesson.LessonDeleteCommand;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.module.ModuleCode;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the LessonDeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the LessonDeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class LessonDeleteCommandParserTest {
    LessonDeleteCommandParser parser = new LessonDeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, " /code CS2103T /num 1",
                new LessonDeleteCommand(INDEX_FIRST, Optional.of(new ModuleCode("CS2103T"))));
        assertParseSuccess(parser, " 1 ", new LessonDeleteCommand(INDEX_FIRST, Optional.empty()));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " a ", ParserUtil.MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, " /code CS2103T /num a ", ParserUtil.MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, " /code CODE123 /num 1 ", ModuleCode.MESSAGE_CONSTRAINTS);
    }
}
