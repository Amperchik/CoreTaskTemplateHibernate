package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static String urlBD="jdbc:mysql://127.0.0.1:3306/PreProject?useSSL=false";
    private static String userBD="root";
    private static String passwordBD="1234";
    public static Connection getConnectionBD() throws SQLException {
        return DriverManager.getConnection(urlBD,userBD,passwordBD);
    }
}
