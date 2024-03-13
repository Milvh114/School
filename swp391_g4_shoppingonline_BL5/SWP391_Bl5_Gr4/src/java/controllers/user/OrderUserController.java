/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.user;

import dao.CategoryDAO;
import dao.OrderDAO;
import dao.ProductDAO;
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
import model.Category;
import model.Order;
import model.Product;
import model.User;

/**
 *
 * @author User
 */
public class OrderUserController extends HttpServlet {

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
            out.println("<title>Servlet OrderUserController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderUserController at " + request.getContextPath() + "</h1>");
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
        UserDAO dao = new UserDAO();

        int page = 1;
        final int LAST_PAGE = 5;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");

        String pageString = request.getParameter("page");
        if (pageString != null) {
            page = Integer.parseInt(pageString);
        }
        if (user != null) {
            OrderDAO OD = new OrderDAO();
            List<Order> listO = new ArrayList<>();
                 listO =   OD.getOrderByUserId(user.getUserId(), page, LAST_PAGE);
            int totalProducts = OD.getTotalOrder(user.getUserId());
            int totalPage = totalProducts / LAST_PAGE;
            if (totalProducts % LAST_PAGE != 0) {
                totalPage += 1;
            }
            CategoryDAO cate = new CategoryDAO();

            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("orders", listO);
            request.getRequestDispatcher("views/Account/UserOrder.jsp").forward(request, response);

        } else {
            request.setAttribute("msg", "Bạn cần đăng nhập để vào trang này!");
            request.getRequestDispatcher("login").forward(request, response);
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
