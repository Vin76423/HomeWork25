package by.tms.servlets;

import by.tms.entity.User;
import by.tms.services.UserService;
import by.tms.services.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "authorisationServlet", urlPatterns = "/auth")
public class AuthorisationServlet extends HttpServlet {
    private final String INVALID_AUTHORISATION = "You write invalid login or/and password!";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/auth.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session =  req.getSession();
        Connection connection = (Connection) session.getAttribute("connection");
        UserService service = UserServiceImpl.getInstance(connection);

        String checkedLogin = req.getParameter("login");
        String checkedPassword = req.getParameter("password");
        User user;

        if ((service.containUserByLogin(checkedLogin)) && (user = service.getUserByLogin(checkedLogin)).getPassword().equals(checkedPassword)) {
            System.out.println(session.getId()); // J_SESSION_ID
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(5*60); // вручную настраиваем время жизни сессии в секундах
            resp.sendRedirect("/");
        }else{
            req.setAttribute("massage", INVALID_AUTHORISATION);
            req.getRequestDispatcher("/auth.jsp").forward(req, resp);
        }
    }
}
