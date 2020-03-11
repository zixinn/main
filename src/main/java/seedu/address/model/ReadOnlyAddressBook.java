package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.facilitator.Facilitator;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the facilitators list.
     * This list will not contain any duplicate facilitators.
     */
    ObservableList<Facilitator> getFacilitatorList();

}
