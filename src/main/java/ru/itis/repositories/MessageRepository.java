package ru.itis.repositories;

import ru.itis.models.Message;
import ru.itis.models.User;

import java.util.List;

public interface MessageRepository {
    List<Message> findAllMessages();
    Message addMessage(User user, String where, String car, String man, String other);
}
