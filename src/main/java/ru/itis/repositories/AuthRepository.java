package ru.itis.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.itis.models.Auth;

import java.util.Optional;

public interface AuthRepository extends CrudRepository<Auth, String> {
    Auth findByCookieValue(String cookieValue);
    Auth save(Auth auth);
    Optional<Auth> findById(Long id);
}
