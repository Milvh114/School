/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.manage;

import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.OrderDetail;
import model.User;

/**
 *
 * @author dell
 */
@WebServlet(name = "OrderRequestController", urlPatterns = {"/OrderRequest"})
public class OrderRequestController extends HttpServlet {

    final int recordsPerPage = 10;

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
            out.println("<title>Servlet ListOrderController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListOrderController at " + request.getContextPath() + "</h1>");
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
        int page = 1;
        String sortOrder="asc";
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");
        if (user != null && user.getRole().getRoleId() == 5) {
            
            
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
                
            }
            if(request.getParameter("sortOrder")!=null)
            {
                      sortOrder = request.getParameter("sortOrder");
            }
      
            
            OrderDetailDAO odDao = new OrderDetailDAO();
            OrderDAO oDao = new OrderDAO();
            List<Order> orders = new ArrayList<>();
            
            int totalRecords = oDao.getNoOfOrderRecordsExceptPending();
            int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage); 
            
            orders = oDao.getAllOrderNotAccept((page - 1) * recordsPerPage, recordsPerPage,sortOrder);
            for (Order order : orders) {
                List<OrderDetail> orderDetails = odDao.getOrderDetailsByOrderId(order.getOrderId());
            }
            UserDAO dao = new UserDAO();
             request.setAttribute("currentPage", page);  
             request.setAttribute("totalPages", totalPages);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/views/Manage/ListOrderResquest.jsp").forward(request, response);
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
        OrderDAO oDao = new OrderDAO();
        OrderDetailDAO odDao = new OrderDetailDAO();
        ProductDAO pDao = new ProductDAO();

        User acc = (User) request.getSession().getAttribute("account");

        String action = request.getParameter("action");
        int OrderId = Integer.parseInt(request.getParameter("id"));
        switch (action) {

            case "fail":
                //get all order detail by orderId
                List<OrderDetail> odList = odDao.getOrderDetailsByOrderId(OrderId);
                for (OrderDetail orderDetails : odList) {
//
                }

                doGet(request, response);
                break;
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
