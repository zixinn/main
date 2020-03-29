package seedu.address.logic.parser.facilitator;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OFFICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.facilitator.FacilEditCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.facilitator.Name;
import seedu.address.model.module.ModuleCode;

/**
 * Parses input arguments and creates a new FacilEditCommand object.
 */
public class FacilEditCommandParser implements Parser<FacilEditCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FacilEditCommand
     * and returns an FacilEditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FacilEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_OFFICE, PREFIX_MODULE_CODE);

        String preAmble = argMultimap.getPreamble();
        Index index = null;
        Name fname = null;

        int mode = 0;
        ParseException pE = null;

        try {
            index = ParserUtil.parseIndex(preAmble);
        } catch (ParseException pe) {
            mode = 1;
        }

        try {
            if (mode == 1) {
                fname = ParserUtil.parseName(preAmble);
            }
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FacilEditCommand.MESSAGE_USAGE));
        }

        FacilEditCommand.EditFacilitatorDescriptor editFacilitatorDescriptor =
                new FacilEditCommand.EditFacilitatorDescriptor();
        if (argMultimap.getValue(PREFIX_NAME) != null) {
            editFacilitatorDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME)));
        }
        if (argMultimap.getValue(PREFIX_PHONE) != null) {
            editFacilitatorDescriptor.setPhone(ParserUtil.parsePhone(
                    parseFieldForEdit(argMultimap.getValue(PREFIX_PHONE))));
        }
        if (argMultimap.getValue(PREFIX_EMAIL) != null) {
            editFacilitatorDescriptor.setEmail(ParserUtil.parseEmail(
                    parseFieldForEdit(argMultimap.getValue(PREFIX_EMAIL))));
        }
        if (argMultimap.getValue(PREFIX_OFFICE) != null) {
            editFacilitatorDescriptor.setOffice(ParserUtil.parseOffice(
                    parseFieldForEdit(argMultimap.getValue(PREFIX_OFFICE))));
        }
        parseModuleCodesForEdit(argMultimap.getAllValues(PREFIX_MODULE_CODE))
                .ifPresent(editFacilitatorDescriptor::setModuleCodes);

        if (!editFacilitatorDescriptor.isAnyFieldEdited()) {
            throw new ParseException(FacilEditCommand.MESSAGE_NOT_EDITED);
        }

        if (mode == 0) {
            return new FacilEditCommand(index, editFacilitatorDescriptor);
        } else {
            return new FacilEditCommand(fname, editFacilitatorDescriptor);
        }
    }

    /**
     * Parses {@code Collection<String> moduleCodes} into a {@code Set<ModuleCode>} if {@code moduleCodes} is non-empty.
     */
    private Optional<Set<ModuleCode>> parseModuleCodesForEdit(Collection<String> moduleCodes) throws ParseException {
        requireNonNull(moduleCodes);

        if (moduleCodes.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(ParserUtil.parseModuleCodes(moduleCodes));
    }

    /**
     * Parses the given {@code String} of arguments and returns the {@code String} for the field for execution.
     * If the given {@code String} of arguments is an empty string, it will be parsed into a null Object.
     *
     * @param args the arguments for the field.
     * @return the {@code String} for the field.
     */
    private String parseFieldForEdit(String args) {
        if (args.equals("")) {
            return null;
        } else {
            return args;
        }
    }
}
