package by.tms.dao;

import by.tms.dao.exceptions.NoResultException;
import by.tms.dao.exceptions.UserNotFoundException;
import by.tms.entity.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDbDao implements UserDao {
    private Connection connection;
    private static UserDao instance = null;

    private UserDbDao(Connection connection) {
        this.connection = connection;
    }

    public static UserDao getInstance(Connection connection) {
        if (instance == null) {
            instance = new UserDbDao(connection);
        }
        return instance;
    }

    // privat util method:
    private User buildUserBySqlFields(ResultSet rs) throws SQLException {
        return new User(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("login"),
                rs.getString("password")
        );
    }



    @Override
    public void createUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(CREATE_USER);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_USERS);
            while (rs.next()) {
                users.add(buildUserBySqlFields(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (users.isEmpty()) {
            throw new NoResultException();
        }
        return users;
    }

    @Override
    public User getUserById(long id) throws UserNotFoundException {
        try {
            PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return buildUserBySqlFields(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new UserNotFoundException();
    }

    @Override
    public User getUserByLogin(String login) throws UserNotFoundException {
        try {
            PreparedStatement statement = connection.prepareStatement(GET_USER_BY_LOGIN);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return buildUserBySqlFields(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new UserNotFoundException();
    }

    @Override
    public void updateUserById(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER_BY_ID);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setLong(4, user.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserById(long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID);
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean containUserById(long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID);
            statement.setLong(1, id);
            return statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean containUserByLogin(String login) {
        try {
            PreparedStatement statement = connection.prepareStatement(GET_USER_BY_LOGIN);
            statement.setString(1, login);
            return statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
