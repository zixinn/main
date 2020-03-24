package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;

public class JsonSerializableModManagerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableModManagerTest");
    private static final Path TYPICAL_ADDRESS_BOOK_FILE = TEST_DATA_FOLDER
            .resolve("typicalModManager.json");
    private static final Path INVALID_FACILITATOR_FILE = TEST_DATA_FOLDER.resolve("invalidFacilitatorModManager.json");
    private static final Path INVALID_MODULE_FILE = TEST_DATA_FOLDER.resolve("invalidModuleModManager.json");
    private static final Path DUPLICATE_FACILITATOR_FILE = TEST_DATA_FOLDER
            .resolve("duplicateFacilitatorModManager.json");
    private static final Path DUPLICATE_MODULE_FILE = TEST_DATA_FOLDER
            .resolve("duplicateModuleModManager.json");
    private static final Path MODULE_DOES_NOT_EXIST_FILE = TEST_DATA_FOLDER
            .resolve("moduleDoesNotExistModManager.json");

    @Test
    public void toModelType_typicalAddressBookFile_success() throws Exception {
        JsonSerializableModManager dataFromFile = JsonUtil.readJsonFile(TYPICAL_ADDRESS_BOOK_FILE,
                JsonSerializableModManager.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalAddressBook = getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalAddressBook);
    }

    @Test
    public void toModelType_invalidModuleFile_throwsIllegalValueException() throws Exception {
        JsonSerializableModManager dataFromFile = JsonUtil.readJsonFile(INVALID_MODULE_FILE,
                JsonSerializableModManager.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidFacilitatorFile_throwsIllegalValueException() throws Exception {
        JsonSerializableModManager dataFromFile = JsonUtil.readJsonFile(INVALID_FACILITATOR_FILE,
                JsonSerializableModManager.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateModules_throwsIllegalValueException() throws Exception {
        JsonSerializableModManager dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MODULE_FILE,
                JsonSerializableModManager.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableModManager.MESSAGE_DUPLICATE_MODULE,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateFacilitators_throwsIllegalValueException() throws Exception {
        JsonSerializableModManager dataFromFile = JsonUtil.readJsonFile(DUPLICATE_FACILITATOR_FILE,
                JsonSerializableModManager.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableModManager.MESSAGE_DUPLICATE_FACILITATOR,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_moduleDoesNotExist_throwsIllegalValueException() throws Exception {
        JsonSerializableModManager dataFromFile = JsonUtil.readJsonFile(MODULE_DOES_NOT_EXIST_FILE,
                JsonSerializableModManager.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableModManager.MESSAGE_MODULE_DOES_NOT_EXIST,
                dataFromFile::toModelType);
    }
}
