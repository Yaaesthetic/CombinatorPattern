package ma.combinator_pattern;

import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@NonNull
class PersonRegistrationValidation {

    public Validator<Person> isNameValid(){
        return person -> !person.getFullName().isBlank();
    }

    public Validator<Person> isAgeValid(){
        return person -> (LocalDate.now().getYear() - person.getAge().getYear()) >= 17;
    }

    public Validator<Person> isEmailValid(){
        return person -> {
            if (person.getMail().contains("@") && !person.getMail().contains(" ")){
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
            else return false;
        };
    }

    public Validator<Person> isPasswordValid(){
        return person -> person.getPassword().length >0;
    }


    public  Validator<Person> isPersonValid(){
        return person -> isNameValid().and(isAgeValid().and(isEmailValid().and(isPasswordValid()))).test(person);
    }

}
