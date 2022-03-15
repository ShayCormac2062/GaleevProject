package ru.itis.servlets;

import ru.itis.models.Auth;
import ru.itis.models.User;
import ru.itis.repositories.*;
import ru.itis.services.UsersService;
import ru.itis.services.UsersServicesImpl;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.NoSuchElementException;

@WebServlet("/post_ride")
public class PostRideServlet extends HttpServlet {

    private UsersService usersService;
    private MessageRepository messageRepository;
    private AuthRepository authRepository;

    private final String URL = "jdbc:postgresql://localhost:5432/semestrovka";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "zR#BKdWn";

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            UsersRepository usersRepository = new UsersRepositoryImpl(connection);
            authRepository = new AuthRepositoryImpl(connection);
            usersService = new UsersServicesImpl(usersRepository, authRepository);
            messageRepository = new MessageRepositoryImpl(connection);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Unavailable");
            throw new UnavailableException("Сайт недоступен!!!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;
        try {
            user = usersService.findUserByCookieValue(Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals("auth"))
                    .findAny().get().getValue());
        } catch (NoSuchElementException e) {

        }
        if (user != null) {
            req.setAttribute("user", user);
            req.getRequestDispatcher("Login_v2/postRide.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Auth auth = authRepository.findByCookieValue(Arrays.stream(req.getCookies())
                .filter(cookie -> cookie.getName().equals("auth"))
                .findFirst()
                .get()
                .getValue());
        String where = req.getParameter("where");
        String car = req.getParameter("car");
        String man = req.getParameter("man");
        String other = req.getParameter("other");
        messageRepository.addMessage(auth.getUser(), where, car, man, other);
        resp.sendRedirect("/chat");
    }
}
