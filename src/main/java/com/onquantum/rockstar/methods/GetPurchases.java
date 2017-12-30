package com.onquantum.rockstar.methods;

import com.onquantum.rockstar.common.SQLDBConnector;
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
 * Created by Admin on 7/29/15.
 */
@WebServlet("/get_purchases")
public class GetPurchases extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html charset=cp1251");
        response.setCharacterEncoding("UTF-8");

        String fromId = request.getParameter("fromId");
        if(fromId == null)
            fromId = "1";
        String query = "SELECT * FROM purchase_tb WHERE id >= " + fromId;

        JSONArray purchaseArray = new JSONArray();
        Statement statement = SQLDBConnector.getStatement();
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                JSONObject purchase = new JSONObject();
                purchase.put("_id",resultSet.getInt("id"));
                purchase.put("_bundle",resultSet.getString("bundle"));
                purchase.put("_product_name",resultSet.getString("product_name"));
                purchase.put("_description",resultSet.getString("description"));
                purchase.put("_price",resultSet.getString("price"));
                purchase.put("_currency_code",resultSet.getString("currency_code"));
                purchaseArray.put(purchase);
            }
            response.getWriter().write(purchaseArray.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html charset=cp1251");
        response.setCharacterEncoding("UTF-8");

        String fromId = request.getParameter("fromId");
        if(fromId == null)
            fromId = "1";
        String query = "SELECT * FROM purchase_tb WHERE id >= " + fromId;

        JSONArray purchaseArray = new JSONArray();
        Statement statement = SQLDBConnector.getStatement();
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                JSONObject purchase = new JSONObject();
                purchase.put("_id",resultSet.getInt("id"));
                purchase.put("_bundle",resultSet.getString("bundle"));
                purchase.put("_product_name",resultSet.getString("product_name"));
                purchase.put("_description",resultSet.getString("description"));
                purchase.put("_price",resultSet.getString("price"));
                purchase.put("_currency_code",resultSet.getString("currency_code"));
                purchaseArray.put(purchase);
            }
            response.getWriter().write(purchaseArray.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
