/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.product;

import dao.ImageProductDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import model.Category;
import model.ImageProduct;
import model.Product;
//import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author User
 */
public class AddProductController extends HttpServlet {

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
        ProductDAO pDao = new ProductDAO();
        ImageProductDAO imgDao = new ImageProductDAO();

        //lay thong tin tu client gui ve
        String name = request.getParameter("name");
        Double price = Double.parseDouble(request.getParameter("price"));
        int categoryID = Integer.parseInt(request.getParameter("category"));
        boolean status = Boolean.valueOf(request.getParameter("status"));
        String description = request.getParameter("description");

        Category category = new Category();
        category.setCategoryId(categoryID);

        //tao san pham 
        int maxID = pDao.getMaxProductID() + 1;

//        Product p = new Product(maxID, name, 12.4, 1, true, Date.valueOf(LocalDate.now()), category, "1");
        Product product = new Product(maxID, name, price, status, Date.valueOf(LocalDate.now()), category, description);
//            Product product = new Product();
//            product.setProductName(name);
//            product.setPrice(price);
//            product.setQuantity(quantity);
//            product.setCategory(category);
//            product.setStatus(status);
//            product.setDescription(description);
//            product.setCreateDate(Date.valueOf(LocalDate.now()));
//
//            //tao id cho product
//            product.setProductId(maxID + 1);

        //them product vao database
        pDao.insert(product);

        //lay file anh client gui len server
//        List<Part> fileParts = request.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList());
//
//        //lay ra duong dan luu folder anh
//        String realPath = getServletContext().getRealPath("") + File.separator + "img";
//
//        for (Part part : fileParts) {
//            //random ten cho image
//            UUID uuid = UUID.randomUUID();
//            String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
//            //String fileExtension = FilenameUtils.getExtension(filename);
//
//            if (!Files.exists(Paths.get(realPath))) {
//                Files.createDirectory(Paths.get(realPath));
//            }
//            //filename = uuid + "." + fileExtension;
//            part.write(realPath + File.separator + filename);
//
//            String pathImage = "/" + "img" + "/" + filename;
//
//            //them anh vao database
//            //tao id cho product
////            int maxImageID = imgDao.getMaxProductImageID() + 1;
////            ImageProduct image = new ImageProduct(product.getProductId(), pathImage);
////            imgDao.insert(image);
//        }
        int maxImageID = imgDao.getMaxProductImageID() + 1;
        ImageProduct image = new ImageProduct(maxID, "https://giaygiare.vn/upload/sanpham/asics-court-trail-cream-cloud-pink.jpg");
        imgDao.insert(image);
        response.sendRedirect("/SWP391_Bl5_Gr4/productmanage");

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
//        Product product = new Product();
//        
//        try {
//            System.out.println(product.getPrice());
//        } catch (Exception e) {
//            System.out.println("oke");
//        }
//    }
}
