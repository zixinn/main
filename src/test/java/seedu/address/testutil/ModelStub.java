package seedu.address.testutil;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.module.Module;

/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getAddressBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasModule(Module module) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteModule(Module target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addModule(Module module) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setModule(Module target, Module editedModule) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Module> getFilteredModuleList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredModuleList(Predicate<Module> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasFacilitator(Facilitator facilitator) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteFacilitator(Facilitator target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addFacilitator(Facilitator facilitator) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setFacilitator(Facilitator target, Facilitator editedFacilitator) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Facilitator> getFilteredFacilitatorList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredFacilitatorList(Predicate<Facilitator> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasLesson(Lesson lesson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addLesson(Lesson lesson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setLesson(Lesson target, Lesson edited) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void removeLesson(Lesson lesson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public List<Lesson> getLessons() {
        throw new AssertionError("This method should not be called.");
    }
}
