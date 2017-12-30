package com.onquantum.rockstar.model;

import com.onquantum.rockstar.common.SQLDBConnector;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 5/6/16.
 */
public class Tablature implements Model {

    private static String TABLE = "tab_tb";

    public static String ID = "id";
    public static String NAME = "name";
    public static String FILE_ID = "file_id";

    int id = 0;
    String name = null;
    int file_id = 0;

    @Override
    public String toString() {
        return "id: " + this.id + ", name: " + this.name + ", file_id: " + this.file_id;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Tablature.ID, this.id);
            jsonObject.put(Tablature.NAME, this.name);
            jsonObject.put(Tablature.FILE_ID, this.file_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static List<Tablature> GetTabList(long from, long limit) {

        String query = "SELECT * FROM " + Tablature.TABLE;
        if(limit > 0) {
            if (from == 0)
                from = 1;
            from -= 1;
            query += " LIMIT " + from + "," + limit;
        }

        Statement statement = SQLDBConnector.getStatement();
        ResultSet resultSet = null;

        List<Tablature>tablatureList = new ArrayList<Tablature>();

        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Tablature tablature = new Tablature();
                tablature.id = resultSet.getInt(Tablature.ID);
                tablature.name = resultSet.getString(Tablature.NAME);
                tablature.file_id = resultSet.getInt(Tablature.FILE_ID);
                tablatureList.add(tablature);
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
        return tablatureList;
    }
}
