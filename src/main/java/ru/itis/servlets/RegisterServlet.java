package ru.itis.servlets;

import ru.itis.forms.UserForm;
import ru.itis.repositories.AuthRepository;
import ru.itis.repositories.AuthRepostoryImpl;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryImpl;
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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

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
        init();
        req.getRequestDispatcher("Login_v2/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        UserForm userForm = new UserForm();
        userForm.setEmail(req.getParameter("email"));
        userForm.setPassword(req.getParameter("pass"));
        userForm.setFirstName(req.getParameter("name"));
        userForm.setLastName(req.getParameter("surname"));

        usersService.register(userForm);

        resp.sendRedirect("/login");
    }
}
