/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.ImageProduct;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

/**
 *
 * @author Admin
 */
public class ImageProductDAO extends DBContext{

    public ArrayList<ImageProduct> getAllImageByProductID(int productID) {
         ArrayList<ImageProduct> list = new ArrayList<>();

        try {
            String sql = "SELECT *\n"
                    + "  FROM [Image_Product]\n"
                    + "  Where [ProductID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, productID);
            ResultSet rs = stm.executeQuery();
            ProductDAO pDao = new ProductDAO(); 

            while (rs.next()) {
                int imageId = rs.getInt("ImageId");
                String image = rs.getString("image");
                list.add(new ImageProduct(imageId,rs.getInt("ProductID"),image));
            }
            if(list.size() == 0){
                list.add(new ImageProduct(0,productID,"https://png.pngtree.com/png-vector/20230726/ourlarge/pngtree-colouring-page-of-sneakers-png-image_6745849.png"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     public int getMaxProductImageID() {
        try {
            String sql = "SELECT Max(ProductId) as 'max'\n"
                    + "  FROM [Products] ";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("max");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
     public void insert(ImageProduct image) {
        try {
            String sql = "INSERT INTO [dbo].[Image_Product]\n"
                    + "           ([ProductID]\n"
                    + "           ,[image])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, image.getProductId());
            stm.setString(2, image.getImage());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ImageProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public void delete(int productID) {
        try {
            String sql = "DELETE FROM [dbo].[Image_Product]\n"
                    + "      WHERE ProductID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, productID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ImageProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        ImageProductDAO dao = new ImageProductDAO();
        System.out.println(dao.getAllImageByProductID(1).get(0).getImage());
    }
    }
    