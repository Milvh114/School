/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CartDAO;
import dao.CartDetailDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import model.Cart;
import model.CartDetail;
import model.Order;
import model.OrderDetail;
import model.User;

/**
 *
 * @author User
 */
public class CheckoutController extends HttpServlet {

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

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("account");
            Cart cart = (Cart) session.getAttribute("carts");
            if (user != null && user.getRole().getRoleId() == 2) {
                double Total = 0;
                for (CartDetail cd : new CartDetailDAO().getAllCartDetailbyCartId(cart)) {
                    Total += cd.getQuantity() * cd.getProductdetail().getProduct().getPrice();

                }
                new CartDAO().updateCartForCus(cart.getCartId(), Total, user.getUserId());
                request.setAttribute("Total", Total);
                request.getRequestDispatcher("/views/Checkout.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "Bạn cần đăng nhập thanh toán");
                request.getRequestDispatcher("login").forward(request, response);
            }
            /* TODO output your page here. You may use following sample code. */

//            Map<Integer, AddToCart> carts = (Map<Integer, AddToCart>) session.getAttribute("carts");
//            if (carts == null) {
//                carts = new LinkedHashMap<>();
//            }
//
//            double Total = 0;
//            for (Map.Entry<Integer, AddToCart> entry : carts.entrySet()) {
//                Integer productId = entry.getKey();
//                AddToCart cart = entry.getValue();
//
//                Total += cart.getQuantity() * cart.getProductdetail().getProduct().getPrice();
//            }
//            User account = (User) session.getAttribute("account");
//            if (account == null) {
//                request.getSession().setAttribute("notLogin", "True");
//                response.sendRedirect("home");
//            } else {
//                request.setAttribute("account", account);
//                request.setAttribute("listcarts", carts);
//                request.setAttribute("Total", Total);
//                request.getRequestDispatcher("/views/Checkout.jsp").forward(request, response);
//            }
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
        request.setCharacterEncoding("UTF-8");

//      request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");
        Cart cart = (Cart) session.getAttribute("carts");
        Order o = new Order();
        o.setCreateDate(Date.valueOf(LocalDate.now()));
        o.setTotalMoney(cart.getTotalOrder());
        o.setUser(user);
        o.setStatus(0);
        OrderDAO oDao = new OrderDAO();
        OrderDetailDAO odDao = new OrderDetailDAO();
        int id = oDao.createByOrderId(o);
//        for(CartDetail c : new CartDetailDAO().getAllCartDetailbyCartId(cart)){
//               OrderDetail od  = new OrderDetailDAO().createOrderDetail(id, c, 2);
//               System.out.println(od);
//               o.getOrderDetails().add(od);
//        }
        System.out.println(o);
        session.removeAttribute("carts");
        response.sendRedirect("home");
//
//       
//        
//        
//        
//        ProductDAO cd = new ProductDAO();
//          
//         for (Map.Entry<Integer, AddToCart> entry : carts.entrySet()) {
//            Integer productId = entry.getKey();
//            AddToCart cart = entry.getValue();
//            int to = cd.getTotalProductID(cart.getProductdetail().getProduct().getProductId());
//            cd.updateQuantity(cart.getProductdetail().getProduct().getProductId(),(to - cart.getQuantity()));
//        }
//        session.removeAttribute("carts");
//        response.sendRedirect("carts");
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
