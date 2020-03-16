package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.model.facilitator.UniqueFacilitatorList;
import seedu.address.model.module.Module;
import seedu.address.model.module.UniqueModuleList;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameModule and .isSameFacilitator and .isSameTask comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueModuleList modules;
    private final UniqueFacilitatorList facilitators;
    private final UniqueTaskList tasks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        modules = new UniqueModuleList();
        facilitators = new UniqueFacilitatorList();
        tasks = new UniqueTaskList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Modules and Facilitators in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the module list with {@code modules}.
     * {@code modules} must not contain duplicate modules.
     */
    public void setModules(List<Module> modules) {
        this.modules.setModules(modules);
    }

    /**
     * Replaces the contents of the facilitator list with {@code facilitators}.
     * {@code facilitators} must not contain duplicate facilitators.
     */
    public void setFacilitators(List<Facilitator> facilitators) {
        this.facilitators.setFacilitators(facilitators);
    }

    /**
     * Replaces the contents of the task list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setFacilitators(newData.getFacilitatorList());
        setModules(newData.getModuleList());
        setTasks(newData.getTaskList());
    }

    //// module-level operations

    /**
     * Returns true if a module with the same module code as {@code moduleCode} exists in Mod Manager.
     */
    public boolean hasModuleCode(String moduleCode) {
        requireNonNull(moduleCode);
        return modules.containsModuleCode(moduleCode);
    }

    /**
     * Returns true if a module with the same identity as {@code module} exists in Mod Manager.
     */
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return modules.contains(module);
    }

    /**
     * Adds a module to the Mod Manager.
     * The module must not already exist in Mod Manager.
     */
    public void addModule(Module p) {
        modules.add(p);
    }

    /**
     * Replaces the given module {@code target} in the list with {@code editedModule}.
     * {@code target} must exist in Mod Manager.
     * The module identity of {@code editedModule} must not be the same as another existing module
     * in Mod Manager.
     */
    public void setModule(Module target, Module editedModule) {
        requireNonNull(editedModule);

        modules.setModule(target, editedModule);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in Mod Manager.
     */
    public void removeModule(Module key) {
        modules.remove(key);
    }

    /**
     * Returns the list of modules in this {@code AddressBook}.
     * @return the list of modules.
     */
    public List<Module> getModules() {
        return modules.getModuleList();
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

    /**
     * Returns the list of modules in this {@code AddressBook}.
     * @return the list of modules.
     */
    public List<Facilitator> getFacilitators() {
        return facilitators.getFacilitatorList();
    }

    //// task-level operations

    /**
     * Returns true if a facilitator with the same identity as {@code facilitator} exists in Mod Manager.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Adds a task to the Mod Manager.
     * The task must not already exist in Mod Manager.
     */
    public void addTask(Task p) {
        tasks.add(p);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in Mod Manager.
     * The task identity of {@code editedTask} must not be the same as another existing task
     * in Mod Manager.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);

        tasks.setTask(target, editedTask);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in Mod Manager.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
    }

    /**
     * Returns the list of modules in this {@code AddressBook}.
     * @return the list of modules.
     */
    public List<Task> getTasks() {
        return tasks.getTaskList();
    }

    //// util methods

    @Override
    public String toString() {
        return modules.asUnmodifiableObservableList().size() + " modules \n"
                + tasks.asUnmodifiableObservableList().size() + " tasks";
        // TODO: refine later
    }

    @Override
    public ObservableList<Facilitator> getFacilitatorList() {
        return facilitators.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Module> getModuleList() {
        return modules.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && modules.equals(((AddressBook) other).modules)
                && facilitators.equals(((AddressBook) other).facilitators)
                && tasks.equals(((AddressBook) other).tasks));
    }

    @Override
    public int hashCode() {
        return Objects.hash(modules, facilitators, tasks);
    }
}
