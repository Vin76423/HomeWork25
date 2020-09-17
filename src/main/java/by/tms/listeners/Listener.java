package by.tms.listeners;


import by.tms.entity.UsersOperation;
import by.tms.services.OperationsServiceImpl;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@WebListener
public class Listener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        ComboPooledDataSource dataSource = new ComboPooledDataSource();
//        try {
//            dataSource.setDriverClass("com.mysql.jdbc.Driver");
//            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/students_jsp_db");
//            dataSource.setUser("root");
//            dataSource.setPassword("my76sql423ol28eg28a_");
//
//            Properties properties = new Properties();
//            properties.setProperty("user", "root");
//            properties.setProperty("password", "my76sql423ol28eg28a_");
//            properties.setProperty("useUnicode", "true");
//            properties.setProperty("characterEncoding", "UTF8");
//            dataSource.setProperties(properties);
//
//            // set options:
//            dataSource.setMaxStatements(180);
//            dataSource.setMaxStatementsPerConnection(180);
//            dataSource.setMinPoolSize(50);
//            dataSource.setAcquireIncrement(10);
//            dataSource.setMaxPoolSize(60);
//            dataSource.setMaxIdleTime(30);
//        } catch (PropertyVetoException e) {
//            e.printStackTrace();
//        }
//
//        sce.getServletContext().setAttribute("dataSource", dataSource);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // SET connection for every user's session:
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/web_application_by_servlet?serverTimezone=UTC",
                    "root",
                    "my76sql423ol28eg28a_");
            se.getSession().setAttribute("connection", connection);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        Connection connection = (Connection) se.getSession().getAttribute("connection");

        // SET operations list in DB:
        List<UsersOperation> operations = (List<UsersOperation>) se.getSession().getAttribute("operations");
        if (operations != null) {
            OperationsServiceImpl.getInstance(connection).setOperationsList(operations); // ???????
        }

        // CLOSE connection for every user's session:
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
