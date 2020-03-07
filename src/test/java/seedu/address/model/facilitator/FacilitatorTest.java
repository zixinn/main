package seedu.address.model.facilitator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFacilitators.ALICE;
import static seedu.address.testutil.TypicalFacilitators.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.FacilitatorBuilder;

public class FacilitatorTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Facilitator facilitator = new FacilitatorBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> facilitator.getTags().remove(0));
    }

    @Test
    public void isSameFacilitator() {
        // same object -> returns true
        assertTrue(ALICE.isSameFacilitator(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameFacilitator(null));

        // different phone and email -> returns false
        Facilitator editedAlice = new FacilitatorBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .build();
        assertFalse(ALICE.isSameFacilitator(editedAlice));

        // different name -> returns false
        editedAlice = new FacilitatorBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameFacilitator(editedAlice));

        // same name, same phone, different attributes -> returns true
        editedAlice = new FacilitatorBuilder(ALICE).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameFacilitator(editedAlice));

        // same name, same email, different attributes -> returns true
        editedAlice = new FacilitatorBuilder(ALICE).withPhone(VALID_PHONE_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameFacilitator(editedAlice));

        // same name, same phone, same email, different attributes -> returns true
        editedAlice = new FacilitatorBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameFacilitator(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Facilitator aliceCopy = new FacilitatorBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different facilitator -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Facilitator editedAlice = new FacilitatorBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new FacilitatorBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new FacilitatorBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new FacilitatorBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new FacilitatorBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
