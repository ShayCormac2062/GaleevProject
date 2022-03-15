package ru.itis.services;

import ru.itis.forms.LoginForm;
import ru.itis.forms.UserForm;
import ru.itis.models.User;

import javax.servlet.http.Cookie;
import java.util.List;

public interface UsersService {
    User register(UserForm userForm);
    Cookie signIn(LoginForm loginForm);
    User findUserByCookieValue(String cookieValue);
    List<User> findAll();
}
