package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Module(null, null));
    }

    @Test
    public void isSameModule() {
        //please add more tests
        ModuleCode code = new ModuleCode("CS2103T");
        Module mod = new Module(code, "SE");

        //same object -> returns true
        assertTrue(mod.isSameModule(mod));

        //null -> return false
        assertFalse(mod.isSameModule(null));
    }
}
