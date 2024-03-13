/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.product;

import dao.ColorDAO;
import dao.ImageProductDAO;
import dao.ProductDAO;
import dao.ReviewDAO;
import dao.SizeDAO;
import model.ImageProduct;
import model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Color;
import model.Review;
import model.Size;
import model.User;

/**
 *
 * @author admin
 */
public class ProductController extends HttpServlet {

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
            out.println("<title>Servlet ProductDetailsController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductDetailsController at " + request.getContextPath() + "</h1>");
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
      if (request.getParameter("productID") != null  ) {
            int productID = Integer.parseInt(request.getParameter("productID"));
            ProductDAO pDao = new ProductDAO();
            ImageProductDAO iDao = new ImageProductDAO();
            Product product = pDao.getProductByID(productID,true);
            SizeDAO sd = new SizeDAO();
            ColorDAO cd = new ColorDAO();
            ReviewDAO rv= new ReviewDAO();
            ArrayList<ImageProduct> images = iDao.getAllImageByProductID(productID);
            List<Size> listS = sd.getAllSize();
            List<Color> listC = cd.getAllColor();
            request.setAttribute("size", listS);
            request.setAttribute("color", listC);
            request.setAttribute("product", product);
            request.setAttribute("images", images);
            List<Review> lr = rv.getAllReviewsByRate(productID);
            request.setAttribute("listrv", lr);
            
            User user = (User) request.getSession().getAttribute("account");
            if (user != null) {
                List<Review> favorList = rv.getAllReviewsByFavor(user.getUserId());
                request.getSession().setAttribute("favorList", favorList);
            }
            request.setAttribute("product", product);
            request.setAttribute("images", images);
            request.getRequestDispatcher("views/Product/ProductDetails.jsp").forward(request, response);
        } 
       
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
       if (request.getParameter("productID") != null  ) {
            int productID = Integer.parseInt(request.getParameter("productID"));
            ProductDAO pDao = new ProductDAO();
            ImageProductDAO iDao = new ImageProductDAO();
            Product product = pDao.getProductByID(productID,true);
            SizeDAO sd = new SizeDAO();
            ColorDAO cd = new ColorDAO();
            ReviewDAO rv= new ReviewDAO();
            ArrayList<ImageProduct> images = iDao.getAllImageByProductID(productID);
            List<Size> listS = sd.getAllSize();
            List<Color> listC = cd.getAllColor();
            request.setAttribute("size", listS);
            request.setAttribute("color", listC);
            request.setAttribute("product", product);
            request.setAttribute("images", images);
            List<Review> lr = rv.getAllReviewsByRate(productID);
            request.setAttribute("listrv", lr);
            request.getRequestDispatcher("views/Product/ProductDetails.jsp").forward(request, response);
        } 
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
