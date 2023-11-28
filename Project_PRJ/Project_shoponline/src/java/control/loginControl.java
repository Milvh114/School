/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.Dao;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author milvh
 */
public class loginControl extends HttpServlet {

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
//      processRequest(request, response);
        // cookie là một cái mảng chứa nhiều cookie 1 giá trị trong mảng chứa 1 cookie
        //b1: lấy data từ cookie
        Cookie arr[] = request.getCookies();
        for(Cookie c : arr){
            if(c.getName().equals("userC")){
                request.setAttribute("userC", c.getValue());
            }
            if(c.getName().equals("passC")){
                request.setAttribute("passC", c.getValue());
            }
        }

        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        String user = request.getParameter("txtUser");
        String pass = request.getParameter("txtPass");
        String remember = request.getParameter("remember");
        String mess = null;
        Dao dao = new Dao();
        Account acc = dao.getAccount(user, pass);
        if(acc==null){
            mess="wrong username or pass";
            request.setAttribute("mess", mess);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }else{
            HttpSession session = request.getSession();
            session.setAttribute("accLogin", acc);
            
            if(remember!=null){
                //luu acc len tren cookie
                Cookie u =  new Cookie("userC",user);
                Cookie p =  new Cookie("passC",pass);
                u.setMaxAge(60*60);
                p.setMaxAge(60*60);
                response.addCookie(u);//luu u va p len brown
                response.addCookie(p);
            }

            //ở đây mình không cần load data lên jsp nên không cần dùng forward
            // ý nghĩa của foward là forward dữ liệu lên,forward() chuyển hướng trực tiếp trên máy chủ mà không thông qua trình duyệt, trong khi phương pháp 
//            request.getRequestDispatcher("home").forward(request, response);
            response.sendRedirect("home");//sendRedirect huyển hướng thông qua trình duyệt bằng cách gửi lại yêu cầu mới đến một địa chỉ khác.
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
