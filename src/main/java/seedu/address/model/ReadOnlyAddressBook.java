package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.module.Module;

import java.util.List;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {


    /**
     * Returns an unmodifiable view of the modules list.
     * This list will not contain any duplicate modules.
     */
    ObservableList<Module> getModuleList();

    /**
     * Returns an unmodifiable view of the facilitators list.
     * This list will not contain any duplicate facilitators.
     */
    ObservableList<Facilitator> getFacilitatorList();

    /**
     * Returns a list of lesson list.
     * This list will not contain any duplicate lessons.
     * @return List of lessons.
     */
    List<Lesson> getLessonList();

}
