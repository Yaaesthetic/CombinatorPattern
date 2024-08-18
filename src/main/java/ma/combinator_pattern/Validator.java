package ma.combinator_pattern;

import java.util.function.Predicate;

@FunctionalInterface
public interface Validator<Person> extends Predicate<Person> {

    default Validator<Person> and(Validator<Person> other) {
        return t ->  this.test(t) && other.test(t);
    }

    default Validator<Person> or(Validator<Person> other) {
        return t ->  this.test(t) || other.test(t);
    }

    default Validator<Person> not() {
        return t ->  !this.test(t);
    }

    default Validator<Person> helloWord(){
        return person -> {
            System.out.println(person);
            return true;
        };
    }
}
