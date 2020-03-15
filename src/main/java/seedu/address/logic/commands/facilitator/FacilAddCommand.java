package seedu.address.logic.commands.facilitator;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OFFICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.facilitator.Facilitator;

/**
 * Adds a facilitator to Mod Manager.
 */
public class FacilAddCommand extends FacilCommand {

    public static final String MESSAGE_USAGE = COMMAND_GROUP_FACIL + " " + COMMAND_WORD_ADD
            + ": Adds a facilitator to Mod Manager. "
            + "Parameters: "
            + PREFIX_NAME + " NAME "
            + "[" + PREFIX_PHONE + " PHONE] "
            + "[" + PREFIX_EMAIL + " EMAIL] "
            + "[" + PREFIX_OFFICE + " OFFICE] "
            + "[" + PREFIX_MODULE_CODE + " MODULE_CODE]...\n"
            + "Example: " + COMMAND_GROUP_FACIL + " " + COMMAND_WORD_ADD + " "
            + PREFIX_NAME + " Martin Henz "
            + PREFIX_PHONE + " 98765432 "
            + PREFIX_EMAIL + " henz@comp.nus.edu.sg "
            + PREFIX_OFFICE + " COM2-0356 "
            + PREFIX_MODULE_CODE + " CS1101S";

    public static final String MESSAGE_SUCCESS = "New facilitator added: %1$s";
    public static final String MESSAGE_NOT_ADDED =
            "At least one of the optional fields (phone, email, office, code) must be provided.";
    public static final String MESSAGE_DUPLICATE_FACILITATOR = "This facilitator already exists in Mod Manager";

    private final Facilitator toAdd;

    /**
     * Creates a FacilAddCommand to add the specified {@code Facilitator}
     */
    public FacilAddCommand(Facilitator facilitator) {
        requireNonNull(facilitator);
        toAdd = facilitator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasFacilitator(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_FACILITATOR);
        }

        model.addFacilitator(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), CommandType.FACILITATOR);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FacilAddCommand // instanceof handles nulls
                && toAdd.equals(((FacilAddCommand) other).toAdd));
    }
}
