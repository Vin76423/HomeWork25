package daoTests;

import by.tms.dao.OperationsDao;
import by.tms.dao.OperationsDbDao;
import by.tms.dao.UserDao;
import by.tms.dao.UserDbDao;
import by.tms.entity.User;
import by.tms.entity.UsersOperation;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OperationsTest {
    private static Connection CONNECTION;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            CONNECTION = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/web_application_by_servlet?serverTimezone=UTC",
                    "root",
                    "my76sql423ol28eg28a_");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void T01___CREATE_OPERATIONS_LIST___Test() {
        UsersOperation [] ops = {
                new UsersOperation(6L, "3", "7", "SUM", "10"),
                new UsersOperation(6L, "10", "2", "DIV", "5"),
                new UsersOperation(6L, "12", "0", "SUB", "12")};
        OperationsDbDao.getInstance(CONNECTION).setOperationsList(Arrays.asList(ops));
    }

    @Test
    public void T02___GET_OPERATIONS_BY_USERS_ID___Test() {
        List<UsersOperation> expectedList = OperationsDbDao.getInstance(CONNECTION).getOperationsListByUserId(6L);
        Assert.assertEquals(2, expectedList.size());
    }

    @Test
    public void T03___CONTAIN_OPERATIONS_BY_USERS_ID___Test() {
        Boolean answer = OperationsDbDao.getInstance(CONNECTION).operationsListByUserIdContain(6L);
        Assert.assertEquals(true, answer);
    }

    @Test
    public void T04___DELETE_OPERATIONS_BY_USERS_ID___Test() {
        OperationsDbDao.getInstance(CONNECTION).clineOperationsListByUserId(6L);
    }
}
