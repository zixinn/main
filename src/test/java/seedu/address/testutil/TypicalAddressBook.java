package seedu.address.testutil;

import static seedu.address.testutil.TypicalFacilitators.getTypicalFacilitators;
import static seedu.address.testutil.TypicalLessons.getTypicalLessons;
import static seedu.address.testutil.TypicalModules.getTypicalModules;

import seedu.address.model.AddressBook;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.module.Module;

/**
 * A utility class containing an {@code AddressBook} object to be used in tests.
 */
public class TypicalAddressBook {
    /**
     * Returns an {@code AddressBook} with all the typical facilitators.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Module module : getTypicalModules()) {
            ab.addModule(module);
        }
        for (Facilitator facilitator : getTypicalFacilitators()) {
            ab.addFacilitator(facilitator);
        }

        for (Lesson lesson : getTypicalLessons()) {
            ab.addLesson(lesson);
        }

        return ab;
    }
}
