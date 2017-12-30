package com.onquantum.rockstar.model;

import com.onquantum.rockstar.common.SQLDBConnector;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Admin on 5/6/16.
 */
public class File implements Model {

    public static String TABLE = "file_tb";

    public static String ID = "id";
    public static String USER_ID = "user_id";
    public static String FILE_NAME = "file_name";
    public static String FULL_PATH = "full_path";

    public int id = 0;
    public int user_id = 0;
    public String file_name = null;
    public String full_path = null;

    @Override
    public String toString() {
        return File.ID + ": " + this.id + ", " + File.USER_ID + ": " + this.user_id + ", " + File.FILE_NAME + ": " + this.file_name + ", " + File.FULL_PATH + ": " + this.full_path;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(File.ID, this.id);
            jsonObject.put(File.USER_ID, this.user_id);
            jsonObject.put(File.FILE_NAME, this.file_name);
            jsonObject.put(File.FULL_PATH, this.full_path);
        } catch (org.json.JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static File GetFileByID(int id) {
        String query = "SELECT * FROM " + File.TABLE + " WHERE " + File.ID + " = " + id;
        Statement statement = SQLDBConnector.getStatement();
        ResultSet resultSet = null;

        File file = null;
        try {
            resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                file = new File();
                file.id = resultSet.getInt(File.ID);
                file.user_id = resultSet.getInt(File.USER_ID);
                file.file_name = resultSet.getString(File.FILE_NAME);
                file.full_path = resultSet.getString(File.FULL_PATH);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.cancel();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
