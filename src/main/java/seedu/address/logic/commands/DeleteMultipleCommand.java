package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NATIONALITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOCIAL_HANDLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class DeleteMultipleCommand extends Command {

    public static final String COMMAND_WORD = "deletem";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes all persons whose details contain every "
            + "specified keywords.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME]... "
            + "[" + PREFIX_GENDER + "GENDER]... "
            + "[" + PREFIX_PHONE + "PHONE]... "
            + "[" + PREFIX_EMAIL + "EMAIL]... "
            + "[" + PREFIX_NATIONALITY + "NATIONALITY]... "
            + "[" + PREFIX_TUTORIAL_GROUP + "TUTORIAL GROUP]... "
            + "[" + PREFIX_REMARK + "REMARK]... "
            + "[" + PREFIX_SOCIAL_HANDLE + "SOCIAL HANDLE]... "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Note:\n"
            + " - Keywords are case-insensitive"
            + "Example:  " + COMMAND_WORD + " n/alice p/91234567 tg/19";

    private int startIndex;
    private int endIndex;
    private Predicate<Person> predicate;

    public DeleteMultipleCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        List<Person> lastShownList = model.getFilteredPersonList();

        this.startIndex = 0;
        this.endIndex = lastShownList.size();

        for (int i = startIndex; i < endIndex; i++) {
            Person personToDelete = lastShownList.get(startIndex);
            model.deletePerson(personToDelete);
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(Messages.MESSAGE_MULTIPLE_PERSONS_DELETED_OVERVIEW, endIndex));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteMultipleCommand // instanceof handles nulls
                && predicate.equals(((DeleteMultipleCommand) other).predicate)); // state check
    }
}
