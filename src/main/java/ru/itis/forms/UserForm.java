package ru.itis.forms;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
