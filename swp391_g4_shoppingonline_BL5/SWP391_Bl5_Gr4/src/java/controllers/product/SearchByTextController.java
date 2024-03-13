/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.product;

import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author admin
 */
public class SearchByTextController extends HttpServlet {

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
        request.getRequestDispatcher("views/Product/products.jsp").forward(request, response);
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
        String textSearch = request.getParameter("textSearch");
        ProductDAO pDao = new ProductDAO();
        List<Product> productsSearch = pDao.getAllProductsForSearch(textSearch, -1, -1, -1);
        request.getSession().setAttribute("textSearch", textSearch);
        request.setAttribute("productsSearch", productsSearch);

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
        int sortOption = Integer.parseInt(request.getParameter("sortOption"));
        String moreOptionParam = (String) request.getParameter("moreOption");
        int moreOption = 0;
        if (moreOptionParam == null || moreOptionParam.isEmpty()) {
            moreOption = -1;
        } else {
            moreOption = Integer.parseInt(moreOptionParam);
        }
        ProductDAO pDao = new ProductDAO();
        String textSearch = (String) request.getSession().getAttribute("textSearch");
        if (textSearch == null || textSearch.isEmpty()) {
            textSearch = "";
        }

        List<Product> productsSearchBySortOption = pDao.getAllProductsForSearch(textSearch, -1, sortOption, moreOption);
        request.getSession().setAttribute("productsSearch", productsSearchBySortOption);

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
