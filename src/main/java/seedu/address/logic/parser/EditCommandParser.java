package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OFFICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditFacilitatorDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.modulecode.ModuleCode;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_OFFICE, PREFIX_MODULE_CODE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditCommand.EditFacilitatorDescriptor editFacilitatorDescriptor = new EditFacilitatorDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editFacilitatorDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editFacilitatorDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editFacilitatorDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_OFFICE).isPresent()) {
            editFacilitatorDescriptor.setOffice(ParserUtil.parseOffice(argMultimap.getValue(PREFIX_OFFICE).get()));
        }
        parseModuleCodesForEdit(argMultimap.getAllValues(PREFIX_MODULE_CODE))
                .ifPresent(editFacilitatorDescriptor::setModuleCodes);

        if (!editFacilitatorDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editFacilitatorDescriptor);
    }

    /**
     * Parses {@code Collection<String> moduleCodes} into a {@code Set<ModuleCode>} if {@code moduleCodes} is non-empty.
     * If {@code moduleCodes} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<ModuleCode>} containing zero module codes.
     */
    private Optional<Set<ModuleCode>> parseModuleCodesForEdit(Collection<String> moduleCodes) throws ParseException {
        assert moduleCodes != null;

        if (moduleCodes.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> moduleCodeSet = moduleCodes.size() == 1 && moduleCodes.contains("")
                ? Collections.emptySet() : moduleCodes;
        return Optional.of(ParserUtil.parseModuleCodes(moduleCodeSet));
    }

}
