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
        Module module = new Module(new ModuleCode("CS2103T"), new Description("Software Engineering"));

        //same object -> returns true
        assertTrue(module.isSameModule(module));

        //null -> return false
        assertFalse(module.isSameModule(null));

        // different module code -> returns false
        Module otherModule = new Module(new ModuleCode("CS2101"), new Description("Software Engineering"));
        assertFalse(module.isSameModule(otherModule));

        // same name, different description -> returns true
        otherModule = new Module(new ModuleCode("CS2103T"), new Description("SE"));
        assertTrue(module.isSameModule(otherModule));

        // same name, same description -> returns true
        otherModule = new Module(new ModuleCode("CS2103T"), new Description("Software Engineering"));
        assertTrue(module.isSameModule(otherModule));
    }

    @Test
    public void equals() {
        Module module = new Module(new ModuleCode("CS2103T"), new Description("Software Engineering"));

        // same values -> returns true
        Module otherModule = new Module(new ModuleCode("CS2103T"), new Description("Software Engineering"));
        assertTrue(module.equals(otherModule));

        // same object -> returns true
        assertTrue(module.equals(module));

        // null -> returns false
        assertFalse(module.equals(null));

        // different type -> returns false
        assertFalse(module.equals(5));

        // different facilitator -> returns false
        otherModule = new Module(new ModuleCode("CS2101"),
                new Description("Effective Communication for Computing Professionals"));
        assertFalse(module.equals(otherModule));

        // different module code -> returns false
        otherModule = new Module(new ModuleCode("CS2101"), new Description("Software Engineering"));
        assertFalse(module.equals(otherModule));

        // different description -> returns false
        otherModule = new Module(new ModuleCode("CS2103T"),
                new Description("Effective Communication for Computing Professionals"));
        assertFalse(module.equals(otherModule));
    }
}
