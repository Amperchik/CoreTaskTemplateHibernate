package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private String createTable = "Create table  if NOT EXISTS UserDB (id BIGINT PRIMARY KEY AUTO_INCREMENT " +
            "NOT NULL,name varchar(256) NOT NULL ,lastName varchar(256) not null,age int not null);";
    private String dropTable = "DROP table  if  EXISTS UserDB;";

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        Session session = null;
        try {
            session = Util.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(createTable).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session != null) session.getTransaction().rollback();
            System.out.println("Таблица не создана");
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        try {
            session = Util.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(dropTable).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session != null) session.getTransaction().rollback();
            System.out.println("Таблица не удалена");
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        try {
            session = Util.getSessionFactory().getCurrentSession();
            System.out.println(session);
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session != null) session.getTransaction().rollback();
            System.out.println("Данные не сохранены");
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        try {
            session = Util.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            System.out.println("Пользователь не удален");
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> table = new ArrayList<>();
        Session session = null;
        try {
            session = Util.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            table = session.createQuery("From " + User.class.getSimpleName()).list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session != null) session.getTransaction().rollback();
            System.out.println("Данные не получены");
            e.printStackTrace();
        }
        return table;
    }


    @Override
    public void cleanUsersTable() {
        Session session = null;
        try {
            session = Util.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session != null) session.getTransaction().rollback();
            System.out.println("Таблица не очищена");
            e.printStackTrace();
        }

    }
}
