package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static String urlBD = "jdbc:mysql://127.0.0.1:3306/PreProject?useSSL=false";
    private static String userBD = "root";
    private static String passwordBD = "1234";
    private static SessionFactory sessionFactory;

    public static Connection getConnectionBD() throws SQLException {
        return DriverManager.getConnection(urlBD, userBD, passwordBD);
    }

    //Шаблонный код с документации для инициализации SessionFactory
    static {
        if (sessionFactory == null) {
            Properties properties = new Properties();
            properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
            properties.put(Environment.URL, urlBD);
            properties.put(Environment.USER, userBD);
            properties.put(Environment.PASS, passwordBD);
            properties.put(Environment.SHOW_SQL, "true");
            properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            final StandardServiceRegistry config = new StandardServiceRegistryBuilder()
                    .applySettings(properties)
                    .build();
            Metadata metadata = new MetadataSources(config)
                    .addAnnotatedClass(User.class)
                    .getMetadataBuilder().build();
            try {
                sessionFactory = metadata.buildSessionFactory();
            } catch (Exception e) {
                StandardServiceRegistryBuilder.destroy(config);
            }
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
