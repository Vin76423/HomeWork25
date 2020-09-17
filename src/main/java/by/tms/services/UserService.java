package by.tms.services;

import by.tms.dao.exceptions.NoResultException;
import by.tms.dao.exceptions.UserNotFoundException;
import by.tms.entity.User;
import by.tms.services.exceptions.UserDuplicateException;

import java.util.List;

public interface UserService {
    void createUser(User user);
    List<User> getAllUsers();
    User getUserById(long id);
    User getUserByLogin(String login);
    void updateUserById(User user);
    void deleteUserById(long id);
    boolean containUserById(long id);
    boolean containUserByLogin(String login);
}
