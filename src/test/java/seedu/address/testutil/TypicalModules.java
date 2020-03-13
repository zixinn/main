package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2103T;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.module.Module;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {
    public static final Module CS2103T = new ModuleBuilder().withModuleCode(VALID_MODULE_CODE_CS2103T)
            .withDescription(VALID_DESCRIPTION_CS2103T).build();
    public static final Module CS2101 = new ModuleBuilder().withModuleCode(VALID_MODULE_CODE_CS2101)
            .withDescription(VALID_DESCRIPTION_CS2101).build();

    private TypicalModules() {} // prevents instantiation

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS2103T, CS2101));
    }
}
