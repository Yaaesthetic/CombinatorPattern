package ma.combinator_pattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootApplication
public class CombinatorPatternApplication {

    public static void main(String[] args) {

        SpringApplication.run(CombinatorPatternApplication.class, args);

        List<Person> people = List.of(
                new Person(
                        "Alice Johnson",
                        LocalDate.of(1990, 5, 15),
                        "alice.johnson@example.com",
                        Person.Gender.FEMALE,
                        "securePass123".toCharArray()
                ),new Person(
                        "Bob Smith",
                        LocalDate.of(1985, 10, 22),
                        "bob.smith@example.com",
                        Person.Gender.MALE,
                        "anotherPass456".toCharArray()
                )
        );

        people.stream()
                .sorted(Comparator.comparing(Person::getFullName))
                .distinct()
                .filter(person -> MyFuncInter.isAgeValid().
                        and(MyFuncInter.isEmailValid())
                        .challenge(person))
                .filter(person -> MyFuncInter.isPersonValid().challenge(person))
                .forEach(System.out::println);
    }

}
