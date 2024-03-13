/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Size;

/**
 *
 * @author Admin
 */
public class SizeDAO extends DBContext{
    public ArrayList<Size> getAllSize(){
        ArrayList<Size> list = new ArrayList<>();
        try {
            String sql = "SELECT *\n"
                    + "  FROM [Size]\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Size size = new Size(rs.getInt("SizeId"),
                        rs.getInt("Value"));
                list.add(size);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
    }
     public Size getSizeByID(int sizeid) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [Size] where SizeId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sizeid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new Size(rs.getInt("SizeId"),
                        rs.getInt("Value") );
            }
        } catch (Exception e) {
            System.out.println("get category by id");
        }
        return null;
    }
    public static void main(String[] args) {
        SizeDAO s = new SizeDAO();
        System.out.println(s.getAllSizeByProductId(1));
    }

    public List<Size> getAllSizeByProductId(int productID) {
         ArrayList<Size> list = new ArrayList<>();
        try {
            String sql = "select distinct pd.Size ,s.Value\n" +
"  from ProductDetail pd   inner join Size s on pd.Size = s.SizeId where productId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, productID);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Size size = new Size(rs.getInt(1),
                        rs.getInt(2));
                list.add(size);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
