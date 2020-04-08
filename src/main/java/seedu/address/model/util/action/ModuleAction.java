package seedu.address.model.util.action;

import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.module.Module;
import seedu.address.model.task.Task;
import seedu.address.model.task.util.TaskNumManager;

/**
 * Represents a DoableAction involving a {@code Module}.
 */
public class ModuleAction implements DoableAction<Module> {
    private final Module target;
    private final Module replacement;
    private final DoableActionType type;
    private List<Lesson> relatedLessons;
    private List<Task> relatedTask;
    private List<Facilitator> relatedFacil;

    public ModuleAction(Module target, DoableActionType type) {
        assert type == DoableActionType.ADD;

        this.target = target;
        this.replacement = null;
        this.type = type;
    }

    public ModuleAction(Module target, Module replacement, DoableActionType type) {
        assert type == DoableActionType.EDIT;

        this.target = target;
        this.replacement = replacement;
        this.type = type;
    }

    public ModuleAction(Module target, List<Lesson> relatedLessons, List<Task> relatedTask,
                        List<Facilitator> relatedFacil, DoableActionType type) {
        assert type == DoableActionType.DELETE;

        this.target = target;
        this.replacement = null;
        this.relatedLessons = relatedLessons;
        this.relatedTask = relatedTask;
        this.relatedFacil = relatedFacil;
        this.type = type;
    }

    @Override
    public void undo(Model model) {
        switch (type) {
        case ADD:
            model.deleteModule(target);
            break;
        case EDIT:
            assert replacement != null;
            model.setModule(replacement, target);
            break;
        case DELETE:
            model.addModule(target);
            relatedLessons.forEach(model::addLesson);
            relatedTask.forEach(t -> {
                TaskNumManager.addNum(target.getModuleCode(), t.getTaskNum());
                model.addTask(t);
            });
            relatedFacil.forEach(f -> {
                f.getModuleCodes().add(target.getModuleCode());
                if (!model.hasFacilitator(f)) {
                    model.addFacilitator(f);
                }
            });
            break;
        default:
            break;
        }
    }

    @Override
    public void redo(Model model) {
        switch (type) {
        case ADD:
            model.addModule(target);
            break;
        case EDIT:
            assert replacement != null;
            model.setModule(target, replacement);
            break;
        case DELETE:
            model.deleteModule(target);
            relatedLessons.forEach(model::removeLesson);
            relatedTask.forEach(model::deleteTask);
            model.deleteModuleCodeFromFacilitatorList(target.getModuleCode());
            break;
        default:
            break;
        }
    }

    @Override
    public Module getTarget() {
        return this.target;
    }

    @Override
    public DoableActionType getType() {
        return this.type;
    }
}
