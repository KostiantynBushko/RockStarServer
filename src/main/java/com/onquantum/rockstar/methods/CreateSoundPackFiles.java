package com.onquantum.rockstar.methods;

import com.onquantum.rockstar.common.SQLDBConnector;

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
@WebServlet("/create_sound_pack")
public class CreateSoundPackFiles extends HttpServlet {

    // Sound package file name structure <sound pack article> + <_> + <bar> + <_> + <string> + .ogg
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");

        String article = request.getParameter("article");
        if(article == null || article.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Check if sound pack with some nem exist
        Statement statement = SQLDBConnector.getStatement();
        String query = "SELECT * FROM guitar_tb WHERE article = '" + article + "'";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                response.getWriter().write("Error Sound pack exist :"
                        + resultSet.getString("article") + " - " + "with id: "
                        + resultSet.getInt("id") + " description : " + resultSet.getString("description"));
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            } else {
                response.getWriter().write("Ready to create sound pack : " + article);
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            }
        } catch (SQLException e) {
            response.getWriter().write("Error message : " + e.getMessage());
            e.printStackTrace();
        }

    }
}
