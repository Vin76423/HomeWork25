package daoTests;

import by.tms.dao.UserDao;
import by.tms.dao.UserDbDao;
import by.tms.dao.exceptions.UserNotFoundException;
import by.tms.entity.User;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTestByPoolConnections {
    private static final ComboPooledDataSource DATA_SOURCE = new ComboPooledDataSource();

    @BeforeClass
    public static void setProperties() {
        try {
            DATA_SOURCE.setDriverClass("com.mysql.jdbc.Driver");
            DATA_SOURCE.setJdbcUrl("jdbc:mysql://localhost:3306/web_application_by_servlet?serverTimezone=UTC");
            DATA_SOURCE.setUser("root");
            DATA_SOURCE.setPassword("my76sql423ol28eg28a_");

            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "my76sql423ol28eg28a_");
            properties.setProperty("useUnicode", "true");
            properties.setProperty("characterEncoding", "UTF8");
            DATA_SOURCE.setProperties(properties);

            // set options:
            DATA_SOURCE.setMaxStatements(180);
            DATA_SOURCE.setMaxStatementsPerConnection(180);
            DATA_SOURCE.setMinPoolSize(50);
            DATA_SOURCE.setAcquireIncrement(10);
            DATA_SOURCE.setMaxPoolSize(60);
            DATA_SOURCE.setMaxIdleTime(30);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void T01___CREATE___GET_ALL_USER___Test() {
        List<User> expectedStudent = new ArrayList<>();
        try {
            Connection connection = DATA_SOURCE.getConnection();
            UserDao userDao = UserDbDao.getInstance(connection);
            userDao.createUser(new User("Andrey", "Andrey@1994", "29111993helo"));
            userDao.createUser(new User("Mihail", "Misha@org", "93helo1111"));
            expectedStudent = userDao.getAllUsers();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(4, expectedStudent.size());
    }

    @Test
    public void T02___UPDATE___GET_USER_BY_ID___Test() {
        User user = new User(2, "Ivan", "Vnechka47@", "2911_777");
        User expectedStudent = null;
        try {
            Connection connection = DATA_SOURCE.getConnection();
            UserDao userDao = UserDbDao.getInstance(connection);
            userDao.updateUserById(user); // update
            expectedStudent = userDao.getUserById(2L); // get user by id
            connection.close();
        } catch (UserNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        Assert.assertEquals("Ivan", expectedStudent.getName());
    }

    @Test
    public void T03___CONTAIN_BY_LOGIN___GET_USER_BY_LOGIN___Test() {
        User expectedStudent = null;
        try {
            Connection connection = DATA_SOURCE.getConnection();
            UserDao userDao = UserDbDao.getInstance(connection);
            if (userDao.containUserByLogin("Misha@org")) {
                expectedStudent = userDao.getUserByLogin("Misha@org");
            }
            connection.close();
        } catch (UserNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        Assert.assertEquals("Mihail", expectedStudent.getName());
    }

    @Test
    public void T04___DELETE___Test() {
        try {
            Connection connection = DATA_SOURCE.getConnection();
            UserDao userDao = UserDbDao.getInstance(connection);
            if (userDao.containUserById(3L)) {
                userDao.deleteUserById(3L);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
