package seedu.address.model.util.action;

import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Represents a DoableAction involving a {@code Module}.
 */
public class ModuleAction implements DoableAction<Module> {
    private final Module target;
    private final Module replacement;
    private final DoableActionType type;

    public ModuleAction(Module target, DoableActionType type) {
        assert type != DoableActionType.EDIT;

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
