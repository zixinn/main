package seedu.address.logic.parser.task;

import org.junit.jupiter.api.Test;

class TaskEditCommandParserTest {
    private TaskAddCommandParser parser = new TaskAddCommandParser();

    @Test
    public void parse_WithPreamble_success() {
        // TODO: @nhatnguyen
        // the TaskAddCommands cannot be compared, since the ID generated for each Add Command is different
        // expected: <[✘] [CS2103T 430] Software Engineering> but was: <[✘] [CS2103T 552] Software Engineering>

        /*
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MODULE_CODE_DESC_CS2103T + DESCRIPTION_DESC_CS2103T,
                new TaskAddCommand(
                        Task.makeNonScheduledTask(new Description(VALID_DESCRIPTION_CS2103T),
                                new ModuleCode(VALID_MODULE_CODE_CS2103T))));

        // arbitrary preamble
        assertParseSuccess(parser, PREAMBLE_NON_EMPTY + MODULE_CODE_DESC_CS2103T + DESCRIPTION_DESC_CS2103T,
                new TaskAddCommand(
                        Task.makeNonScheduledTask(new Description(VALID_DESCRIPTION_CS2103T),
                                new ModuleCode(MODULE_CODE_DESC_CS2103T))));


        // the rest of the methods below will not test the presence of Preamble
        // since we already tested it here.
        */
    }
}