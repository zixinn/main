package seedu.address.logic.commands.facilitator;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.facilitator.Facilitator;

/**
 * Deletes a facilitator identified using it's displayed index from Mod Manager.
 */
public class FacilDeleteCommand extends FacilCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD_DELETE
            + ": Deletes the facilitator identified by the index number used in the displayed facilitator list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD_DELETE + " 1";

    public static final String MESSAGE_DELETE_FACILITATOR_SUCCESS = "Deleted Facilitator: %1$s";

    private final Index targetIndex;

    public FacilDeleteCommand(Index targetIndex) {
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
                || (other instanceof FacilDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((FacilDeleteCommand) other).targetIndex)); // state check
    }
}
