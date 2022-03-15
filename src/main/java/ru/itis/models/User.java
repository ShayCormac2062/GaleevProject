package ru.itis.models;

import lombok.*;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String email;
    private String password;
    private String lastName;
    private String firstName;
    private List<Message> messages;

}
