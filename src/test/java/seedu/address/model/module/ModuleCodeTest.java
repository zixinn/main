package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleCodeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleCode(null));
    }

    @Test
    public void constructor_invalidModuleCode_throwsIllegalArgumentException() {
        String invalidModuleCode = "";
        assertThrows(IllegalArgumentException.class, () -> new ModuleCode(invalidModuleCode));
    }

    @Test
    public void isValidModuleCode() {
        // null module code
        assertThrows(NullPointerException.class, () -> ModuleCode.isValidModuleCode(null));

        // invalid module codes
        assertFalse(ModuleCode.isValidModuleCode("CS 2103T")); // contains space
        assertFalse(ModuleCode.isValidModuleCode("longModuleCode")); // long module code
        assertFalse(ModuleCode.isValidModuleCode("^")); // only non-alphanumeric characters
        assertFalse(ModuleCode.isValidModuleCode("cs2103t*")); // contains non-alphanumeric characters

        // valid module codes
        assertTrue(ModuleCode.isValidModuleCode("cs")); // alphabets only
        assertTrue(ModuleCode.isValidModuleCode("2103T")); // numbers only
        assertTrue(ModuleCode.isValidModuleCode("cs2103t")); // alphanumeric characters
        assertTrue(ModuleCode.isValidModuleCode("CS2103T")); // with capital letters
    }

}
