package by.tms.services;

import by.tms.dao.UserDao;
import by.tms.dao.UserDbDao;
import by.tms.dao.exceptions.UserNotFoundException;
import by.tms.entity.User;
import by.tms.services.exceptions.UserDuplicateException;
import java.sql.Connection;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private static UserService instance = null;

    private UserServiceImpl(Connection connection) {
        this.userDao = UserDbDao.getInstance(connection);
    }

    public static UserService getInstance(Connection connection) {
        if (instance == null) {
            instance = new UserServiceImpl(connection);
        }
        return instance;
    }


    @Override
    public void createUser(User user) {
        if (userDao.containUserByLogin(user.getLogin())) {
            throw new UserDuplicateException();
        }
        userDao.createUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
        // если возвращаемый список пуст, бахнeт исключение в рантайме на уровне dao
    }

    @Override
    public User getUserById(long id) {
        try {
            return userDao.getUserById(id);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        // до null дело так и не дойдет, чуть что бахнет исключение
        return null;
    }

    @Override
    public User getUserByLogin(String login) {
        try {
            return userDao.getUserByLogin(login);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        // до null дело так и не дойдет, чуть что бахнет исключение
        return null;
    }

    @Override
    public void updateUserById(User user) {
        if (userDao.containUserById(user.getId())) {
            userDao.updateUserById(user);
        }
    }

    @Override
    public void deleteUserById(long id) {
        if (userDao.containUserById(id)) {
            userDao.deleteUserById(id);
        }
    }

    @Override
    public boolean containUserById(long id) {
        return userDao.containUserById(id);
    }

    @Override
    public boolean containUserByLogin(String login) {
        return userDao.containUserByLogin(login);
    }
}
