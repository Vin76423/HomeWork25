package by.tms.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "reportFilter", urlPatterns = "/rep")
public class ReportFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if ((boolean) req.getAttribute("reportFlag")) {
            chain.doFilter(req, res);
        } else {
            res.sendRedirect("/");
        }
    }
}
