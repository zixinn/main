package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";

    public static final String MESSAGE_UNKNOWN_MODULE_COMMAND = "Unknown module command! \n%1$s";
    public static final String MESSAGE_UNKNOWN_FACILITATOR_COMMAND = "Unknown facilitator command! \n%1$s";
    public static final String MESSAGE_UNKNOWN_LESSON_COMMAND = "Unknown lesson command! \n%1$s";
    public static final String MESSAGE_UNKNOWN_CALENDAR_COMMAND = "Unknown calendar command! \n%1$s";
    public static final String MESSAGE_UNKNOWN_TASK_COMMAND = "Unknown task command! \n%1$s";
    public static final String MESSAGE_UNKNOWN_CMD_COMMAND = "Unknown cmd command! \n%1$s";

    public static final String MESSAGE_INVALID_MODULE_DISPLAYED_INDEX = "The module index provided is invalid!";
    public static final String MESSAGE_INVALID_FACILITATOR_DISPLAYED_INDEX =
            "The facilitator index provided is invalid!";
    public static final String MESSAGE_FACILITATORS_LISTED_OVERVIEW = "%1$d facilitators listed!";
    public static final String MESSAGE_FACILITATOR_NOT_FOUND = "Facilitator %s does not exist in Mod Manager.";
    public static final String MESSAGE_TASKS_LISTED_OVERVIEW = "%1$d tasks listed!";
    public static final String MESSAGE_TASKS_FILTERED_OVERVIEW = "%1$d tasks matched your query! "
            + "Type `task list` to return to the main task list.";
    public static final String MESSAGE_INVALID_CMD_GROUP = "%s group does not exist.";
    public static final String MESSAGE_AT_WITHOUT_ON_ERROR = "/at parameter must not exist without /on parameter.";
}
