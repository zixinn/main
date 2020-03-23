package seedu.address.storage;

import java.util.ArrayList;
import java.util.IllformedLocaleException;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.task.Task;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "modmanager")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_MODULE = "Module list contains duplicate module(s).";
    public static final String MESSAGE_DUPLICATE_FACILITATOR = "Facilitator list contains duplicate facilitator(s).";
    public static final String MESSAGE_DUPLICATE_TASK = "Task list contains duplicate task(s).";
    public static final String MESSAGE_DUPLICATE_LESSON = "Lesson list contains duplicate lesson(s).";

    public static final String MESSAGE_MODULE_DOES_NOT_EXIST =
            "Facilitator list contains facilitator(s) whose module code does not exist in Mod Manager.";
    public static final String MESSAGE_TASK_DOES_NOT_EXIST =
            "Task list contains task(s) whose does not belong to any module in Mod Manager.";
    public static final String MESSAGE_FACILITATOR_WITHOUT_MODULE_CODE =
            "Facilitator list contains facilitator(s) whose module code is empty.";

    private final List<JsonAdaptedModule> modules = new ArrayList<>();
    private final List<JsonAdaptedFacilitator> facilitators = new ArrayList<>();
    private final List<JsonAdaptedTask> tasks = new ArrayList<>();
    private final List<JsonAdaptedLesson> lessons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given modules and facilitators.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("modules") List<JsonAdaptedModule> modules,
                                       @JsonProperty("facilitators") List<JsonAdaptedFacilitator> facilitators,
                                       @JsonProperty("tasks") List<JsonAdaptedTask> tasks,
                                       @JsonProperty("lessons") List<JsonAdaptedLesson> lessons) {
        if (modules != null) {
            this.modules.addAll(modules);
        }
        if (facilitators != null) {
            this.facilitators.addAll(facilitators);
        }
        if (tasks != null) {
            this.tasks.addAll(tasks);
        }

        if (lessons != null) {
            this.lessons.addAll(lessons);
        }
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        modules.addAll(source.getModuleList().stream().map(JsonAdaptedModule::new).collect(Collectors.toList()));
        facilitators.addAll(source.getFacilitatorList().stream().map(JsonAdaptedFacilitator::new)
                .collect(Collectors.toList()));
        tasks.addAll(source.getTaskList().stream().map(JsonAdaptedTask::new)
                .collect(Collectors.toList()));
        lessons.addAll(source.getLessonList().stream().map(JsonAdaptedLesson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook modManager = new AddressBook();
        for (JsonAdaptedModule jsonAdaptedModule : modules) {
            Module module = jsonAdaptedModule.toModelType();
            if (modManager.hasModule(module)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULE);
            }
            modManager.getModules().add(module);
        }
        for (JsonAdaptedFacilitator jsonAdaptedFacilitator : facilitators) {
            Facilitator facilitator = jsonAdaptedFacilitator.toModelType();
            if (modManager.hasFacilitator(facilitator)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_FACILITATOR);
            }
            if (facilitator.getModuleCodes().isEmpty()) {
                throw new IllformedLocaleException(MESSAGE_FACILITATOR_WITHOUT_MODULE_CODE);
            }
            for (ModuleCode moduleCode : facilitator.getModuleCodes()) {
                if (!modManager.hasModuleCode(moduleCode.value)) {
                    throw new IllegalValueException(MESSAGE_MODULE_DOES_NOT_EXIST);
                }
            }
            modManager.getFacilitators().add(facilitator);
        }
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (modManager.hasTask(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            /* to check later, 17 Mar 00:43
            for (ModuleCode moduleCode : tasks.getModuleCodes()) {
                if (!modManager.hasModuleCode(moduleCode.moduleCode)) {
                    throw new IllegalValueException(MESSAGE_MODULE_DOES_NOT_EXIST);
                }
            }
            */
            modManager.getTasks().add(task);
        }

        for (JsonAdaptedLesson jsonAdaptedLesson : lessons) {
            Lesson lesson = jsonAdaptedLesson.toModelType();
            if (modManager.hasLesson(lesson)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_LESSON);
            }
            modManager.getLessonList().add(lesson);
        }

        return modManager;
    }
}
