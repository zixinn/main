package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand;
import seedu.address.model.facilitator.Address;
import seedu.address.model.facilitator.Email;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.model.facilitator.Name;
import seedu.address.model.facilitator.Phone;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditFacilitatorDescriptor objects.
 */
public class EditFacilitatorDescriptorBuilder {

    private EditCommand.EditFacilitatorDescriptor descriptor;

    public EditFacilitatorDescriptorBuilder() {
        descriptor = new EditCommand.EditFacilitatorDescriptor();
    }

    public EditFacilitatorDescriptorBuilder(EditCommand.EditFacilitatorDescriptor descriptor) {
        this.descriptor = new EditCommand.EditFacilitatorDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditFacilitatorDescriptor} with fields containing {@code facilitator}'s details
     */
    public EditFacilitatorDescriptorBuilder(Facilitator facilitator) {
        descriptor = new EditCommand.EditFacilitatorDescriptor();
        descriptor.setName(facilitator.getName());
        descriptor.setPhone(facilitator.getPhone());
        descriptor.setEmail(facilitator.getEmail());
        descriptor.setAddress(facilitator.getAddress());
        descriptor.setTags(facilitator.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditFacilitatorDescriptor} that we are building.
     */
    public EditFacilitatorDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditFacilitatorDescriptor} that we are building.
     */
    public EditFacilitatorDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditFacilitatorDescriptor} that we are building.
     */
    public EditFacilitatorDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditFacilitatorDescriptor} that we are building.
     */
    public EditFacilitatorDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditFacilitatorDescriptor}
     * that we are building.
     */
    public EditFacilitatorDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommand.EditFacilitatorDescriptor build() {
        return descriptor;
    }
}
