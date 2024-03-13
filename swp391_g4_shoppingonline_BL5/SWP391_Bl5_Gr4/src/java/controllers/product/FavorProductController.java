/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.product;

import dao.ProductDAO;
import dao.ReviewDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Product;
import model.Review;
import model.User;

/**
 *
 * @author admin
 */
public class FavorProductController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FavorProductController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FavorProductController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        ReviewDAO rDao = new ReviewDAO();
        ProductDAO pDao = new ProductDAO();
        int productId = Integer.parseInt(request.getParameter("productID"));

        if (request.getSession().getAttribute("account") == null) {
            response.sendRedirect("login");
        } else {
            User user = (User) request.getSession().getAttribute("account");
            List<Review> favorList = (List<Review>) request.getSession().getAttribute("favorList");

            Product product = pDao.getProductByID(productId, true);
            Review review = new Review(user, product);
            Review reviewUpdate = rDao.getReviewByProductAndUser(user.getUserId(), productId);
               List<Review> reviewL = rDao.getAllReviewsByFavorAndProductId(user.getUserId(),productId);
            if (isReviewExists(reviewL, user.getUserId(), product.getProductId())) {
                rDao.updateFavorProduct(1, reviewUpdate.getReviewId());
            } else {
                rDao.insertFavorProduct(review);
            }
            favorList = rDao.getAllReviewsByFavor(user.getUserId());
            request.getSession().setAttribute("favorList", favorList);
            response.sendRedirect("/SWP391_Bl5_Gr4/productDetail?productID=" + productId);
        }
    }

    public boolean isReviewExists(List<Review> listReview, int userId, int productId) {
        for (Review existingReview : listReview) {
            // Kiểm tra xem đánh giá đã tồn tại cho sản phẩm và người dùng cụ thể chưa
            if (existingReview.getUser().getUserId() == userId && existingReview.getProduct().getProductId() == productId) {
                return true;
            }
        }
        return false;
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
