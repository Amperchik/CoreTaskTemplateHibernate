package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    //Шаблоны SQL запросов на создание\удаление\отчистки\добавление юзера\удаление юзера\получение таблицы.
    private  String createTable = "Create table  if NOT EXISTS UserDB (id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL " +
            ", nam varchar(256) NOT NULL ,lastName varchar(256) not null, age int not null);";
    private  String dropTable = "DROP table  if  EXISTS UserDB;";
    private  String cleanTable = "TRUNCATE TABLE userDB;";
    private  String addUser ="INSERT INTO UserDB(nam,lastName,age) VALUES (?,?,?)";
    private  String deleteUser = "DELETE FROM userDB WHERE id=?";
    private  String getAllUsers = "SELECT * FROM userDB;";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection create = Util.getConnectionBD();
             PreparedStatement queryCreate = create.prepareStatement(this.createTable)) {
            queryCreate.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection create = Util.getConnectionBD();
             PreparedStatement queryDrop = create.prepareStatement(this.dropTable)) {
            queryDrop.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection create = Util.getConnectionBD();
             PreparedStatement queryDrop = create.prepareStatement(this.addUser)) {
            queryDrop.setByte(3,age);
            queryDrop.setString(1,name);
            queryDrop.setString(2,lastName);
            queryDrop.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection create = Util.getConnectionBD();
             PreparedStatement queryDrop = create.prepareStatement(this.deleteUser)) {
            queryDrop.setLong(1,id);
            queryDrop.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User>table=new ArrayList<>();
        try (Connection create = Util.getConnectionBD();
             PreparedStatement queryDrop = create.prepareStatement(this.getAllUsers)) {
           ResultSet otvet=queryDrop.executeQuery();
           while(otvet.next()){
               Long id=otvet.getLong(1);
               String name=otvet.getString(2);
               String lastName=otvet.getString(3);
               Byte age=otvet.getByte(4);
               User Temp=new User(name,lastName,age);
               Temp.setId(id);
               table.add(Temp);
           }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return table;
    }

    public void cleanUsersTable() {
        try (Connection create = Util.getConnectionBD();
             PreparedStatement queryDrop = create.prepareStatement(this.cleanTable)) {
            queryDrop.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
