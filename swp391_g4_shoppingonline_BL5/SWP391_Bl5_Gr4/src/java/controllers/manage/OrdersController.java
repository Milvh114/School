
package controllers.manage;

import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductDAO;
import dao.UserDAO;
import model.Order;
import model.OrderDetail;
import model.User;
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

/**
 *
 * @author dell
 */
@WebServlet(name="OrdersController", urlPatterns={"/Orders"})
public class OrdersController extends HttpServlet {

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
        int saleAdminFilter=0;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");
        if (user != null && user.getRole().getRoleId() == 5) {
       
            OrderDetailDAO odDao = new OrderDetailDAO();
            OrderDAO oDao = new OrderDAO();
            UserDAO uDao = new UserDAO();            
            List<Order> orders = new ArrayList<>();           
            
            
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
                
            }
              if(request.getParameter("saleAdminFilter")!=null){
             saleAdminFilter = Integer.parseInt(request.getParameter("saleAdminFilter"));
             
            }
            
              
            int totalRecords = oDao.getNoOfOrderRecordsExceptPending();
            int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

            if(saleAdminFilter!=0)
            {
                orders = oDao.getAllOfSaleStaffOrder(saleAdminFilter,(page - 1) * recordsPerPage, recordsPerPage);
            }
            else
            {
              orders = oDao.getAllOrder((page - 1) * recordsPerPage, recordsPerPage);
            }                                        
            for (Order order : orders) {
                List<OrderDetail> orderDetails = odDao.getOrderDetailsByOrderId(order.getOrderId());
            }
             ArrayList<User> list = uDao.getAllUserByRoleID(3);     
            request.setAttribute("currentPage", page);  
             request.setAttribute("totalPages", totalPages);
            request.setAttribute("user", user);
            request.setAttribute("salesAdminList", list);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/views/Manage/OrderManagement.jsp").forward(request, response);
        }
        else if(user != null && user.getRole().getRoleId() == 3){
              OrderDetailDAO odDao = new OrderDetailDAO();
            OrderDAO oDao = new OrderDAO();
            UserDAO uDao = new UserDAO();            
            List<Order> orders = new ArrayList<>();           
            
            
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
                
            }
            int totalRecords = oDao.getCountOfSaleStaffOrder(user.getUserId());
            int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

            orders = oDao.getAllOfSaleStaffOrder(user.getUserId(),(page - 1) * recordsPerPage, recordsPerPage);
            for (Order order : orders) {
                List<OrderDetail> orderDetails = odDao.getOrderDetailsByOrderId(order.getOrderId());
            }
             ArrayList<User> list = uDao.getAllUserByRoleID(3);     
          request.setAttribute("currentPage", page);  
             request.setAttribute("totalPages", totalPages);
            request.setAttribute("user", user);
            request.setAttribute("salesAdminList", list);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/views/Manage/OrderManagement.jsp").forward(request, response);
        }
        
        else{
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
