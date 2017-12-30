package com.onquantum.rockstar.methods;

import com.onquantum.rockstar.common.SQLDBConnector;
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
 * Created by Admin on 9/13/15.
 */
@WebServlet("/get_sound_pack_by_name")
public class GetSounPackByName extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        String name = request.getParameter("name");
        if(name == null || name.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String queryGetGuitar = "SELECT * FROM rockstar.guitar_tb WHERE name = '" + name + "'";
        Statement statement = SQLDBConnector.getStatement();
        ResultSet resultSet;

        JSONObject resultObject = new JSONObject();
        try {
            resultSet = statement.executeQuery(queryGetGuitar);
            if(!resultSet.next()) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                response.getWriter().write("The package with some name " + name + " not exist");
                return;
            }

            resultObject.put("_id", resultSet.getInt("id"));
            resultObject.put("_name",resultSet.getString("name"));
            resultObject.put("_article", resultSet.getString("article"));
            resultObject.put("_icon",resultSet.getString("icon"));
            resultObject.put("_purchase_id", resultSet.getInt("purchase_id"));
            resultObject.put("_sample_sound", resultSet.getString("sample_sound"));
            resultObject.put("_description", resultSet.getString("description"));
            resultObject.put("_status",resultSet.getString("status"));

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(resultObject.toString());

        }catch (SQLException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        String name = request.getParameter("name");
        if(name == null || name.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String queryGetGuitar = "SELECT * FROM rockstar.guitar_tb WHERE name = '" + name + "'";
        Statement statement = SQLDBConnector.getStatement();
        ResultSet resultSet;

        JSONObject resultObject = new JSONObject();
        try {
            resultSet = statement.executeQuery(queryGetGuitar);
            if(!resultSet.next()) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                response.getWriter().write("The package with some name " + name + " not exist");
                return;
            }

            resultObject.put("_id", resultSet.getInt("id"));
            resultObject.put("_name",resultSet.getString("name"));
            resultObject.put("_article", resultSet.getString("article"));
            resultObject.put("_icon",resultSet.getString("icon"));
            resultObject.put("_purchase_id", resultSet.getInt("purchase_id"));
            resultObject.put("_sample_sound", resultSet.getString("sample_sound"));
            resultObject.put("_description", resultSet.getString("description"));
            resultObject.put("_status",resultSet.getString("status"));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(resultObject.toString());

        }catch (SQLException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
