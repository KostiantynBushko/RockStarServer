package com.onquantum.rockstar.common;

/**
 * Created by Admin on 7/7/15.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by Admin on 10/21/14.
 */
public class SQLDBConnector {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/rockstar";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";
    private static final String CHARACTER_ENCODING = "cp1251";
    private static final String UNICODE = "true";

    public static Connection getConnection() {
        Connection connection;
        Properties properties = new Properties();
        properties.setProperty("user", JDBC_USER);
        properties.setProperty("password", JDBC_PASSWORD);
        properties.setProperty("useUnicode", UNICODE);
        properties.setProperty("characterEncoding", CHARACTER_ENCODING);
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, properties);
        } catch (ClassNotFoundException e) {
            e.getLocalizedMessage();
            System.out.println("Connection Failed! Driver not found");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.getLocalizedMessage();
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;
        }
        return connection;
    }

    public static Statement getStatement() {
        try {
            return getConnection().createStatement();
        } catch (SQLException e) {
            e.getLocalizedMessage();
            System.out.println("Get Statement Failed! Check output console");
            e.printStackTrace();
            return null;
        }
    }
}
