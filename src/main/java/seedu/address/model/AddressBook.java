package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.model.facilitator.UniqueFacilitatorList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameFacilitator comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueFacilitatorList facilitators;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        facilitators = new UniqueFacilitatorList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Facilitators in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the facilitator list with {@code facilitators}.
     * {@code facilitators} must not contain duplicate facilitators.
     */
    public void setFacilitators(List<Facilitator> facilitators) {
        this.facilitators.setFacilitators(facilitators);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setFacilitators(newData.getFacilitatorList());
    }

    //// facilitator-level operations

    /**
     * Returns true if a facilitator with the same identity as {@code facilitator} exists in Mod Manager.
     */
    public boolean hasFacilitator(Facilitator facilitator) {
        requireNonNull(facilitator);
        return facilitators.contains(facilitator);
    }

    /**
     * Adds a facilitator to the Mod Manager.
     * The facilitator must not already exist in Mod Manager.
     */
    public void addFacilitator(Facilitator p) {
        facilitators.add(p);
    }

    /**
     * Replaces the given facilitator {@code target} in the list with {@code editedFacilitator}.
     * {@code target} must exist in Mod Manager.
     * The facilitator identity of {@code editedFacilitator} must not be the same as another existing facilitator
     * in Mod Manager.
     */
    public void setFacilitator(Facilitator target, Facilitator editedFacilitator) {
        requireNonNull(editedFacilitator);

        facilitators.setFacilitator(target, editedFacilitator);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in Mod Manager.
     */
    public void removeFacilitator(Facilitator key) {
        facilitators.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return facilitators.asUnmodifiableObservableList().size() + " facilitators";
        // TODO: refine later
    }

    @Override
    public ObservableList<Facilitator> getFacilitatorList() {
        return facilitators.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && facilitators.equals(((AddressBook) other).facilitators));
    }

    @Override
    public int hashCode() {
        return facilitators.hashCode();
    }
}
