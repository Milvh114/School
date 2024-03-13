/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers.product;

import dao.ImageProductDAO;
import dao.ReviewDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.ImageProduct;
import model.Review;

/**
 *
 * @author admin
 */
public class RateController extends HttpServlet {
    private final int recordsPerPage = 2;
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         request.getRequestDispatcher("views/Product/rating.jsp").forward(request, response);
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         ReviewDAO rDao = new ReviewDAO();
         int page = 1;
          if (request.getParameter("page") != null) {
            page = Integer.parseInt(
                    request.getParameter("page"));
        }
          int totalRecords = rDao.getTotalReviewsByRating();
         int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
         int starRating = 0;
         String starRatingParam = request.getParameter("star");
         if(starRatingParam == null)
              starRating = -1;
             
         else{
             starRating = Integer.parseInt(starRatingParam);
             request.getSession().setAttribute("starRating", starRating);
         }
         List<Review> reviewList = rDao.getAllReviews((page - 1) * recordsPerPage, recordsPerPage, starRating);
      // int  reviewByRate = rDao.getTotalReviewsByRating(0)
      request.setAttribute("currentPage", page);
       request.setAttribute("totalPages", totalPages);
         request.setAttribute("reviewList", reviewList);
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
