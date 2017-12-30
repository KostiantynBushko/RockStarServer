package com.onquantum.rockstar.methods;

import com.onquantum.rockstar.model.Tablature;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Admin on 5/7/16.
 */
@WebServlet("/get_tablature_list")
public class GetTablaturesList extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html; charset=cp1251");
        response.setCharacterEncoding("UTF-8");

        int from = 0;
        int limit = 0;

        if (request.getParameter("from") != null) {
            from = Integer.parseInt(request.getParameter("from"));
        }
        if (request.getParameter("limit") != null) {
            limit = Integer.parseInt(request.getParameter("limit"));
        }

        List<Tablature> tablatureList = Tablature.GetTabList(from,limit);
        Iterator<Tablature>iterator = tablatureList.iterator();

        JSONArray jsonArray = new JSONArray();
        while (iterator.hasNext()) {
            Tablature tablature = iterator.next();
            jsonArray.put(tablature.toJSONObject());
        }
        response.getWriter().write(jsonArray.toString());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html; charset=cp1251");
        response.setCharacterEncoding("UTF-8");


        int from = 0;
        int limit = 0;

        if (request.getParameter("from") != null) {
            from = Integer.parseInt(request.getParameter("from"));
        }
        if (request.getParameter("limit") != null) {
            limit = Integer.parseInt(request.getParameter("limit"));
        }

        List<Tablature> tablatureList = Tablature.GetTabList(from,limit);
        Iterator<Tablature>iterator = tablatureList.iterator();

        JSONArray jsonArray = new JSONArray();
        while (iterator.hasNext()) {
            Tablature tablature = iterator.next();
            jsonArray.put(tablature.toJSONObject());
        }
        response.getWriter().write(jsonArray.toString());
    }
}
