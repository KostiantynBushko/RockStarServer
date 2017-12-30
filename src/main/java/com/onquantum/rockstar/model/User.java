package com.onquantum.rockstar.model;

import com.onquantum.rockstar.common.SQLDBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Admin on 5/5/16.
 */
public class User {
    int id = 0;
    String email = null;
    String first_name = null;
    String last_name = null;
    int role_id = 0;


    public boolean saveUser() {

        return false;
    }

    public static User GetUserByID(int id) {
        String query = "SELECT * FROM user_tb WHERE id = " + id;
        Statement statement = SQLDBConnector.getStatement();
        ResultSet resultSet = null;

        User user = null;

        try {
            user = new User();
            resultSet = statement.executeQuery(query);
            resultSet.next();
            user.id = resultSet.getInt("id");
            user.email = resultSet.getString("email");
            user.first_name = resultSet.getString("first_name");
            user.last_name = resultSet.getString("last_name");
            user.role_id = resultSet.getInt("role_id");
        } catch (SQLException e) {
            user = null;
            e.printStackTrace();
        } finally {
            try {
                statement.cancel();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public static User GetUserByEmail(String email) {
        String query = "SELECT * FROM user_tb WHERE email = " + email;
        Statement statement = SQLDBConnector.getStatement();
        ResultSet resultSet = null;

        User user = null;

        try {
            user = new User();
            resultSet = statement.executeQuery(query);
            resultSet.next();
            user.id = resultSet.getInt("id");
            user.email = resultSet.getString("email");
            user.first_name = resultSet.getString("first_name");
            user.last_name = resultSet.getString("last_name");
            user.role_id = resultSet.getInt("role_id");
        } catch (SQLException e) {
            user = null;
            e.printStackTrace();
        } finally {
            try {
                statement.cancel();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

}
