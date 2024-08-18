package ma.combinator_pattern;

import java.time.LocalDate;
import java.util.Objects;

@FunctionalInterface
public interface MyFuncInter {
    boolean challenge(Person person);

    default MyFuncInter and(MyFuncInter other) {
        Objects.requireNonNull(other);
        return person -> this.challenge(person) && other.challenge(person);
    }

    static MyFuncInter isNameValid() {
        return person -> !person.getFullName().isBlank();
    }

    static MyFuncInter isAgeValid() {
        return person -> (LocalDate.now().getYear() - person.getAge().getYear()) >= 17;
    }

    static MyFuncInter isEmailValid() {
        return person -> {
            if (person.getMail().contains("@") && !person.getMail().contains(" ")) {
                String[] parts = person.getMail().split("@");
                String localPart = parts[0];
                String domain = parts[1];
                boolean isValidLocalPart = localPart.matches("^[a-zA-Z0-9!#$%&'*+/=?^_`{|}~.-]+$") &&
                        !localPart.startsWith(".") &&
                        !localPart.endsWith(".") &&
                        !localPart.contains("..");
                boolean isValidDomain = domain.matches("^[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

                return isValidDomain && isValidLocalPart;
            }
            return false;
        };
    }

    static MyFuncInter isPasswordValid() {
        return person -> person.getPassword().length > 0;
    }

    static MyFuncInter isPersonValid() {
        return person ->  isNameValid()
                .and(isAgeValid())
                .and(isEmailValid())
                .and(isPasswordValid())
                .challenge(person);
    }
}
