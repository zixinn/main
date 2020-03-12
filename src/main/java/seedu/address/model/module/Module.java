package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Module in Mod Manager.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Module {

    public static final String COMMAND_WORD = "mod";

    //Identity fields
    private final ModuleCode code;

    //Data fields
    private Description description;

    public Module(ModuleCode code, Description description) {
        requireAllNonNull(code, description);
        this.code = code;
        this.description = description;
    }

    public ModuleCode getCode() {
        return code;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns true if both modules have the same module code.
     *
     * @param otherModule The other module to compare with.
     * @return true if both modules have the same module code.
     */
    public boolean isSameModule(Module otherModule) {
        if (otherModule == this) {
            return true;
        }

        return otherModule != null
                && otherModule.getCode().equals(getCode());
    }

    /**
     * Returns true if both modules have the same identity and data fields.
     *
     * @param other The other object to compare with.
     * @return true if both modules are equal.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Module)) {
            return false;
        }

        Module otherModule = (Module) other;
        return otherModule.getCode().equals(getCode())
                && otherModule.getDescription().equals(getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, description);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getCode());
        builder.append(" Description: ").append(getDescription());
        return builder.toString();
    }

}
