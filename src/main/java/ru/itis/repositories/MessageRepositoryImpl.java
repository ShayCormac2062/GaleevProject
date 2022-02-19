package ru.itis.repositories;

import ru.itis.models.Message;
import ru.itis.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageRepositoryImpl implements MessageRepository{

    private Connection connection;

    //language=sql
    private final String SQL_INSERT_MESSAGE = "INSERT INTO \"message\"(user_id, text, time_of_create, title) VALUES (?, ?, ?, ?)";
    private final String SQL_FIND_ALL_MESSAGES = "SELECT * FROM \"message\";";

    public MessageRepositoryImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Message> findAllMessages() {
        ResultSet resultSet;
        List<Message> messages = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_MESSAGES);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Message message = new Message();
                message.setUserId(resultSet.getInt("user_id"));
                message.setText(resultSet.getString("text"));
                message.setTimeOfCreate(resultSet.getTimestamp("time_of_create"));
                message.setTitle(resultSet.getString("title"));
                messages.add(message);
            }
        } catch (SQLException throwables) {
            System.out.println("Что-то пошло не так");
            throwables.printStackTrace();
        }
        return messages;
    }

    @Override
    public void addMessage(User user, String where, String car, String man, String other) {
        ResultSet resultSet = null;
        Message message = new Message();
        message.setUserId(Integer.parseInt(user.getId().toString()));
        message.setText("МОЙ МАРШРУТ: " + where + "\nМОЙ АВТОМОБИЛЬ И ЕГО ВМЕСТИТЕЛЬНОСТЬ: " + car +
                "\nПРЕДПОЧТИТЕЛЬНОЕ КОЛИЧЕСТВО ЧЕЛОВЕК: " + man + "\nПРИМЕЧАНИЯ: " + other);
        message.setTimeOfCreate(new Timestamp(System.currentTimeMillis()));
        message.setTitle("\t" + user.getFirstName() + " " + user.getLastName());
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_MESSAGE);
            preparedStatement.setInt(1, message.getUserId());
            preparedStatement.setString(2, message.getText());
            preparedStatement.setTimestamp(3, message.getTimeOfCreate());
            preparedStatement.setString(4, message.getTitle());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Что-то пошло не так!");
            e.printStackTrace();
        }
    }
}
