package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.models.Message;
import ru.itis.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("massageRepositoryImpl")
public class MessageRepositoryImpl implements MessageRepository{

    private Connection connection;

    @Autowired
    private JdbcTemplate template;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=sql
    private final String SQL_INSERT_MESSAGE = "INSERT INTO \"message\"(user_id, text, time_of_create, title) VALUES (?, ?, ?, ?)";
    private final String SQL_INSERT_MESSAGE_PARAMETER = "INSERT INTO \"message\"(user_id, text, time_of_create, title) VALUES (:userId, :text, :timeOfCreate, :title)";
    private final String SQL_FIND_ALL_MESSAGES = "SELECT * FROM \"message\";";

    public MessageRepositoryImpl() {}
    public MessageRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
    public MessageRepositoryImpl(JdbcTemplate template, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.template = template;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void setConnection(Connection connection) {
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
    public Message addMessage(User user, String where, String car, String man, String other) {
        Message message = new Message();
        message.setUserId(Integer.parseInt(user.getId().toString()));
        message.setText("МОЙ МАРШРУТ: " + where + "\nМОЙ АВТОМОБИЛЬ И ЕГО ВМЕСТИТЕЛЬНОСТЬ: " + car +
                "\nПРЕДПОЧТИТЕЛЬНОЕ КОЛИЧЕСТВО ЧЕЛОВЕК: " + man + "\nПРИМЕЧАНИЯ: " + other);
        message.setTimeOfCreate(new Timestamp(System.currentTimeMillis()));
        message.setTitle("\t" + user.getFirstName() + " " + user.getLastName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(message);
        namedParameterJdbcTemplate.update(
                SQL_INSERT_MESSAGE_PARAMETER,
                namedParameters,
                keyHolder,
                new String[]{"id"}
        );
        return message;
//        ResultSet resultSet = null;
//
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_MESSAGE);
//            preparedStatement.setInt(1, message.getUserId());
//            preparedStatement.setString(2, message.getText());
//            preparedStatement.setTimestamp(3, message.getTimeOfCreate());
//            preparedStatement.setString(4, message.getTitle());
//            resultSet = preparedStatement.executeQuery();
//        } catch (SQLException e) {
//            System.out.println("Что-то пошло не так!");
//            e.printStackTrace();
//        }
    }
}
