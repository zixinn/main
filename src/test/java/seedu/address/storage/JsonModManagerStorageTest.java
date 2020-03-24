package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalFacilitators.ALICE;
import static seedu.address.testutil.TypicalFacilitators.HOON;
import static seedu.address.testutil.TypicalFacilitators.IDA;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;

public class JsonModManagerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonModManagerStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyAddressBook> readAddressBook(String filePath) throws Exception {
        return new JsonModManagerStorage(Paths.get(filePath)).readModManager(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatModManager.json"));
    }

    @Test
    public void readAddressBook_invalidModuleAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidModuleModManager.json"));
    }

    @Test
    public void readAddressBook_invalidFacilitatorAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidFacilitatorModManager.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidModuleAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook(
                "invalidAndValidModuleModManager.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidFacilitatorAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook(
                "invalidAndValidFacilitatorModManager.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempModManager.json");
        AddressBook original = getTypicalAddressBook();
        JsonModManagerStorage jsonModManagerStorage = new JsonModManagerStorage(filePath);

        // Save in new file and read back
        jsonModManagerStorage.saveModManager(original, filePath);
        ReadOnlyAddressBook readBack = jsonModManagerStorage.readModManager(filePath).get();
        assertEquals(original, new AddressBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addFacilitator(HOON);
        original.removeFacilitator(ALICE);
        jsonModManagerStorage.saveModManager(original, filePath);
        readBack = jsonModManagerStorage.readModManager(filePath).get();
        assertEquals(original, new AddressBook(readBack));

        // Save and read without specifying file path
        original.addFacilitator(IDA);
        jsonModManagerStorage.saveModManager(original); // file path not specified
        readBack = jsonModManagerStorage.readModManager().get(); // file path not specified
        assertEquals(original, new AddressBook(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyAddressBook addressBook, String filePath) {
        try {
            new JsonModManagerStorage(Paths.get(filePath))
                    .saveModManager(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new AddressBook(), null));
    }
}
