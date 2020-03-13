package seedu.address.testutil;

import seedu.address.model.module.Module;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {
    public static final String VALID_MODULE_CODE_CS2103T = "CS2103T";
    public static final String VALID_DESCRIPTION_CS2103T = "Software Engineering";

    public static final String VALID_MODULE_CODE_CS2101 = "CS2101";
    public static final String VALID_DESCRIPTION_CS2101 = "Effective Communication for Computing Professionals";

    public static final Module CS2103T = new ModuleBuilder().withModuleCode(VALID_MODULE_CODE_CS2103T)
            .withDescription(VALID_DESCRIPTION_CS2103T).build();
    public static final Module CS2101 = new ModuleBuilder().withModuleCode(VALID_MODULE_CODE_CS2101)
            .withDescription(VALID_DESCRIPTION_CS2101).build();
}
