package by.tms.servlets;

import by.tms.entity.User;
import by.tms.entity.UsersOperation;
import by.tms.services.CalcService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "calcServlet", urlPatterns = "/calc")
public class CalcServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/calc.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num1 = req.getParameter("num1");
        String num2 = req.getParameter("num2");
        String type = req.getParameter("type");
        String result;
        try {
            result = String.valueOf(CalcService.valueOf(type).run(Double.parseDouble(num1), Double.parseDouble(num2)));
        } catch (IllegalArgumentException e) {
            result = "invalid parameters!";
        } catch (ArithmeticException e) {
            result = "you can't division by zero!";
        }

        // создать список операций положить в сессию
        List<UsersOperation> operations;
        if ((operations = (List<UsersOperation>) req.getSession().getAttribute("operations")) == null) {
            operations = new ArrayList<>();
            req.getSession().setAttribute("operations", operations);
        }
        User user = (User) req.getSession().getAttribute("user");
        operations.add(new UsersOperation(user.getId(), num1, num2, type, result));

        req.setAttribute("result", result);
        req.getRequestDispatcher("/calc.jsp").forward(req, resp);
     }
}
