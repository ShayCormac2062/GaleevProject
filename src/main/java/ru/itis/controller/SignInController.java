package ru.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.forms.LoginForm;
import ru.itis.forms.UserForm;
import ru.itis.services.UsersService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
public class SignInController {

    @Autowired
    @Qualifier("usersService")
    private UsersService usersService;

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public void signIn(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("email");
        String password = req.getParameter("pass");

        LoginForm loginForm = new LoginForm(login, password);
        Cookie cookie = usersService.signIn(loginForm);

        if (cookie != null) {
            resp.addCookie(cookie);
            resp.sendRedirect("/profile");
        } else {
            req.setAttribute("signInStatus", "Неправильный логин или пароль");
            req.getRequestDispatcher("Login_v2/jsp/login.jsp").forward(req, resp);
        }
    }
}
