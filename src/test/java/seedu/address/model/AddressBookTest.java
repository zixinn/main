package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OFFICE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFacilitators.ALICE;
import static seedu.address.testutil.TypicalFacilitators.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.model.facilitator.exceptions.DuplicateFacilitatorException;
import seedu.address.testutil.FacilitatorBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getFacilitatorList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateFacilitators_throwsDuplicateFacilitatorException() {
        // Two facilitators with the same identity fields
        Facilitator editedAlice = new FacilitatorBuilder(ALICE).withOffice(VALID_OFFICE_BOB)
                .withModuleCodes(VALID_MODULE_CODE_CS2103T).build();
        List<Facilitator> newFacilitators = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newFacilitators);

        assertThrows(DuplicateFacilitatorException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasFacilitator_nullFacilitator_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasFacilitator(null));
    }

    @Test
    public void hasFacilitator_facilitatorNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasFacilitator(ALICE));
    }

    @Test
    public void hasFacilitator_facilitatorInAddressBook_returnsTrue() {
        addressBook.addFacilitator(ALICE);
        assertTrue(addressBook.hasFacilitator(ALICE));
    }

    @Test
    public void hasFacilitator_facilitatorWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addFacilitator(ALICE);
        Facilitator editedAlice = new FacilitatorBuilder(ALICE).withOffice(VALID_OFFICE_BOB)
                .withModuleCodes(VALID_MODULE_CODE_CS2103T).build();
        assertTrue(addressBook.hasFacilitator(editedAlice));
    }

    @Test
    public void getFacilitatorList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getFacilitatorList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose facilitators list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Facilitator> facilitators = FXCollections.observableArrayList();

        AddressBookStub(Collection<Facilitator> facilitators) {
            this.facilitators.setAll(facilitators);
        }

        @Override
        public ObservableList<Facilitator> getFacilitatorList() {
            return facilitators;
        }
    }

}
