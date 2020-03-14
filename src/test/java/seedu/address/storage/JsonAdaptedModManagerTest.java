package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalFacilitators.ALICE;
import static seedu.address.testutil.TypicalModules.CS2103T;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;


public class JsonAdaptedModManagerTest {

    private static final AddressBook ab = new AddressBook();

    @Test
    public void toModelType_validModManager_returnsModManager() throws Exception {
        ab.addModule(CS2103T);
        ab.addFacilitator(ALICE);
        JsonAdaptedModManager modManager = new JsonAdaptedModManager(ab);
        assertEquals(ab, modManager.toModelType());
    }
}
