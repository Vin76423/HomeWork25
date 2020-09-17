package by.tms.filters;

import by.tms.entity.User;
import by.tms.services.OperationsServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

// Filter for all servlets:
@WebFilter(filterName = "globalFilter", urlPatterns = "/*")
public class GlobalFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        Connection connection = (Connection) req.getSession().getAttribute("connection");
        User user;


//        if ((user = (User) req.getSession().getAttribute("user")) != null) {
//            if (req.getSession().getAttribute("operations") != null ||
//                    UsersOperationServiceImpl.getInstance(connection).operationsListByUserIdContain(user.getId())) {
//                req.setAttribute("reportFlag" , true);
//            }else {
//                req.setAttribute("reportFlag" , false);
//            }
//        }else {
//            req.setAttribute("reportFlag" , false);
//        }
//        chain.doFilter(req, res);


        if ((user = (User) req.getSession().getAttribute("user")) != null &&
                (req.getSession().getAttribute("operations") != null ||
                        OperationsServiceImpl.getInstance(connection).operationsListByUserIdContain(user.getId()))) {
            req.setAttribute("reportFlag" , true);
        }else {
            req.setAttribute("reportFlag" , false);
        }

        chain.doFilter(req, res);
    }
}
