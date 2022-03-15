package ru.itis.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.itis.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Long> {
    User findByLogin(String login);
    List<User> findAll(int nothing);
    User findUserById(Long id);
    User save(User t);
}
