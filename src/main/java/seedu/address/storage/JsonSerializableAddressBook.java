package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.model.module.Module;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "modmanager")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_FACILITATOR = "Facilitator list contains duplicate facilitator(s).";
    public static final String MESSAGE_DUPLICATE_MODULE = "Module list contains duplicate module(s).";

    private final List<JsonAdaptedModule> modules = new ArrayList<>();
    private final List<JsonAdaptedFacilitator> facilitators = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given modules and facilitators.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("modules") List<JsonAdaptedModule> modules,
                                       @JsonProperty("facilitators") List<JsonAdaptedFacilitator> facilitators) {
        if (modules != null) {
            this.modules.addAll(modules);
        }
        if (facilitators != null) {
            this.facilitators.addAll(facilitators);
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
            modManager.getFacilitators().add(facilitator);
        }
        return modManager;
    }
}
