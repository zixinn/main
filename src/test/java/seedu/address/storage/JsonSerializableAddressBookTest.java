package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.testutil.TypicalFacilitators;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_FACILITATORS_FILE = TEST_DATA_FOLDER
            .resolve("typicalFacilitatorsAddressBook.json");
    private static final Path INVALID_FACILITATOR_FILE = TEST_DATA_FOLDER.resolve("invalidFacilitatorAddressBook.json");
    private static final Path DUPLICATE_FACILITATOR_FILE = TEST_DATA_FOLDER
            .resolve("duplicateFacilitatorAddressBook.json");

    @Test
    public void toModelType_typicalFacilitatorsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_FACILITATORS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalFacilitatorsAddressBook = TypicalFacilitators.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalFacilitatorsAddressBook);
    }

    @Test
    public void toModelType_invalidFacilitatorFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_FACILITATOR_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateFacilitators_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_FACILITATOR_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_FACILITATOR,
                dataFromFile::toModelType);
    }

}
