package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args)  {
        UserServiceImpl contactBD = new UserServiceImpl();
        contactBD.createUsersTable();
        User a = new User("Vasy1", "Pupkin1", (byte) 10);
        User b = new User("Vasy2", "Pupkin2", (byte) 12);
        User c = new User("Vasy3", "Pupkin3", (byte) 14);
        User d = new User("Vasy4", "Pupkin4", (byte) 16);
        contactBD.saveUser(a.getName(), a.getLastName(), a.getAge());
        contactBD.saveUser(b.getName(), b.getLastName(), b.getAge());
        contactBD.saveUser(c.getName(), c.getLastName(), c.getAge());
        contactBD.saveUser(d.getName(), d.getLastName(), d.getAge());
        System.out.println(contactBD.getAllUsers().toString());
        contactBD.cleanUsersTable();
        contactBD.dropUsersTable();
    }
}
