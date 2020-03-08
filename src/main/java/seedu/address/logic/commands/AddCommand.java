package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OFFICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.facilitator.Facilitator;

/**
 * Adds a facilitator to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a facilitator to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_OFFICE + "OFFICE] "
            + "[" + PREFIX_MODULE_CODE + "MODULE_CODE]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_OFFICE + "311, Clementi Ave 2, #02-25 "
            + PREFIX_MODULE_CODE + "friends "
            + PREFIX_MODULE_CODE + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New facilitator added: %1$s";
    public static final String MESSAGE_NOT_ADDED = "At least optional fields (phone, email, office) must be provided.";
    public static final String MESSAGE_DUPLICATE_FACILITATOR = "This facilitator already exists in the address book";

    private final Facilitator toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Facilitator}
     */
    public AddCommand(Facilitator facilitator) {
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
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
