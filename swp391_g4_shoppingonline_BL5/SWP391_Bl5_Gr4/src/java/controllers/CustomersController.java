/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CustomerDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import model.Customer;
import model.User;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import model.Order;
import model.OrderDetail;

/**
 *
 * @author User
 */
public class CustomersController extends HttpServlet {

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

        if (user != null && user.getRole().getRoleId() == 3) {
            OrderDAO oDao = new OrderDAO();
            OrderDetailDAO odDAO = new OrderDetailDAO();
            CustomerDAO c = new CustomerDAO();
            List<Customer> list = c.getAllCustomer();
            for (Customer customer : list) {
                customer.setOrders(oDao.getOrderByUserIdAndSaleUserId(customer.getUserID(), user.getUserId()));
            }

            request.setAttribute("listCustomers", list);
            request.getRequestDispatcher("views/Customers.jsp").forward(request, response);
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
//         int userId = Integer.parseInt(request.getParameter("userId"));
//        boolean isChecked = Boolean.parseBoolean(request.getParameter("isChecked"));
//
//        if (isChecked) {
//              CustomerDAO cu = new CustomerDAO();
//              cu.updateStatusCustomerFalse(userId);
//          
//            response.setStatus(HttpServletResponse.SC_OK);
//        } else {
//           
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//        }

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

//    public static void main(String[] args) {
//        OrderDAO oDao = new OrderDAO();
//        OrderDetailDAO odDAO = new OrderDetailDAO();
//        CustomerDAO c = new CustomerDAO();
//        List<Customer> list = c.getAllCustomer();  
//        Map<Integer, List<Order>> orders = new HashMap<>();
//        for (int i = 0; i < list.size(); i++) {
//            orders.put(list.get(i).getUserID(),oDao.getOrderByUserIdAndSaleUserId(list.get(i).getUserID(),2) );
//        }
//        System.out.println(orders.get(1).size());
//    }
}
