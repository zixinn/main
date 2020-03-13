package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

import seedu.address.logic.commands.Command;
import seedu.address.model.module.Module;

/**
 * A utility class for Module.
 */
public class ModuleUtil {
    /**
     * Returns an add command string for adding the {@code module}.
     */
    public static String getModuleAddCommand(Module module) {
        return Command.COMMAND_WORD_ADD + " " + getModuleDetails(module);
    }

    /**
     * Returns the part of command string for the given {@code facilitator}'s details.
     */
    public static String getModuleDetails(Module module) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_MODULE_CODE + module.getModuleCode().moduleCode + " ");
        sb.append(PREFIX_DESCRIPTION + module.getDescription().value + " ");
        return sb.toString();
    }
}
