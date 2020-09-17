package by.tms.dao;

import by.tms.dao.exceptions.NoResultException;
import by.tms.dao.exceptions.UserNotFoundException;
import by.tms.entity.User;
import java.util.List;

public interface UserDao {
    String CREATE_USER = "INSERT INTO `users` VALUES(null, ?, ?, ?)";
    String GET_ALL_USERS = "SELECT * FROM `users`";
    String GET_USER_BY_ID = "SELECT * FROM `users` WHERE id = ?";
    String GET_USER_BY_LOGIN = "SELECT * FROM `users` WHERE `login` = ?";
    String UPDATE_USER_BY_ID = "UPDATE `users` SET `name` = ?, `login` = ?, `password` = ? WHERE id = ?";
    String DELETE_USER_BY_ID = "DELETE FROM `users` WHERE id = ?";

    void createUser(User user);
    List<User> getAllUsers();
    User getUserById(long id) throws UserNotFoundException;
    User getUserByLogin(String login) throws  UserNotFoundException;
    void updateUserById(User user);
    void deleteUserById(long id);
    boolean containUserById(long id);
    boolean containUserByLogin(String login);
}
