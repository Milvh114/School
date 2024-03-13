/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CartDAO;
import dao.CartDetailDAO;
import dao.ColorDAO;
import dao.ProductDAO;
import dao.ProductDetailDAO;
import dao.SizeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;
import model.Cart;
import model.CartDetail;
import model.Color;
import model.Product;
import model.ProductDetail;
import model.Size;
import model.User;

/**
 *
 * @author User
 */
public class OrderController extends HttpServlet {

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
//            int productId = Integer.parseInt(request.getParameter("productId"));
//            int ColorId = Integer.parseInt(request.getParameter("colorid"));
//            int sizeId = Integer.parseInt(request.getParameter("sizeid"));
//
//            HttpSession session = request.getSession();
//            Map<Integer, AddToCart> carts = (Map<Integer, AddToCart>) session.getAttribute("carts");
//            if (carts == null) {
//                carts = new LinkedHashMap<>();
//            }
//
//            if (carts.containsKey(productId)) {
//                int oldQuantity = carts.get(productId).getQuantity();
//                carts.get(productId).setQuantity(oldQuantity + 1);
//            } else {
//                ProductDetail productd = new ProductDetailDAO().getProductDetailBySizeIdAndColorId(productId,sizeId, ColorId);
//               
//                AddToCart cart = new AddToCart(productd, 1);
//                carts.put(productId, cart);
//            }
//            session.setAttribute("carts", carts);
//            System.out.println(carts);
//            response.sendRedirect("/SWP391_Bl5_Gr4/carts");
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

//            
//            Map<Integer, CartDetail> carts = (Map<Integer, CartDetail>) session.getAttribute("carts");
//            if (carts == null) {
//                carts = new LinkedHashMap<>();
//            }
//
//            if (carts.containsKey(productId)) {
//                int oldQuantity = carts.get(productId).getQuantity();
//                carts.get(productId).setQuantity(oldQuantity + 1);
//            } else {
//                ProductDetail productd = new ProductDetailDAO().getProductDetailBySizeIdAndColorId(productId,sizeId, ColorId);
//                AddToCart cart = new AddToCart(productd, 1);
//                carts.put(productId, cart);
//            }
//             User account = (User) session.getAttribute("account");
//            session.setAttribute("carts", carts);
//            response.sendRedirect("/SWP391_Bl5_Gr4/home");
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
        int ColorId = Integer.parseInt(request.getParameter("colorid"));
        int sizeId = Integer.parseInt(request.getParameter("sizeid"));

        ProductDetailDAO pDetailDao = new ProductDetailDAO();
        CartDAO cDao = new CartDAO();
        CartDetailDAO cdetailDao = new CartDetailDAO();
        ProductDetail pDetailtail = pDetailDao.getProductDetailBySizeIdAndColorId(productId, sizeId, ColorId);
        HttpSession session = request.getSession();
        Cart cart = new Cart();
        cart = (Cart) session.getAttribute("carts");
        System.out.println(cart);
        if (cart == null) {
            cart = cDao.createNewCart();
            session.setAttribute("carts", cart);
        }
        if (pDetailtail != null) {

            cdetailDao.createNewCartDetail(cart, pDetailtail, 1);
        }

        response.sendRedirect("home");
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
