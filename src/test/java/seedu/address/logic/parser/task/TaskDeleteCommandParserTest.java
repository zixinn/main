package seedu.address.logic.parser.task;

import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TASK_ID_DESC_VALID_ID;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_ID;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.task.TaskDeleteCommand;
import seedu.address.model.module.ModuleCode;

class TaskDeleteCommandParserTest {
    private TaskDeleteCommandParser parser = new TaskDeleteCommandParser();

    @Test
    public void parse_WithPreamble_success() {
    }
}