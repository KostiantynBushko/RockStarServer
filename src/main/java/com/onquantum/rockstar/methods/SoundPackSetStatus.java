package com.onquantum.rockstar.methods;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Admin on 12/13/15.
 */
public class SoundPackSetStatus extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String package_name = request.getParameter("package").toLowerCase();
        String status = request.getParameter("status").toLowerCase();
        if(package_name != null && status != null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

    }
}
