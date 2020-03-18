package seedu.address.logic.commands.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

import java.util.Optional;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.facilitator.ModuleCodesContainKeywordPredicate;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;

/**
 * Shows all information of a module in Mod Manager to the user.
 */
public class ModuleViewCommand extends ModuleCommand {

    public static final String MESSAGE_USAGE = COMMAND_GROUP_MOD + " " + COMMAND_WORD_VIEW
            + ": Views a module in Mod Manager. "
            + "Parameters: "
            + PREFIX_MODULE_CODE + " MOD_CODE \n"
            + "Example: " + COMMAND_GROUP_MOD + " " + COMMAND_WORD_VIEW + " "
            + PREFIX_MODULE_CODE + " CS2103T ";
    public static final String MESSAGE_MODULE_DOES_NOT_EXIST = "The module %1$s does not exist in Mod Manager.";

    public static final String MESSAGE_SUCCESS = "Viewed module: %1$s";

    private final ModuleCode toView;

    public ModuleViewCommand(ModuleCode toView) {
        requireNonNull(toView);
        this.toView = toView;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<Module> module = model.findModule(toView);
        if (module.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_MODULE_DOES_NOT_EXIST, toView.moduleCode));
        }

        model.updateModule(module.get());
        model.updateFilteredFacilitatorList(new ModuleCodesContainKeywordPredicate(toView.moduleCode));
        return new CommandResult(String.format(MESSAGE_SUCCESS, toView), CommandType.MODULE_VIEW);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleViewCommand // instanceof handles nulls
                && toView.equals(((ModuleViewCommand) other).toView));
    }
}
