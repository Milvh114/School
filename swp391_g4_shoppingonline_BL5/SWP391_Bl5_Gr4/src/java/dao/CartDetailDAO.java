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
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cart;
import model.CartDetail;
import model.ProductDetail;

/**
 *
 * @author Admin
 */
public class CartDetailDAO extends DBContext {

    public CartDetail createNewCartDetail(Cart cart, ProductDetail productDetail, int quantity) {
        CartDetail cartDetail = new CartDetail();
        try {
            String sql = "INSERT INTO [dbo].[CartDetail] ([Cart], [ProductDetail], [Quantity]) VALUES (?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Set the parameters for the SQL query
            stm.setInt(1, cart.getCartId());                 // Assuming Cart class has a getCartId() method
            stm.setInt(2, productDetail.getProductDetailId()); // Assuming ProductDetail class has a getProductDetailId() method
            stm.setInt(3, quantity);

            // Execute the insert statement
            int affectedRows = stm.executeUpdate();

            // Check if the insertion was successful
            if (affectedRows > 0) {
                // Retrieve the generated keys
                ResultSet rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    // Instantiate a new CartDetail object with the generated ID
                    cartDetail.setCartDetailId(rs.getInt(1));
                    cartDetail.setCart(cart);
                    cartDetail.setProductdetail(productDetail);
                    cartDetail.setQuantity(quantity);
                } else {
                    throw new SQLException("Failed to retrieve generated CartDetail ID.");
                }
            } else {
                throw new SQLException("Failed to create a new CartDetail.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
            return cartDetail;
        }
        return cartDetail;
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

    

    public void deleteCartDetailById(int cdId) {
        try {
            String sql = "Delete from CartDetail where CartDetailsId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);

            // Set the parameters for the SQL query
            stm.setInt(1, cdId);                 // Assuming Cart class has a getCartId() method
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        CartDetailDAO c = new CartDetailDAO();
        Cart cart = new CartDAO().getCartByID(63);
        c.deleteCartDetailById(19);
    }

}
