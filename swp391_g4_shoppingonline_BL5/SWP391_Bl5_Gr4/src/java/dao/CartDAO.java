/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cart;
import model.Order;
import model.User;

/**
 *
 * @author Admin
 */
public class CartDAO extends DBContext {

    public void createByCartId(Cart cart) {
        try {
            String sql = "INSERT INTO [dbo].[Cart]\n"
                    + "           ([TotalOrder]\n"
                    + "           ,[User])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDouble(1, cart.getTotalOrder());
            System.out.println(cart.getTotalOrder());
            stm.setInt(2, cart.getUser().getUserId());
            System.out.println(cart.getUser().getUserId());
            stm.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Cart createNewCart() {
        Cart cart = null;
        try {
            String sql = "INSERT INTO [dbo].[Cart] ([TotalOrder], [User]) VALUES (0, null)";
            PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Execute the insert statement
            int affectedRows = stm.executeUpdate();

            // Check if the insertion was successful
            if (affectedRows > 0) {
                // Retrieve the generated keys
                ResultSet rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    cart = new Cart();
                    cart.setCartId(generatedId);
                    cart.setTotalOrder(0); // You may need to adjust this based on your actual data
                    cart.setUser(null);    // You may need to adjust this based on your actual data
                } else {
                    throw new SQLException("Failed to retrieve generated cart ID.");
                }
            } else {
                throw new SQLException("Failed to create a new cart.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cart;
    }

    public Cart getCartByID(int cartid) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [Cart] where CartID = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cartid);
            ResultSet rs = stm.executeQuery();
            CategoryDAO cDao = new CategoryDAO();
            ColorDAO coDao = new ColorDAO();
            ProductDAO pdao = new ProductDAO();
            SizeDAO sdao = new SizeDAO();
            ImageProductDAO imageDao = new ImageProductDAO();
            UserDAO ud = new UserDAO();
            Cart c = new Cart();
            if (rs.next()) {
                c.setCartId(rs.getInt("CartID"));
                c.setTotalOrder(rs.getDouble("TotalOrder"));
                c.setUser(ud.getUserByID(rs.getInt("User")));
            }
            return c;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getMaxCartID() {
        try {
            String sql = "SELECT Max(CartID) as 'max'\n"
                    + "  FROM [Cart] ";
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

    public static void main(String[] args) {
        CartDAO c = new CartDAO();
        c.updateCartForCus(65,200, 1);

    }

    public void updateCartForCus(int cartId, double total, int userId) {
        try {
            String sql = " UPDATE Cart  SET [User] = ? , TotalOrder = ?\n" +
"                    WHERE [CartID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setDouble(2, total);
            stm.setInt(3, cartId);

            stm.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
