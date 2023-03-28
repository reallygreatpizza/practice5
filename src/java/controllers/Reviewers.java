/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import business.Movie;
import business.Review;
import data.MovieDB;
import data.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


/**
 *
 * @author fssco
 */
public class Reviewers extends HttpServlet {

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
        String url = "/reviews/viewUserReviews.jsp";

        //https://stackoverflow.com/a/6671686
        //get logged in user
        Principal user = request.getUserPrincipal();
        int userID = -1;
        try {
            userID = UserDB.getIDforUsername(user.getName());
        } catch (SQLException ex) {
            Logger.getLogger(Reviewers.class.getName()).log(Level.SEVERE, null, ex);
        }

        String action = request.getParameter("action");

        if (action == null) {
            action = "view";
        }

        if (action.equals("deleteReview")) {
            int ratingID = Integer.parseInt(request.getParameter("ratingID"));

            try {
                MovieDB.deleteReview(ratingID, userID);
            } catch (SQLException ex) {
                Logger.getLogger(Reviewers.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        LinkedHashMap<Integer, Review> reviews = null;
        LinkedHashMap<Integer, Movie> movies = null;
        try {
            reviews = MovieDB.getReviewsForID(userID);
            movies = MovieDB.selectAllMovies();

        } catch (Exception ex) {
            Logger.getLogger(Reviewers.class.getName()).log(Level.SEVERE, null, ex);

        }
        request.setAttribute("reviews", reviews);
        request.setAttribute("movies", movies);

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
