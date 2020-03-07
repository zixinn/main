package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.facilitator.Facilitator;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Facilitator> PREDICATE_SHOW_ALL_FACILITATORS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a facilitator with the same identity as {@code facilitator} exists in the address book.
     */
    boolean hasFacilitator(Facilitator facilitator);

    /**
     * Deletes the given facilitator.
     * The facilitator must exist in the address book.
     */
    void deleteFacilitator(Facilitator target);

    /**
     * Adds the given facilitator.
     * {@code facilitator} must not already exist in the address book.
     */
    void addFacilitator(Facilitator facilitator);

    /**
     * Replaces the given facilitator {@code target} with {@code editedFacilitator}.
     * {@code target} must exist in the address book.
     * The facilitator identity of {@code editedFacilitator} must not be the same as another existing facilitator
     * in the address book.
     */
    void setFacilitator(Facilitator target, Facilitator editedFacilitator);

    /** Returns an unmodifiable view of the filtered facilitator list */
    ObservableList<Facilitator> getFilteredFacilitatorList();

    /**
     * Updates the filter of the filtered facilitator list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFacilitatorList(Predicate<Facilitator> predicate);
}
