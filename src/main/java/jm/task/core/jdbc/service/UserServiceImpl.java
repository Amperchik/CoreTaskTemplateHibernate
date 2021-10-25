package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserDaoJDBCImpl reliz = new UserDaoJDBCImpl();

    public void createUsersTable() {
        reliz.createUsersTable();
    }

    public void dropUsersTable() {
        reliz.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        reliz.saveUser(name, lastName, age);
        System.out.printf("User с именем – %s добавлен в базу данных\n",name);
    }

    public void removeUserById(long id) {
        reliz.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return reliz.getAllUsers();
    }

    public void cleanUsersTable() {
        reliz.cleanUsersTable();
    }
}
