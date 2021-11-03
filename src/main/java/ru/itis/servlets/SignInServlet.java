package ru.itis.servlets;

import ru.itis.forms.LoginForm;
import ru.itis.repositories.AuthRepository;
import ru.itis.repositories.AuthRepostoryImpl;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryImpl;
import ru.itis.services.UsersService;
import ru.itis.services.UsersServicesImpl;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/login")
public class SignInServlet extends HttpServlet {

    private UsersService usersService;

    private final String URL = "jdbc:postgresql://localhost:5432/semestrovka";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "zR#BKdWn";

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            UsersRepository usersRepository = new UsersRepositoryImpl(connection);
            AuthRepository authRepository = new AuthRepostoryImpl(connection);
            usersService = new UsersServicesImpl(usersRepository, authRepository);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Unavailable");
            throw new UnavailableException("Сайт недоступен!!!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("Login_v2/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("email");
        String password = req.getParameter("pass");

        LoginForm loginForm = new LoginForm(login, password);
        Cookie cookie = usersService.signIn(loginForm);

        if (cookie != null) {
            resp.addCookie(cookie);
            resp.sendRedirect("/profile");
        } else {
            req.setAttribute("signInStatus", "Неправильный логин или пароль");
            req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
        }
    }


}
