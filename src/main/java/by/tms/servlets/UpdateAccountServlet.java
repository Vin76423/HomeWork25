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

@WebServlet(name = "updateAccountServlet", urlPatterns = "/updateAccount")
public class UpdateAccountServlet extends HttpServlet {
    private final String INVALID_VALUE = "You write invalid %s !";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/updateAccount.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getSession().getAttribute("connection");
        UserService service = UserServiceImpl.getInstance(connection);
        User user = (User) req.getSession().getAttribute("user");
        String value = req.getParameter("value");

        if (value.length() == 5) {
            req.setAttribute("massage", String.format(INVALID_VALUE, req.getParameter("field")));
            req.getRequestDispatcher("/updateAccount.jsp").forward(req, resp);
        } else {
            switch (req.getParameter("field")) {
                case "name":
                    user.setName(value);
                    break;
                case "login":
                    user.setLogin(value);
                    break;
                case "password":
                    user.setPassword(value);
                    break;
            }
            service.updateUserById(user);
            resp.sendRedirect("/account");
        }
    }
}
