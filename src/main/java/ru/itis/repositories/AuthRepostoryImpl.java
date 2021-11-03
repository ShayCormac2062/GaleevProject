package ru.itis.repositories;

import ru.itis.mapper.RowMapper;
import ru.itis.models.Auth;
import ru.itis.models.User;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class AuthRepostoryImpl implements AuthRepository {

    private Connection connection;

    private final String SQL_FIND_BY_COOKIE_VALUE = "SELECT *, auth.id as auth_id, user.id as user_id FROM auth INNER JOIN user ON auth.user_id=user.id WHERE auth.cookie_value=?";
    private final String SQL_INSERT_AUTH = "INSERT INTO auth (user_id, cookie_value) VALUES (?, ?)";


    public AuthRepostoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Auth findByCookieValue(String cookieValue) {
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_COOKIE_VALUE);
            preparedStatement.setString(1, cookieValue);
            resultSet = preparedStatement.executeQuery();
            Auth auth = authRowMapper.rowMap(resultSet);
            return auth;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Auth> findAll() {
        return null;
    }

    @Override
    public Optional<Auth> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Auth save(Auth auth) {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_AUTH, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, auth.getUser().getId());
            preparedStatement.setString(2, auth.getCookieValue());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {

        }
        return auth;
    }

    @Override
    public void deleteById(Long id) {

    }

    private RowMapper<Auth> authRowMapper = (resultSet) -> {
        if (resultSet.next()) {
            Auth auth = new Auth();
            auth.setId(resultSet.getLong("auth_id"));
            auth.setCookieValue(resultSet.getString("cookie_value"));

            User user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("login"));
            user.setPasswordHash(resultSet.getString("password_hash"));

            auth.setUser(user);
            return auth;
        } else {
            return null;
        }
    };
}
