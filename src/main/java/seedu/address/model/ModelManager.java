package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonList;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.task.Task;

/**
 * Represents the in-memory model of Mod Manager data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Facilitator> filteredFacilitators;
    private final FilteredList<Module> filteredModules;
    private final FilteredList<Task> filteredTasks;
    private final List<Lesson> filteredLesson;
    private Optional<Module> module;
    private final FilteredList<Facilitator> facilitatorsForModule;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with Mod Manager: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredFacilitators = new FilteredList<>(this.addressBook.getFacilitatorList());
        filteredModules = new FilteredList<>(this.addressBook.getModuleList());
        filteredTasks = new FilteredList<>(this.addressBook.getTaskList());
        filteredLesson = this.addressBook.getLessonList();
        module = Optional.empty();
        facilitatorsForModule = new FilteredList<>(this.addressBook.getFacilitatorList());
        facilitatorsForModule.setPredicate(PREDICATE_SHOW_NO_FACILITATORS);
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    //=========== Module ================================================================================

    @Override
    public boolean hasModuleCode(String moduleCode) {
        requireNonNull(moduleCode);
        return addressBook.hasModuleCode(moduleCode);
    }

    @Override
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return addressBook.hasModule(module);
    }

    @Override
    public void deleteModule(Module target) {
        addressBook.removeModule(target);
    }

    @Override
    public void addModule(Module module) {
        addressBook.addModule(module);
        updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
    }

    @Override
    public void setModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);

        addressBook.setModule(target, editedModule);
    }

    @Override
    public Optional<Module> getModule() {
        return module;
    }

    @Override
    public Optional<Module> findModule(ModuleCode moduleCode) {
        return addressBook.findModule(moduleCode);
    }

    @Override
    public void updateModule(Module module) {
        requireNonNull(module);
        this.module = Optional.of(module);
    }

    //=========== Filtered Module List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Module} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return filteredModules;
    }

    @Override
    public void updateFilteredModuleList(Predicate<Module> predicate) {
        requireNonNull(predicate);
        filteredModules.setPredicate(predicate);
    }

    //=========== Facilitator ================================================================================

    @Override
    public boolean hasFacilitator(Facilitator facilitator) {
        requireNonNull(facilitator);
        return addressBook.hasFacilitator(facilitator);
    }

    @Override
    public void deleteFacilitator(Facilitator target) {
        addressBook.removeFacilitator(target);
    }

    @Override
    public void addFacilitator(Facilitator facilitator) {
        addressBook.addFacilitator(facilitator);
        updateFilteredFacilitatorList(PREDICATE_SHOW_ALL_FACILITATORS);
    }

    @Override
    public void setFacilitator(Facilitator target, Facilitator editedFacilitator) {
        requireAllNonNull(target, editedFacilitator);

        addressBook.setFacilitator(target, editedFacilitator);
    }

    //=========== Filtered Facilitator List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Facilitator} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Facilitator> getFilteredFacilitatorList() {
        return filteredFacilitators;
    }

    @Override
    public void updateFilteredFacilitatorList(Predicate<Facilitator> predicate) {
        requireNonNull(predicate);
        filteredFacilitators.setPredicate(predicate);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Facilitator} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Facilitator> getFacilitatorListForModule() {
        return facilitatorsForModule;
    }

    @Override
    public void updateFacilitatorListForModule(Predicate<Facilitator> predicate) {
        requireNonNull(predicate);
        facilitatorsForModule.setPredicate(predicate);
    }

    //=========== Lesson =========================================================================================
    @Override
    public boolean hasLesson(Lesson lesson) {
        requireNonNull(lesson);
        return addressBook.hasLesson(lesson);
    }

    @Override
    public void addLesson(Lesson lesson) {
        requireNonNull(lesson);
        addressBook.addLesson(lesson);
    }

    @Override
    public void setLesson(Lesson target, Lesson edited) {
        requireAllNonNull(target, edited);
        addressBook.setLesson(target, edited);
    }

    @Override
    public void removeLesson(Lesson lesson) {
        requireNonNull(lesson);
        addressBook.removeLesson(lesson);
    }

    @Override
    public Lesson findNextLesson() {
        LessonList lessons = addressBook.getLessons();
        return lessons.findNextLesson();
    }

    @Override
    public List<Lesson> findLessonByDay(DayOfWeek day) {
        return addressBook.getLessons().findLessonsByDay(day);
    }

    @Override
    public List<Lesson> getLessons() {
        return addressBook.getLessonList();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredModules.equals(other.filteredModules)
                && filteredFacilitators.equals(other.filteredFacilitators)
                && facilitatorsForModule.equals(other.facilitatorsForModule);
    }

    //=========== Task ================================================================================

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return addressBook.hasTask(task);
    }

    @Override
    public void deleteTask(Task task) {
        addressBook.removeTask(task);
    }

    @Override
    public void addTask(Task task) {
        addressBook.addTask(task);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        addressBook.setTask(target, editedTask);
    }

    //=========== Filtered Task List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }
}
