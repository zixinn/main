package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_FACILITATORS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.facilitator.Address;
import seedu.address.model.facilitator.Email;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.model.facilitator.Name;
import seedu.address.model.facilitator.Phone;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing facilitator in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the facilitator identified "
            + "by the index number used in the displayed facilitator list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_FACILITATOR_SUCCESS = "Edited Facilitator: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_FACILITATOR = "This facilitator already exists in the address book.";

    private final Index index;
    private final EditFacilitatorDescriptor editFacilitatorDescriptor;

    /**
     * @param index of the facilitator in the filtered facilitator list to edit
     * @param editFacilitatorDescriptor details to edit the facilitator with
     */
    public EditCommand(Index index, EditFacilitatorDescriptor editFacilitatorDescriptor) {
        requireNonNull(index);
        requireNonNull(editFacilitatorDescriptor);

        this.index = index;
        this.editFacilitatorDescriptor = new EditFacilitatorDescriptor(editFacilitatorDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Facilitator> lastShownList = model.getFilteredFacilitatorList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FACILITATOR_DISPLAYED_INDEX);
        }

        Facilitator facilitatorToEdit = lastShownList.get(index.getZeroBased());
        Facilitator editedFacilitator = createEditedFacilitator(facilitatorToEdit, editFacilitatorDescriptor);

        if (!facilitatorToEdit.isSameFacilitator(editedFacilitator) && model.hasFacilitator(editedFacilitator)) {
            throw new CommandException(MESSAGE_DUPLICATE_FACILITATOR);
        }

        model.setFacilitator(facilitatorToEdit, editedFacilitator);
        model.updateFilteredFacilitatorList(PREDICATE_SHOW_ALL_FACILITATORS);
        return new CommandResult(String.format(MESSAGE_EDIT_FACILITATOR_SUCCESS, editedFacilitator));
    }

    /**
     * Creates and returns a {@code Facilitator} with the details of {@code facilitatorToEdit}
     * edited with {@code editFacilitatorDescriptor}.
     */
    private static Facilitator createEditedFacilitator(Facilitator facilitatorToEdit,
                EditFacilitatorDescriptor editFacilitatorDescriptor) {
        assert facilitatorToEdit != null;

        Name updatedName = editFacilitatorDescriptor.getName().orElse(facilitatorToEdit.getName());
        Phone updatedPhone = editFacilitatorDescriptor.getPhone().orElse(facilitatorToEdit.getPhone());
        Email updatedEmail = editFacilitatorDescriptor.getEmail().orElse(facilitatorToEdit.getEmail());
        Address updatedAddress = editFacilitatorDescriptor.getAddress().orElse(facilitatorToEdit.getAddress());
        Set<Tag> updatedTags = editFacilitatorDescriptor.getTags().orElse(facilitatorToEdit.getTags());

        return new Facilitator(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editFacilitatorDescriptor.equals(e.editFacilitatorDescriptor);
    }

    /**
     * Stores the details to edit the facilitator with. Each non-empty field value will replace the
     * corresponding field value of the facilitator.
     */
    public static class EditFacilitatorDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;

        public EditFacilitatorDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditFacilitatorDescriptor(EditFacilitatorDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditFacilitatorDescriptor)) {
                return false;
            }

            // state check
            EditFacilitatorDescriptor e = (EditFacilitatorDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }
    }
}
