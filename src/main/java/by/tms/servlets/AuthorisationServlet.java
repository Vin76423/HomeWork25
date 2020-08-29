package by.tms.servlets;

import by.tms.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "authorisationServlet", urlPatterns = "/auth")
public class AuthorisationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/auth.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = (List<User>) getServletContext().getAttribute("users");
        for (User user : users){
            if (user.getLogin().equals(req.getParameter("login")) && user.getPassword().equals(req.getParameter("password"))){
                req.getSession().setAttribute("user", user);
                resp.sendRedirect("/");
            }else{
                req.getRequestDispatcher("/auth.jsp").forward(req, resp);
            }
        }
    }
}
