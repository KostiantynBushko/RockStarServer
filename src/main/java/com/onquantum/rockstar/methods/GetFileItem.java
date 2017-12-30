package com.onquantum.rockstar.methods;

import com.onquantum.rockstar.model.File;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Admin on 5/8/16.
 */
@WebServlet("/get_file_item")
public class GetFileItem extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/json; charset=cp1251");
        response.setCharacterEncoding("UTF-8");

        int id = 0;

        if (request.getParameter(File.ID) != null) {
            id = Integer.parseInt(request.getParameter(File.ID));
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        File file = File.GetFileByID(id);
        if (file == null) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.getWriter().write(file.toJSONObject().toString());
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/json; charset=cp1251");
        response.setCharacterEncoding("UTF-8");

        int id = 0;

        if (request.getParameter(File.ID) != null) {
            id = Integer.parseInt(request.getParameter(File.ID));
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        File file = File.GetFileByID(id);
        if (file == null) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.getWriter().write(file.toJSONObject().toString());
        }

    }
}
