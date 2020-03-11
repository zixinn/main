package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.facilitator.Facilitator;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withFacilitator("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Facilitator} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withFacilitator(Facilitator facilitator) {
        addressBook.addFacilitator(facilitator);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
