/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dao.CategoryDAO;
import dao.ProductDAO;
import dao.ReviewDAO;
import model.Category;
import model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.ProductDetail;
import model.Review;
import model.User;

/**
 *
 * @author admin
 */
public class HomeController extends HttpServlet {

    private final int recordsPerPage = 1;

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
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(
                    request.getParameter("page"));
        }

        ProductDAO pdao = new ProductDAO();
        int totalRecords = pdao.getAllProductsByPage(-1, -1).size();
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
        CategoryDAO cDao = new CategoryDAO();
        ReviewDAO rDao = new ReviewDAO();
        //List<Product> bestseller = pdao.get5ProductNewArrival();
        List<Product> newarrival = pdao.get5ProductNewArrival();
        List<Product> listAllProducts = pdao.getAllProductsByPage((page - 1) * recordsPerPage, recordsPerPage);
        int totalProduct = pdao.getTotalProductsViewByCustomer();
        float reviewAvg = rDao.getAvgRating();
        int totalReview = rDao.getTotalReviewsByRating();
        ArrayList<Category> listCategory = cDao.getAllCategory();
        request.setAttribute("totalProduct", totalProduct);
        request.getSession().setAttribute("reviewAvg", reviewAvg);
        request.setAttribute("totalReview", totalReview);
        //request.setAttribute("bestSellers", bestseller);
        request.setAttribute("newArrivals", newarrival);
        request.setAttribute("listAllProducts", listAllProducts);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.getSession().setAttribute("listCategory", listCategory);
        User user = (User) request.getSession().getAttribute("account");
        if (user != null) {
            List<Review> favorList = rDao.getAllReviewsByFavor(user.getUserId());
            request.getSession().setAttribute("favorList", favorList);
        }
        request.getRequestDispatcher("views/HomePage.jsp").forward(request, response);
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
