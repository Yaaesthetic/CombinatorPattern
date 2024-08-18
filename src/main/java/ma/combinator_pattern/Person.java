package ma.combinator_pattern;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@NonNull
public class Person {
    private String fullName;
    private LocalDate age;
    private String mail;
    private Gender gender;
    private char[] password;

    enum Gender {
        MALE, FEMALE
    }

}
