package seedu.address.testutil;

import seedu.address.logic.commands.module.ModuleEditCommand;
import seedu.address.model.module.Module;
import seedu.address.model.util.Description;

/**
 * A utility class to help with building EditModuleDescriptor objects.
 */
public class EditModuleDescriptorBuilder {

    private ModuleEditCommand.EditModuleDescriptor descriptor;

    public EditModuleDescriptorBuilder() {
        descriptor = new ModuleEditCommand.EditModuleDescriptor();
    }

    public EditModuleDescriptorBuilder(ModuleEditCommand.EditModuleDescriptor descriptor) {
        this.descriptor = new ModuleEditCommand.EditModuleDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditModuleDescriptor} with fields containing {@code module}'s details
     */
    public EditModuleDescriptorBuilder(Module module) {
        descriptor = new ModuleEditCommand.EditModuleDescriptor();
        descriptor.setDescription(module.getDescription());
    }

    /**
     * Sets the {@code Description} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    public ModuleEditCommand.EditModuleDescriptor build() {
        return descriptor;
    }
}
