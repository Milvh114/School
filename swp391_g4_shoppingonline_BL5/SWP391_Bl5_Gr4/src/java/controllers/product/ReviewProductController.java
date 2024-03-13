/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.product;

import dao.ColorDAO;
import dao.ImageProductDAO;
import dao.ProductDAO;
import dao.ReviewDAO;
import dao.SizeDAO;
import dao.UserDAO;
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
import model.ImageProduct;
import model.Product;
import model.Review;
import model.Size;
import model.User;

/**
 *
 * @author User
 */
public class ReviewProductController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ReviewProductController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReviewProductController at " + request.getContextPath() + "</h1>");
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
     
        int productId = Integer.parseInt(request.getParameter("productId"));
        int rating = Integer.parseInt(request.getParameter("rating1"));
        String textSearch = request.getParameter("commentText");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");
        ProductDAO pr = new ProductDAO();
        UserDAO ud = new UserDAO();
        ReviewDAO rvd = new ReviewDAO();
       
        if (user != null && textSearch != "") {
            Review rv = new Review(ud.getUserByID(user.getUserId()), pr.getProductByID(productId), rating, true, textSearch);
            rvd.insert(rv);
            response.sendRedirect("home");
        }else if(user != null && textSearch == ""){
              request.getSession().setAttribute("msgc", "bạn cần nhập bình luận");
                int prId = (Integer) session.getAttribute("productID");
                 ProductDAO pDao = new ProductDAO();
            ImageProductDAO iDao = new ImageProductDAO();
            Product product = pDao.getProductByID(prId,true);
            SizeDAO sd = new SizeDAO();
            ColorDAO cd = new ColorDAO();
            ReviewDAO rv= new ReviewDAO();
            ArrayList<ImageProduct> images = iDao.getAllImageByProductID(prId);
            List<Size> listS = sd.getAllSize();
            List<Color> listC = cd.getAllColor();
            request.setAttribute("size", listS);
            request.setAttribute("color", listC);
            request.setAttribute("product", product);
            request.setAttribute("images", images);
            List<Review> lr = rv.getAllReviewsByProductId(prId);
            request.setAttribute("listrv", lr);
            request.getRequestDispatcher("views/Product/ProductDetails.jsp").forward(request, response);
              
        }
        else{
            request.getSession().setAttribute("msg", "Bạn cần đăng nhập để vào trang này!");
            response.sendRedirect("login");
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
