package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OFFICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.facil.FacilAdd;
import seedu.address.logic.commands.facil.FacilCommand;
import seedu.address.logic.commands.facil.FacilDelete;
import seedu.address.logic.commands.facil.FacilEdit;
import seedu.address.logic.commands.facil.FacilFind;
import seedu.address.logic.parser.exceptions.ParseException;

import seedu.address.model.facilitator.Email;
import seedu.address.model.facilitator.Facilitator;
import seedu.address.model.facilitator.Name;
import seedu.address.model.facilitator.NameContainsKeywordsPredicate;
import seedu.address.model.facilitator.Office;
import seedu.address.model.facilitator.Phone;
import seedu.address.model.module.ModuleCode;

/**
 * Parses input arguments and creates a new FacilAdd object
 */
public class FacilCommandParser implements Parser<FacilCommand> {

    /**
     * The general parse method, checks which type of parsing is required and call the corresponding one.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FacilCommand parse(String args, String commandWord) throws ParseException {
        switch (commandWord) {

        case FacilCommand.ADD_COMMAND_WORD:
            return parseAdd(args);

        case FacilCommand.EDIT_COMMAND_WORD:
            return parseEdit(args);

        case FacilCommand.DELETE_COMMAND_WORD:
            return parseDelete(args);

        case FacilCommand.FIND_COMMAND_WORD:
            return parseFind(args);

        case FacilCommand.LIST_COMMAND_WORD:
            return parseList();

        default:
            throw new ParseException("Error");
        }
    }

    @Override
    public FacilCommand parse(String userInput) throws ParseException {
        return null;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FacilAdd
     * and returns an FacilAdd object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FacilCommand parseAdd(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_OFFICE, PREFIX_MODULE_CODE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FacilAdd.MESSAGE_USAGE));
        }

        if ((!arePrefixesPresent(argMultimap, PREFIX_PHONE) && !arePrefixesPresent(argMultimap, PREFIX_EMAIL)
                && !arePrefixesPresent(argMultimap, PREFIX_OFFICE))) {
            throw new ParseException(FacilAdd.MESSAGE_NOT_ADDED);
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME));
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE));
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL));
        Office office = ParserUtil.parseOffice(argMultimap.getValue(PREFIX_OFFICE));

        Set<ModuleCode> moduleCodeList = ParserUtil.parseModuleCodes(argMultimap.getAllValues(PREFIX_MODULE_CODE));

        Facilitator facilitator = new Facilitator(name, phone, email, office, moduleCodeList);

        return FacilCommand.add(facilitator);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FacilEdit
     * and returns an FacilEdit object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FacilCommand parseEdit(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_OFFICE, PREFIX_MODULE_CODE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FacilEdit.MESSAGE_USAGE), pe);
        }

        FacilEdit.EditFacilitatorDescriptor editFacilitatorDescriptor = new FacilEdit.EditFacilitatorDescriptor();
        if (argMultimap.getValue(PREFIX_NAME) != null) {
            editFacilitatorDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME)));
        }
        if (argMultimap.getValue(PREFIX_PHONE) != null) {
            if (argMultimap.getValue(PREFIX_PHONE).equals("")) {
                editFacilitatorDescriptor.setPhone(ParserUtil.parsePhone(null));
            } else {
                editFacilitatorDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE)));
            }
        }
        if (argMultimap.getValue(PREFIX_EMAIL) != null) {
            if (argMultimap.getValue(PREFIX_EMAIL).equals("")) {
                editFacilitatorDescriptor.setEmail(ParserUtil.parseEmail(null));
            } else {
                editFacilitatorDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL)));
            }
        }
        if (argMultimap.getValue(PREFIX_OFFICE) != null) {
            if (argMultimap.getValue(PREFIX_OFFICE).equals("")) {
                editFacilitatorDescriptor.setOffice(ParserUtil.parseOffice(null));
            } else {
                editFacilitatorDescriptor.setOffice(ParserUtil.parseOffice(argMultimap.getValue(PREFIX_OFFICE)));
            }
        }
        parseModuleCodesForEdit(argMultimap.getAllValues(PREFIX_MODULE_CODE))
                .ifPresent(editFacilitatorDescriptor::setModuleCodes);

        if (!editFacilitatorDescriptor.isAnyFieldEdited()) {
            throw new ParseException(FacilEdit.MESSAGE_NOT_EDITED);
        }

        return FacilCommand.edit(index, editFacilitatorDescriptor);
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

    /**
     * Parses the given {@code String} of arguments in the context of the FacilDelete
     * and returns a FacilDelete object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FacilCommand parseDelete(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return FacilCommand.delete(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FacilDelete.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FacilFind
     * and returns a FacilFind object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FacilCommand parseFind(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FacilFind.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return FacilCommand.find(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FacilList
     * and returns a FacilList object for execution.
     */
    public FacilCommand parseList() {
        return FacilCommand.list();
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix) != null);
    }

}
