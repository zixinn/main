package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.facilitator.Facilitator;

/**
 * Deletes a facilitator identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the facilitator identified by the index number used in the displayed facilitator list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_FACILITATOR_SUCCESS = "Deleted Facilitator: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Facilitator> lastShownList = model.getFilteredFacilitatorList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FACILITATOR_DISPLAYED_INDEX);
        }

        Facilitator facilitatorToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteFacilitator(facilitatorToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_FACILITATOR_SUCCESS, facilitatorToDelete),
                CommandType.FACILITATOR);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
