package seedu.address.logic.commands.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.facilitator.ModuleCodesContainKeywordPredicate;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.util.Description;

/**
 * Edits the details of an existing module in Mod Manager.
 */
public class ModuleEditCommand extends ModuleCommand {

    public static final String MESSAGE_USAGE = COMMAND_GROUP_MOD + " " + COMMAND_WORD_EDIT
            + ": Edits the details of the module identified "
            + "by the index number used in the displayed module list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_MODULE_CODE + " MOD_CODE] "
            + "[" + PREFIX_DESCRIPTION + " DESCRIPTION]\n"
            + "Example: " + COMMAND_GROUP_MOD + " " + COMMAND_WORD_EDIT + " 1 "
            + PREFIX_DESCRIPTION + "new description";

    public static final String MESSAGE_EDIT_MODULE_SUCCESS = "Edited Module: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in Mod Manager.";

    private final Index index;
    private final ModuleEditCommand.EditModuleDescriptor editModuleDescriptor;

    /**
     * Creates a ModuleEditCommand to edit the module at the specified {@code index}.
     *
     * @param index of the module in the filtered module list to edit.
     * @param editModuleDescriptor details to edit the module with.
     */
    public ModuleEditCommand(Index index, ModuleEditCommand.EditModuleDescriptor editModuleDescriptor) {
        requireNonNull(index);
        requireNonNull(editModuleDescriptor);

        this.index = index;
        this.editModuleDescriptor = new ModuleEditCommand.EditModuleDescriptor(editModuleDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        Module moduleToEdit = lastShownList.get(index.getZeroBased());
        Module editedModule = createEditedModule(moduleToEdit, editModuleDescriptor);

        if (!moduleToEdit.isSameModule(editedModule) && model.hasModule(editedModule)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.setModule(moduleToEdit, editedModule);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        model.setModuleCodeInFacilitatorList(moduleToEdit.getModuleCode(), editedModule.getModuleCode());

        if (model.getModule().isPresent() && model.getModule().get().equals(moduleToEdit)) {
            model.updateModule(Optional.of(editedModule));
            model.updateFacilitatorListForModule(
                    new ModuleCodesContainKeywordPredicate(editedModule.getModuleCode().value));
        }

        return new CommandResult(String.format(MESSAGE_EDIT_MODULE_SUCCESS, editedModule),
                CommandType.MODULE);
    }

    /**
     * Creates and returns a {@code Module} with the details of {@code moduleToEdit}
     * edited with {@code editModuleDescriptor}.
     */
    private static Module createEditedModule(Module moduleToEdit,
                ModuleEditCommand.EditModuleDescriptor editModuleDescriptor) {
        assert moduleToEdit != null;

        ModuleCode updatedModuleCode = editModuleDescriptor.getModuleCode().orElse(moduleToEdit.getModuleCode());
        Description updatedDescription = editModuleDescriptor.getDescription().orElse(moduleToEdit.getDescription());

        return new Module(updatedModuleCode, updatedDescription);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleEditCommand)) {
            return false;
        }

        // state check
        ModuleEditCommand e = (ModuleEditCommand) other;
        return index.equals(e.index)
                && editModuleDescriptor.equals(e.editModuleDescriptor);
    }

    /**
     * Stores the details to edit the module with. Each non-empty field value will replace the
     * corresponding field value of the module.
     */
    public static class EditModuleDescriptor {
        private ModuleCode moduleCode;
        private Description description;

        public EditModuleDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditModuleDescriptor(ModuleEditCommand.EditModuleDescriptor toCopy) {
            setModuleCode(toCopy.moduleCode);
            setDescription(toCopy.description);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(moduleCode, description);
        }

        public void setModuleCode(ModuleCode moduleCode) {
            this.moduleCode = moduleCode;
        }

        public Optional<ModuleCode> getModuleCode() {
            return Optional.ofNullable(moduleCode);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof ModuleEditCommand.EditModuleDescriptor)) {
                return false;
            }

            // state check
            ModuleEditCommand.EditModuleDescriptor e = (ModuleEditCommand.EditModuleDescriptor) other;

            return getModuleCode().equals(e.getModuleCode())
                    && getDescription().equals(e.getDescription());
        }
    }
}
