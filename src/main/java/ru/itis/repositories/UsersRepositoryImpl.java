package ru.itis.repositories;

import ru.itis.models.User;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository {

    private Connection connection;

    //language=sql
    private final String SQL_INSERT_USER = "INSERT INTO \"user\"(first_name, last_name, email, password_hash) VALUES (?, ?, ?, ?)";
    private final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM \"user\" WHERE email=?;";

    public UsersRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPasswordHash());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public User findByLogin(String login) {
        ResultSet resultSet = null;
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            preparedStatement.setString(1, login);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("login"));
                user.setPasswordHash(resultSet.getString("password_hash"));
                user.setLastName(resultSet.getString("last_name"));
                user.setFirstName(resultSet.getString("first_name"));
            }
        } catch (SQLException throwables) {
            //
        }
        return user;
    }
}
