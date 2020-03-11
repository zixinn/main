package seedu.address.logic.commands.facil;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.model.facilitator.NameContainsKeywordsPredicate;

/**
 * General type for 'facil' command family
 */
public abstract class FacilCommand extends Command {
    public static final String ADD_COMMAND_WORD = "add";
    public static final String EDIT_COMMAND_WORD = "edit";
    public static final String LIST_COMMAND_WORD = "list";
    public static final String FIND_COMMAND_WORD = "find";
    public static final String DELETE_COMMAND_WORD = "delete";
    public static final List<String> ALL_COMMAND_WORDS = List.of(
                    ADD_COMMAND_WORD, EDIT_COMMAND_WORD, LIST_COMMAND_WORD, FIND_COMMAND_WORD, DELETE_COMMAND_WORD);


    public static FacilAdd add(Facilitator facilitator) {
        return new FacilAdd(facilitator);
    }

    public static FacilEdit edit(Index index, FacilEdit.EditFacilitatorDescriptor editFacilitatorDescriptor) {
        return new FacilEdit(index, editFacilitatorDescriptor);
    }

    public static FacilDelete delete(Index targetIndex) {
        return new FacilDelete(targetIndex);
    }

    public static FacilFind find(NameContainsKeywordsPredicate predicate) {
        return new FacilFind(predicate);
    }

    public static FacilList list() {
        return new FacilList();
    }

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}
