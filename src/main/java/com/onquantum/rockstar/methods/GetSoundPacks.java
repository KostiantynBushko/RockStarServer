package com.onquantum.rockstar.methods;

import com.onquantum.rockstar.common.SQLDBConnector;
import com.onquantum.rockstar.model.PackageStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Admin on 7/7/15.
 */

@WebServlet("/get_sound_packs")
public class GetSoundPacks extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=cp1251");
        response.setCharacterEncoding("UTF-8");

        String fromId = request.getParameter("fromId");
        String statusId = request.getParameter("status_id");
        String select_query = " = 2";

        if(fromId == null)
            fromId = "1";

        if(statusId == null) {
            select_query = " = 2";
        }else {
            if(statusId.equals("0")) {
                select_query = " >= 1";
            } else if(statusId.equals("1")) {
                select_query = " = 1";
            } else if(statusId.equals("2")) {
                select_query = " = 2";
            } else if(statusId.equals("3")) {
                select_query = " = 3";
            }
        }


        String query = "SELECT * FROM guitar_tb WHERE id >= " + fromId + " AND status_id " + select_query;


        JSONArray guitarsArray = new JSONArray();
        Statement statement = SQLDBConnector.getStatement();
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                JSONObject guitar = new JSONObject();

                guitar.put("_id",resultSet.getInt("id"));
                guitar.put("_name",resultSet.getString("name"));
                guitar.put("_article", resultSet.getString("article"));
                guitar.put("_icon",resultSet.getString("icon"));
                guitar.put("_purchase_id", resultSet.getInt("purchase_id"));
                guitar.put("_sample_sound", resultSet.getString("sample_sound"));
                guitar.put("_description", resultSet.getString("description"));
                guitar.put("_status", PackageStatus.Status.values()[Integer.parseInt(resultSet.getString("status_id"))].name());
                guitarsArray.put(guitar);
            }
            response.getWriter().write(guitarsArray.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=cp1251");
        request.setCharacterEncoding("UTF-8");

        String fromId = request.getParameter("fromId");
        String statusId = request.getParameter("status_id");
        String select_query = " = 2";

        if(fromId == null)
            fromId = "1";

        if(statusId == null) {
            select_query = " = 2";
        }else {
            if(statusId.equals("0")) {
                select_query = " >= 1";
            } else if(statusId.equals("1")) {
                select_query = " = 1";
            } else if(statusId.equals("2")) {
                select_query = " = 2";
            } else if(statusId.equals("3")) {
                select_query = " = 3";
            }
        }


        String query = "SELECT * FROM guitar_tb WHERE id >= " + fromId + " AND status_id " + select_query;


        JSONArray guitarsArray = new JSONArray();
        Statement statement = SQLDBConnector.getStatement();
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                JSONObject guitar = new JSONObject();

                guitar.put("_id",resultSet.getInt("id"));
                guitar.put("_name",resultSet.getString("name"));
                guitar.put("_article", resultSet.getString("article"));
                guitar.put("_icon",resultSet.getString("icon"));
                guitar.put("_purchase_id", resultSet.getInt("purchase_id"));
                guitar.put("_sample_sound", resultSet.getString("sample_sound"));
                guitar.put("_description", resultSet.getString("description"));
                guitar.put("_status", PackageStatus.Status.values()[Integer.parseInt(resultSet.getString("status_id"))].name());
                guitarsArray.put(guitar);
            }
            response.getWriter().write(guitarsArray.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
