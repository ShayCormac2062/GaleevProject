package ru.itis.models;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private int userId;
    private String title;
    private String text;
    private Timestamp timeOfCreate;
}
