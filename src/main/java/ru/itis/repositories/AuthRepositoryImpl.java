package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.mapper.RowMapper;
import ru.itis.models.Auth;
import ru.itis.models.User;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository("authRepositoryImpl")
public class AuthRepositoryImpl implements AuthRepository {

    private Connection connection;
    @Autowired
    private JdbcTemplate template;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String SQL_FIND_BY_COOKIE_VALUE = "SELECT *, auth.id as auth_id, \"user\".id as user_id FROM \"auth\" INNER JOIN \"user\" ON auth.user_id=\"user\".id WHERE auth.cookie_value=?;";
    private final String SQL_INSERT_AUTH = "INSERT INTO auth (user_id, cookie_value) VALUES (?, ?)";

    public AuthRepositoryImpl() {}
    public AuthRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
    public AuthRepositoryImpl(JdbcTemplate template, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.template = template;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Auth findByCookieValue(String cookieValue) {
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_COOKIE_VALUE);
            preparedStatement.setString(1, cookieValue);
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();
            Auth auth = authRowMapper.rowMap(resultSet);
            return auth;
        } catch (SQLException throwables) {
            System.out.println("Что-то пошло не так!");
            return null;
        }
    }

    @Override
    public <S extends Auth> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Auth> findById(String s) {
        return Optional.of(findByCookieValue(s));
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<Auth> findAll() {
        return null;
    }

    @Override
    public Iterable<Auth> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Auth entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Auth> entities) {

    }

    @Override
    public void deleteAll() {

    }

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

    private RowMapper<Auth> authRowMapper = (resultSet -> {
        if (resultSet.next()) {
            Auth auth = new Auth();
            auth.setId(resultSet.getLong("auth_id"));
            auth.setCookieValue(resultSet.getString("cookie_value"));

            User user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password_hash"));

            auth.setUser(user);
            return auth;
        } else {
            return null;
        }
    });
}
