package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedFacilitator.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFacilitators.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.facilitator.Email;
import seedu.address.model.facilitator.Name;
import seedu.address.model.facilitator.Office;
import seedu.address.model.facilitator.Phone;

public class JsonAdaptedFacilitatorTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_OFFICE = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_OFFICE = BENSON.getOffice().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validFacilitatorDetails_returnsFacilitator() throws Exception {
        JsonAdaptedFacilitator facilitator = new JsonAdaptedFacilitator(BENSON);
        assertEquals(BENSON, facilitator.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedFacilitator facilitator =
                new JsonAdaptedFacilitator(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_OFFICE, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, facilitator::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedFacilitator facilitator = new JsonAdaptedFacilitator(
                null, VALID_PHONE, VALID_EMAIL, VALID_OFFICE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, facilitator::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedFacilitator facilitator =
                new JsonAdaptedFacilitator(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_OFFICE, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, facilitator::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedFacilitator facilitator = new JsonAdaptedFacilitator(
                VALID_NAME, null, VALID_EMAIL, VALID_OFFICE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, facilitator::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedFacilitator facilitator =
                new JsonAdaptedFacilitator(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_OFFICE, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, facilitator::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedFacilitator facilitator = new JsonAdaptedFacilitator(
                VALID_NAME, VALID_PHONE, null, VALID_OFFICE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, facilitator::toModelType);
    }

    @Test
    public void toModelType_invalidOffice_throwsIllegalValueException() {
        JsonAdaptedFacilitator facilitator =
                new JsonAdaptedFacilitator(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_OFFICE, VALID_TAGS);
        String expectedMessage = Office.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, facilitator::toModelType);
    }

    @Test
    public void toModelType_nullOffice_throwsIllegalValueException() {
        JsonAdaptedFacilitator facilitator = new JsonAdaptedFacilitator(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Office.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, facilitator::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedFacilitator facilitator =
                new JsonAdaptedFacilitator(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_OFFICE, invalidTags);
        assertThrows(IllegalValueException.class, facilitator::toModelType);
    }

}
