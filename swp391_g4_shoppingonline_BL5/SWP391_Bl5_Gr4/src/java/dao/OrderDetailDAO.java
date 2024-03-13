/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.OrderDetail;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cart;
import model.CartDetail;
import model.ImageProduct;
import model.Order;
import model.Product;
import model.ProductDetail;

/**
 *
 * @author Admin
 */
public class OrderDetailDAO extends DBContext {

    public ArrayList<OrderDetail> getOrderDetailsByOrderID(int orderId, boolean status) {
        ArrayList<OrderDetail> list = new ArrayList<>();
        ProductDetailDAO pDao = new ProductDetailDAO();

        try {
            String sql = "select * From OrderDetail where [Order] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, orderId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new OrderDetail(
                        rs.getInt("OrderDetailsId"),
                        rs.getInt("Order"),
                        pDao.getProductDetailByID(rs.getInt("ProductDetail")),
                        rs.getInt("Quantity")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        ArrayList<OrderDetail> list = new ArrayList<>();
        ProductDetailDAO pDao = new ProductDetailDAO();

        try {
            String sql = "select * From OrderDetail where [Order] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, orderId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new OrderDetail(
                        rs.getInt("OrderDetailsId"),
                        rs.getInt("Order"),
                        pDao.getProductDetailByID(rs.getInt("ProductDetail")),
                        rs.getInt("Quantity")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static void main(String[] args) {
        float total = 0;
        List<OrderDetail> o = new OrderDetailDAO().getOrderDetailsByOrderId(2);
         for (OrderDetail orderDetail : o) {
                System.out.println(orderDetail.getProductDetail());
                int quantity = orderDetail.getQuantity();
            }
    }

    public void saveCart(Cart cart) {
//        try {
//            String sql = "INSERT INTO [dbo].[OrderDetail]\n"
//                    + "           ([Order]\n"
//                    + "           ,[Product]\n"
//                    + "           ,[Quantity])\n"
//                    + "     VALUES\n"
//                    + "           (?\n"
//                    + "           ,?\n"
//                    + "           ,?)";
//            PreparedStatement stm = connection.prepareStatement(sql);
//            stm.setInt(1, orderId);
//          
//            for (Map.Entry<Integer, AddToCart> entry : carts.entrySet()) {
//                Integer productId = entry.getKey();
//                AddToCart cart = entry.getValue();
//                stm.setInt(2, cart.getProductdetail().getProductDetailId());
//                stm.setInt(3, cart.getQuantity());
//                 ResultSet rs = stm.executeQuery();
//            }
//        } catch (Exception ex) {
//             Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    public List<CartDetail> getAllCartDetailbyCartId(Cart cart) {
        List<CartDetail> list = new ArrayList<>();
        try {
            String sql = "Select * from CartDetail where Cart = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cart.getCartId());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ProductDetail pd = new ProductDetailDAO().getProductDetailByID(rs.getInt(3));
                list.add(new CartDetail(rs.getInt(1), cart, pd, rs.getInt(4)));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public OrderDetail createOrderDetail(int orderId,CartDetail cd, int payment){
    
        try {
          
        
            String sql = "INSERT INTO [dbo].[OrderDetail]   ([Order]   ,[ProductDetail]      ,[Quantity]     ,[Payment]      )  VALUES        (?     ,?,?  ,?  )";
            PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1,orderId);
            stm.setInt(2,cd.getProductdetail().getProductDetailId());
            stm.setInt(3,cd.getQuantity());
            stm.setInt(4,payment);
            // Execute the insert statement
            int affectedRows = stm.executeUpdate();

            // Check if the insertion was successful
            if (affectedRows > 0) {
                // Retrieve the generated keys
                ResultSet rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    return new OrderDetail(rs.getInt(1), orderId,cd.getProductdetail() , payment);
                } else {
                    throw new SQLException("Failed to retrieve generated CartDetail ID.");
                }
            } else {
                throw new SQLException("Failed to create a new CartDetail.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
}
