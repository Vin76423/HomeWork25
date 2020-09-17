package by.tms.servlets;

import by.tms.entity.User;
import by.tms.services.UserService;
import by.tms.services.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "registrationServlet", urlPatterns = "/reg")
public class RegistrationServlet extends HttpServlet {
    private final String DUPLICATE_USER = "User with such login already exist in DB!";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getSession().getAttribute("connection");
        UserService service = UserServiceImpl.getInstance(connection);
        if (service.containUserByLogin(req.getParameter("login"))) {
            req.setAttribute("massage", DUPLICATE_USER);
            req.getRequestDispatcher("/reg.jsp").forward(req, resp);
        }else{
            User user = new User(
                    req.getParameter("name"),
                    req.getParameter("login"),
                    req.getParameter("password"));
            service.createUser(user);
            resp.sendRedirect("/");
        }
    }
}
