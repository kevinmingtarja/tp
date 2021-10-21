package seedu.address.model.comparator;
import java.util.Comparator;

import seedu.address.model.person.Person;

public class PhoneComparator implements Comparator<Person> {
    private final String comparator = "phone";

    @Override
    public int compare(Person p1, Person p2) {
        if (p1.getPhone() == null) {
            if (p2.getPhone() == null) {
                return 0;
            }
            return 1;
        }
        if (p2.getPhone() == null) {
            return -1;
        }
        return p1.getPhone().value.compareTo(p2.getPhone().value);
    }

    @Override
    public String toString() {
        return this.comparator;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneComparator // instanceof handles nulls
                && comparator.equals(((PhoneComparator) other).comparator)); // state check
    }
}
