package by.tms.servlets;

import by.tms.entity.User;
import by.tms.entity.UsersOperation;
import by.tms.services.OperationsService;
import by.tms.services.OperationsServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "reportServlet", urlPatterns = "/rep")
public class ReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OperationsService operationService = OperationsServiceImpl.getInstance((Connection) req.getSession().getAttribute("connection"));
        User user = (User) req.getSession().getAttribute("user");
        List<UsersOperation> report = new ArrayList<>();
        if (operationService.operationsListByUserIdContain(user.getId())) {
            report.addAll(operationService.getOperationsListByUserId(user.getId()));
        }
        if (req.getSession().getAttribute("operations") != null) {
            report.addAll((List<UsersOperation>) req.getSession().getAttribute("operations"));
        }
        req.setAttribute("report", report);
        req.getRequestDispatcher("/rep.jsp").forward(req, resp);
    }
}
