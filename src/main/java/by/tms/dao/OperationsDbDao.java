package by.tms.dao;

import by.tms.dao.exceptions.NoResultException;
import by.tms.entity.UsersOperation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperationsDbDao implements OperationsDao {
    private Connection connection;
    private static OperationsDao instance = null;

    private OperationsDbDao(Connection connection) {
        this.connection = connection;
    }

    public static OperationsDao getInstance(Connection connection) {
        if (instance == null) {
            instance = new OperationsDbDao(connection);
        }
        return instance;
    }


    //*
    @Override
    public void setOperationsList(List<UsersOperation> operations) {
        try {
            PreparedStatement statement = connection.prepareStatement(SET_OPERATION);
            for (UsersOperation operation : operations){
                statement.setLong(1, operation.getUserId());
                statement.setDate(2, operation.getDate());
                statement.setString(3, operation.getNum1());
                statement.setString(4, operation.getNum2());
                statement.setString(5, operation.getType());
                statement.setString(6, operation.getResult());
                // добавляет(дублирует) запрос в пакет для каждого элемента из списка :
                statement.addBatch();
            }
            // сетит в БД пакет запросов:
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UsersOperation> getOperationsListByUserId(long userId) {
        List<UsersOperation> operations = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_OPERATIONS_LIST_BY_ID);
            statement.setLong(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                operations.add(new UsersOperation(
                        rs.getDate("date"),
                        rs.getString("num_1"),
                        rs.getString("num_2"),
                        rs.getString("operation_typ"),
                        rs.getString("result")
                ));
            }
            if (!operations.isEmpty()) {
                return operations;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new NoResultException();
    }

    @Override
    public void clineOperationsListByUserId(long userId) {
        try {
            PreparedStatement statement = connection.prepareStatement(CLINE_OPERATIONS_LIST_BY_ID);
            statement.setLong(1, userId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean operationsListByUserIdContain(long userId) {
        try {
            PreparedStatement statement = connection.prepareStatement(GET_OPERATIONS_LIST_BY_ID);
            statement.setInt(1,(int) userId);
            return statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
