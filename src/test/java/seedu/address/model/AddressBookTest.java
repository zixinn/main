package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OFFICE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalFacilitators.ALICE;
import static seedu.address.testutil.TypicalModules.CS2103T;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.model.facilitator.UniqueFacilitatorList;
import seedu.address.model.facilitator.exceptions.DuplicateFacilitatorException;
import seedu.address.model.module.Module;
import seedu.address.model.module.UniqueModuleList;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.testutil.FacilitatorBuilder;
import seedu.address.testutil.ModuleBuilder;

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
        AddressBookStub newData = new AddressBookStub(new ArrayList<>(), newFacilitators);

        assertThrows(DuplicateFacilitatorException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateModules_throwsDuplicateModuleException() {
        // Two modules with the same identity fields
        Module otherModule = new ModuleBuilder(CS2103T).withDescription(VALID_DESCRIPTION_CS2101).build();
        List<Module> newModules = Arrays.asList(CS2103T, otherModule);
        AddressBookStub newData = new AddressBookStub(newModules, new ArrayList<>());

        assertThrows(DuplicateModuleException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasModuleCode_nullModuleCode_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasModuleCode(null));
    }

    @Test
    public void hasModuleCode_moduleCodeNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasModuleCode("CS2103T"));
    }

    @Test
    public void hasModuleCode_moduleCodeInAddressBook_returnsTrue() {
        addressBook.addModule(CS2103T);
        assertTrue(addressBook.hasModuleCode("CS2103T"));
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasModule(null));
    }

    @Test
    public void hasModule_moduleNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasModule(CS2103T));
    }

    @Test
    public void hasModule_moduleInAddressBook_returnsTrue() {
        addressBook.addModule(CS2103T);
        assertTrue(addressBook.hasModule(CS2103T));
    }

    @Test
    public void hasModule_moduleWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addModule(CS2103T);
        Module editedModule = new ModuleBuilder().withDescription(VALID_DESCRIPTION_CS2101).build();
        assertTrue(addressBook.hasModule(editedModule));
    }

    @Test
    public void getModules_emptyList_success() {
        UniqueModuleList modules = new UniqueModuleList();
        assertEquals(modules.getModuleList(), addressBook.getModules());
    }

    @Test
    public void getModules_nonEmptyList_success() {
        addressBook.addModule(CS2103T);
        UniqueModuleList modules = new UniqueModuleList();
        modules.add(CS2103T);
        assertEquals(modules.getModuleList(), addressBook.getModules());
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
    public void getFacilitators_emptyList_success() {
        UniqueFacilitatorList facilitators = new UniqueFacilitatorList();
        assertEquals(facilitators.getFacilitatorList(), addressBook.getFacilitators());
    }

    @Test
    public void getFacilitators_nonEmptyList_success() {
        addressBook.addFacilitator(ALICE);
        UniqueFacilitatorList facilitators = new UniqueFacilitatorList();
        facilitators.add(ALICE);
        assertEquals(facilitators.getFacilitatorList(), addressBook.getFacilitators());
    }

    @Test
    public void getAddressBook_emptyAddressBook_success() {
        assertEquals(new AddressBook(), addressBook);
    }

    @Test
    public void getModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getModuleList().remove(0));
    }

    @Test
    public void getFacilitatorList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getFacilitatorList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose modules and facilitators list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Module> modules = FXCollections.observableArrayList();
        private final ObservableList<Facilitator> facilitators = FXCollections.observableArrayList();

        AddressBookStub(Collection<Module> modules, Collection<Facilitator> facilitators) {
            this.modules.setAll(modules);
            this.facilitators.setAll(facilitators);
        }

        @Override
        public ObservableList<Facilitator> getFacilitatorList() {
            return facilitators;
        }

        @Override
        public ObservableList<Module> getModuleList() {
            return modules;
        }
    }

}
