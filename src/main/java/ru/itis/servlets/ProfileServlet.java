package ru.itis.servlets;

import ru.itis.models.User;
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
import java.util.Arrays;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = usersService.findUserByCookieValue(Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("auth"))
                .findAny().get().getValue());
        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("BlaBlaCar/main_page.jsp").forward(request, response);
        } else {
            user = new User();
            user.setId((long) 9999);
            user.setEmail("Треугольнаязалупа.com");
            user.setPasswordHash("mkcdad");
            user.setLastName("Вход");
            user.setFirstName("Вход");
        }
    }
}
