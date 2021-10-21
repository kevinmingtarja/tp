package seedu.address.model.comparator;
import java.util.Comparator;

import seedu.address.model.person.Person;


public class NameComparator implements Comparator<Person> {
    private final String comparator = "name";

    @Override
    public int compare(Person p1, Person p2) {
        if (p1.getName() == null) {
            if (p2.getName() == null) {
                return 0;
            }
            return 1;
        }
        if (p2.getName() == null) {
            return -1;
        }
        return p1.getName().fullName.compareTo(p2.getName().fullName);
    }

    @Override
    public String toString() {
        return this.comparator;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameComparator // instanceof handles nulls
                && comparator.equals(((NameComparator) other).comparator)); // state check
    }
}
