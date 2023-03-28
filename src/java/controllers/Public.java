/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import business.Movie;
import business.User;
import data.MovieDB;
import data.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.realm.SecretKeyCredentialHandler;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 *
 * @author fssco
 */
public class Public extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Logger LOG = Logger.getLogger(Public.class.getName());

        String url = "/index.jsp";

        LinkedHashMap<Integer, Movie> movies = null;
        try {
            movies = MovieDB.selectAllMovies();
            MovieDB.getReviewsForID(1);
            MovieDB.selectAllMovies();
            UserDB.getAllUsers();
        } catch (SQLException ex) {
            Logger.getLogger(Public.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("movies", movies);

        String action = request.getParameter("action");
        if (action == null) {
            action = "viewMovies";
        }

        switch (action) {
            case "regForm": {
                url = "/register.jsp";
                break;
            }

            case "register": {
                String email = request.getParameter("email");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String userName = request.getParameter("userName");
                String password = request.getParameter("password");
                String hash = "";

                SecretKeyCredentialHandler ch;

                try {
                    ch = new SecretKeyCredentialHandler();
                    ch.setAlgorithm("PBKDF2WithHmacSHA256");
                    ch.setKeyLength(256);
                    ch.setSaltLength(16);
                    ch.setIterations(4096);

                    hash = ch.mutate(password);
                } catch (Exception ex) {
                    LOG.log(Level.SEVERE, null, ex);

                }

                User user = new User(email, firstName, lastName, userName, hash);
                user.addRole("reviewer");

                try {
                    UserDB.insertIntoUser(user);

                    UserDB.insertRoles(user);
                } catch (Exception ex) {
                    LOG.log(Level.SEVERE, null, ex);;
                }

            }
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
