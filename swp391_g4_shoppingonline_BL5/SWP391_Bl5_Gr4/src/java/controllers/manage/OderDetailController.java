/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.manage;

import dao.OrderDAO;
import dao.OrderDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.OrderDetail;
import model.User;

/**
 *
 * @author dell
 */
@WebServlet(name = "OderDetail", urlPatterns = {"/OderDetail"})
public class OderDetailController extends HttpServlet {

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
            out.println("<title>Servlet OderDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OderDetail at " + request.getContextPath() + "</h1>");
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

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");
        if (user != null && user.getRole().getRoleId() == 5 || user.getRole().getRoleId() == 3) {
            OrderDetailDAO odDao = new OrderDetailDAO();
            OrderDAO oDao = new OrderDAO();

            // Get orderId from request parameters
            int orderId = Integer.parseInt(request.getParameter("orderId"));

            List<OrderDetail> orderDetails = odDao.getOrderDetailsByOrderId(orderId);

            double totalAmount = 0;
//            for (OrderDetail orderDetail : orderDetails) {
//                double productPrice = orderDetail.getProductDeteail().getProduct().getPrice();
//                int quantity = orderDetail.getQuantity();
//                totalAmount += productPrice * quantity;
//            }

//// Add the totalAmount to the request scope
            request.setAttribute("totalAmount", totalAmount);
            request.setAttribute("orderDetails", orderDetails);
            request.getRequestDispatcher("/views/Manage/OrderDetail.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("msg", "Bạn cần đăng nhập để vào trang này!");
            response.sendRedirect("home");
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
