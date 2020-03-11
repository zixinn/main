package seedu.address.logic.commands.facil;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.model.Model;
import seedu.address.model.facilitator.NameContainsKeywordsPredicate;

/**
 * Finds and lists all facilitators in Mod Manager whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FacilFind extends FacilCommand {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all facilitators whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;

    public FacilFind(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFacilitatorList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_FACILITATORS_LISTED_OVERVIEW,
                model.getFilteredFacilitatorList().size()), CommandType.FACILITATOR);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FacilFind // instanceof handles nulls
                && predicate.equals(((FacilFind) other).predicate)); // state check
    }
}
