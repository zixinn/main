package seedu.address.testutil;

import seedu.address.model.ModManager;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.model.module.Module;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code ModManager ab = new AddressBookBuilder().withFacilitator("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private ModManager modManager;

    public AddressBookBuilder() {
        modManager = new ModManager();
    }

    public AddressBookBuilder(ModManager modManager) {
        this.modManager = modManager;
    }

    /**
     * Adds a new {@code Module} to the {@code ModManager} that we are building.
     */
    public AddressBookBuilder withModule(Module module) {
        modManager.addModule(module);
        return this;
    }

    /**
     * Adds a new {@code Facilitator} to the {@code ModManager} that we are building.
     */
    public AddressBookBuilder withFacilitator(Facilitator facilitator) {
        modManager.addFacilitator(facilitator);
        return this;
    }

    public ModManager build() {
        return modManager;
    }
}
