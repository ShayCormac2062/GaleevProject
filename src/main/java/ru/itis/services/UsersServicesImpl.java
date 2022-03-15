package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.forms.LoginForm;
import ru.itis.forms.UserForm;
import ru.itis.models.Auth;
import ru.itis.models.User;
import ru.itis.repositories.AuthRepository;
import ru.itis.repositories.UsersRepository;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.UUID;

@Service("usersService")
public class UsersServicesImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsersServicesImpl() {}
    public UsersServicesImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    public UsersServicesImpl(UsersRepository usersRepository, AuthRepository authRepository) {
        this.usersRepository = usersRepository;
        this.authRepository = authRepository;
    }

    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void setAuthRepository(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(UserForm userForm) {

        User user = new User();
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setEmail(userForm.getEmail());

        String passwordHash = passwordEncoder.encode(userForm.getPassword());

        user.setPassword(passwordHash);

        return usersRepository.save(user);
    }

    @Override
    public Cookie signIn(LoginForm loginForm) {

        User user = usersRepository.findByLogin(loginForm.getEmail());

        if (user != null) {
            if (passwordEncoder.matches(loginForm.getPassword(), user.getPassword())) {

                String cookieValue = UUID.randomUUID().toString();

                Auth auth = new Auth();
                auth.setUser(user);
                auth.setCookieValue(cookieValue);
                authRepository.save(auth);

                Cookie cookie = new Cookie("auth", cookieValue);
                cookie.setMaxAge(10 * 60 * 60);
                return cookie;
            } else {
                System.out.println("Вход не выполнен!");
            }
        }
        return null;
    }

    @Override
    public User findUserByCookieValue(String cookieValue) {
        Auth auth = authRepository.findByCookieValue(cookieValue);
        if (auth != null) {
            return auth.getUser();
        } else {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        return usersRepository.findAll(0);
    }
}
