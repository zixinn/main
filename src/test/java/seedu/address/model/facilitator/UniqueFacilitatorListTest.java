package seedu.address.model.facilitator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OFFICE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFacilitators.ALICE;
import static seedu.address.testutil.TypicalFacilitators.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.facilitator.exceptions.DuplicateFacilitatorException;
import seedu.address.model.facilitator.exceptions.FacilitatorNotFoundException;
import seedu.address.testutil.FacilitatorBuilder;

public class UniqueFacilitatorListTest {

    private final UniqueFacilitatorList uniqueFacilitatorList = new UniqueFacilitatorList();

    @Test
    public void contains_nullFacilitator_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFacilitatorList.contains(null));
    }

    @Test
    public void contains_facilitatorNotInList_returnsFalse() {
        assertFalse(uniqueFacilitatorList.contains(ALICE));
    }

    @Test
    public void contains_facilitatorInList_returnsTrue() {
        uniqueFacilitatorList.add(ALICE);
        assertTrue(uniqueFacilitatorList.contains(ALICE));
    }

    @Test
    public void contains_facilitatorWithSameIdentityFieldsInList_returnsTrue() {
        uniqueFacilitatorList.add(ALICE);
        Facilitator editedAlice = new FacilitatorBuilder(ALICE).withOffice(VALID_OFFICE_BOB)
                .withModuleCodes(VALID_MODULE_CODE_CS2103T).build();
        assertTrue(uniqueFacilitatorList.contains(editedAlice));
    }

    @Test
    public void add_nullFacilitator_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFacilitatorList.add(null));
    }

    @Test
    public void add_duplicateFacilitator_throwsDuplicateFacilitatorException() {
        uniqueFacilitatorList.add(ALICE);
        assertThrows(DuplicateFacilitatorException.class, () -> uniqueFacilitatorList.add(ALICE));
    }

    @Test
    public void setFacilitator_nullTargetFacilitator_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFacilitatorList.setFacilitator(null, ALICE));
    }

    @Test
    public void setFacilitator_nullEditedFacilitator_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFacilitatorList.setFacilitator(ALICE, null));
    }

    @Test
    public void setFacilitator_targetFacilitatorNotInList_throwsFacilitatorNotFoundException() {
        assertThrows(FacilitatorNotFoundException.class, () -> uniqueFacilitatorList.setFacilitator(ALICE, ALICE));
    }

    @Test
    public void setFacilitator_editedFacilitatorIsSameFacilitator_success() {
        uniqueFacilitatorList.add(ALICE);
        uniqueFacilitatorList.setFacilitator(ALICE, ALICE);
        UniqueFacilitatorList expectedUniqueFacilitatorList = new UniqueFacilitatorList();
        expectedUniqueFacilitatorList.add(ALICE);
        assertEquals(expectedUniqueFacilitatorList, uniqueFacilitatorList);
    }

    @Test
    public void setFacilitator_editedFacilitatorHasSameIdentity_success() {
        uniqueFacilitatorList.add(ALICE);
        Facilitator editedAlice = new FacilitatorBuilder(ALICE).withOffice(VALID_OFFICE_BOB)
                .build();
        uniqueFacilitatorList.setFacilitator(ALICE, editedAlice);
        UniqueFacilitatorList expectedUniqueFacilitatorList = new UniqueFacilitatorList();
        expectedUniqueFacilitatorList.add(editedAlice);
        assertEquals(expectedUniqueFacilitatorList, uniqueFacilitatorList);
    }

    @Test
    public void setFacilitator_editedFacilitatorHasDifferentIdentity_success() {
        uniqueFacilitatorList.add(ALICE);
        uniqueFacilitatorList.setFacilitator(ALICE, BOB);
        UniqueFacilitatorList expectedUniqueFacilitatorList = new UniqueFacilitatorList();
        expectedUniqueFacilitatorList.add(BOB);
        assertEquals(expectedUniqueFacilitatorList, uniqueFacilitatorList);
    }

    @Test
    public void setFacilitator_editedFacilitatorHasNonUniqueIdentity_throwsDuplicateFacilitatorException() {
        uniqueFacilitatorList.add(ALICE);
        uniqueFacilitatorList.add(BOB);
        assertThrows(DuplicateFacilitatorException.class, () -> uniqueFacilitatorList.setFacilitator(ALICE, BOB));
    }

    @Test
    public void remove_nullFacilitator_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFacilitatorList.remove(null));
    }

    @Test
    public void remove_facilitatorDoesNotExist_throwsFacilitatorNotFoundException() {
        assertThrows(FacilitatorNotFoundException.class, () -> uniqueFacilitatorList.remove(ALICE));
    }

    @Test
    public void remove_existingFacilitator_removesFacilitator() {
        uniqueFacilitatorList.add(ALICE);
        uniqueFacilitatorList.remove(ALICE);
        UniqueFacilitatorList expectedUniqueFacilitatorList = new UniqueFacilitatorList();
        assertEquals(expectedUniqueFacilitatorList, uniqueFacilitatorList);
    }

    @Test
    public void setFacilitators_nullUniqueFacilitatorList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFacilitatorList
                .setFacilitators((UniqueFacilitatorList) null));
    }

    @Test
    public void setFacilitators_uniqueFacilitatorList_replacesOwnListWithProvidedUniqueFacilitatorList() {
        uniqueFacilitatorList.add(ALICE);
        UniqueFacilitatorList expectedUniqueFacilitatorList = new UniqueFacilitatorList();
        expectedUniqueFacilitatorList.add(BOB);
        uniqueFacilitatorList.setFacilitators(expectedUniqueFacilitatorList);
        assertEquals(expectedUniqueFacilitatorList, uniqueFacilitatorList);
    }

    @Test
    public void setFacilitators_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFacilitatorList.setFacilitators((List<Facilitator>) null));
    }

    @Test
    public void setFacilitators_list_replacesOwnListWithProvidedList() {
        uniqueFacilitatorList.add(ALICE);
        List<Facilitator> facilitatorList = Collections.singletonList(BOB);
        uniqueFacilitatorList.setFacilitators(facilitatorList);
        UniqueFacilitatorList expectedUniqueFacilitatorList = new UniqueFacilitatorList();
        expectedUniqueFacilitatorList.add(BOB);
        assertEquals(expectedUniqueFacilitatorList, uniqueFacilitatorList);
    }

    @Test
    public void setFacilitators_listWithDuplicateFacilitators_throwsDuplicateFacilitatorException() {
        List<Facilitator> listWithDuplicateFacilitators = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateFacilitatorException.class, () -> uniqueFacilitatorList
                .setFacilitators(listWithDuplicateFacilitators));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueFacilitatorList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void getFacilitatorList_emptyList_success() {
        ObservableList<Facilitator> internalList = FXCollections.observableArrayList();
        assertEquals(internalList, uniqueFacilitatorList.getFacilitatorList());
    }

    @Test
    public void getFacilitatorList_nonEmptyList_success() {
        ObservableList<Facilitator> internalList = FXCollections.observableArrayList();
        internalList.add(ALICE);
        uniqueFacilitatorList.add(ALICE);
        assertEquals(internalList, uniqueFacilitatorList.getFacilitatorList());
    }
}
