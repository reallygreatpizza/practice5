package controllers;

import business.User;
import data.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Really Great Pizza
 */
public class adminController extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            LinkedHashMap<Integer, User> users = null;
            HashMap<String, String> errors = new HashMap();
            
                   try {
                    users = UserDB.getAllUsers();
                    } catch (SQLException e) {
                        errors.put("select0", "Error with SQL in the database.");
                    } catch (Exception e) {
                        errors.put("select0", "Error with the database.");
                    }        
    request.setAttribute("users", users);
    request.getRequestDispatcher("/admin/viewAdmin.jsp").forward(request, response);
    
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
