/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Category;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

/**
 *
 * @author Admin
 */
public class CategoryDAO extends DBContext {

    public ArrayList<Category> getAllCategory() {
        ArrayList<Category> categories = new ArrayList<Category>();
        try {
            String sql = "SELECT *\n"
                    + "  FROM [Category]";
            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Category category = new Category(rs.getInt("CategoryId"),
                        rs.getString("CategoryName"),
                        rs.getBoolean("Status"),
                        rs.getString("Description"));
                categories.add(category);
            }
        } catch (Exception e) {
            System.out.println("get category by id");
        }
        return categories;
    }

    public Category getCategoryByID(int CategoryID) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [Category] where CategoryID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, CategoryID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new Category(rs.getInt("CategoryId"),
                        rs.getString("CategoryName"),
                        rs.getBoolean("Status"),
                        rs.getString("Description"));
            }
        } catch (Exception e) {
            System.out.println("get category by id");
        }
        return null;
    }

    public void update(Category category) {
        try {
            String sql = "UPDATE [dbo].[Category]\n"
                    + "   SET [CategoryName] =?\n"
                    + "      ,[Status] = ?\n"
                    + "      ,[Description] = ?\n"
                    + " WHERE CategoryId =?";
            PreparedStatement stm = connection.prepareStatement(sql);
           stm.setString(1, category.getCategoryName());
            stm.setBoolean(2, category.isStatus());
            stm.setString(3, category.getDescription());
            stm.setInt(4, category.getCategoryId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void add(Category category) {
        try {
            String sql = "INSERT INTO [dbo].[Category]\n"
                    + "           ([CategoryName]\n"
                    + "           ,[Status]\n"
                    + "           ,[Description])\n"
                    + "     VALUES\n"
                    + "          (?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, category.getCategoryName());
            stm.setBoolean(2, category.isStatus());
            stm.setString(3, category.getDescription());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        CategoryDAO category = new CategoryDAO();
        Category ca = new Category(3,"test", true, "test");
        category.update(ca);
    }
}
