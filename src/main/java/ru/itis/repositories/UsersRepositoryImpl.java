package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.itis.models.User;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("usersRepositoryImpl")
public class UsersRepositoryImpl implements UsersRepository {

    private Connection connection;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=SQL
    private static final String INSERT_USER = "insert into \"user\"(first_name, last_name, email, password_hash) values (:firstName, :lastName, :email, :password)";
    private static final String INSERT = "insert into \"user\"(first_name, last_name, email, password_hash) values (?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "select * from \"user\" where id=?";
    private static final String SELECT_ALL = "select * from \"user\"";
    private static final String DELETE = "select * from \"user\""; //Не знаю, как это делается


    public UsersRepositoryImpl() {}
    public UsersRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
    public UsersRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    private final RowMapper<User> userRowMapper = ((resultSet, i) -> User.builder()
            .id(resultSet.getLong("id"))
            .firstName(resultSet.getString("first_name"))
            .lastName(resultSet.getString("last_name"))
            .email("email")
            .password(resultSet.getString("password_hash"))
            .build());

    @Override
    public User save(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
        namedParameterJdbcTemplate.update(
                INSERT_USER,
                namedParameters,
                keyHolder,
                new String[]{"id"}
                );
        user.setId((long) keyHolder.getKey().intValue());
        return user;
//        jdbcTemplate.update(connection -> {
//            PreparedStatement statement = connection.prepareStatement(INSERT);
//            statement.setString(1, user.getFirstName());
//            statement.setString(2, user.getLastName());
//            statement.setString(3, user.getEmail());
//            statement.setString(4, user.getPasswordHash());
//            return statement;
//        }, keyHolder);
    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> users) {
        List<User> userList = new ArrayList<>();
        users.forEach(userList::add);
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(userList.toArray());
        namedParameterJdbcTemplate.batchUpdate(INSERT, batch);
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user;
        try {
            user = jdbcTemplate.queryForObject(
                    SELECT_BY_ID,
                    userRowMapper,
                    id);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
        return Optional.of(user);
    }

    @Override
    public User findByLogin(String login) {
        return null;
    }

    @Override
    public List<User> findAll(int nothing) {
        List<User> result;
        result = jdbcTemplate.query(SELECT_ALL, userRowMapper);
        return result;
    }

    @Override
    public User findUserById(Long id) {
        User user;
        try {
            user = jdbcTemplate.queryForObject(
                    SELECT_BY_ID,
                            userRowMapper,
                            id);
        } catch (DataAccessException e) {
            return null;
        }
        return user;
    }

    @Override
    public boolean existsById(Long id) {
        return findUserById(id) != null;
    }

    @Override
    public Iterable<User> findAll() {
        return findAll(0);
    }

    @Override
    public Iterable<User> findAllById(Iterable<Long> longs) {
        List<User> result = new ArrayList<>();
        longs.forEach(myLong -> result.add(findUserById(myLong)));
        return result;
    }

    @Override
    public long count() {
        return findAll(0).size();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
