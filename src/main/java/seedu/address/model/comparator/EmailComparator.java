package seedu.address.model.comparator;

import seedu.address.model.person.GenderContainsKeywordsPredicate;
import seedu.address.model.person.Person;

import java.util.Comparator;

public class EmailComparator implements Comparator<Person> {
    private final String comparator = "email";

    @Override
    public int compare(Person p1, Person p2) {
        if (p1.getEmail() == null) {
            if (p2.getEmail() == null) {
                return 0;
            }
            return 1;
        }
        if (p2.getEmail() == null) {
            return -1;
        }
        return p1.getEmail().value.compareTo(p2.getEmail().value);
    }

    @Override
    public String toString() {
        return this.comparator;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailComparator // instanceof handles nulls
                && comparator.equals(((EmailComparator) other).comparator)); // state check
    }
}
