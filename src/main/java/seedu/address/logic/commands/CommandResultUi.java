package seedu.address.logic.commands;

/**
 * Represents the result of a command execution for Ui.
 */
public class CommandResultUi extends CommandResult {

    /**
     * Arguments in commands to be used in Ui.
     */
    private final String argForUi;

    /**
     * Constructs a {@code CommandResultUi} with the specified fields.
     */
    public CommandResultUi(String feedbackToUser, CommandType type, String argForUi) {
        super(feedbackToUser, type);
        this.argForUi = argForUi;
    }

    public String getArgForUi() {
        return argForUi;
    }
}
