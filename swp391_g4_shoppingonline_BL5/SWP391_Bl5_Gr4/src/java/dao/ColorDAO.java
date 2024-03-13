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
import model.Color;

/**
 *
 * @author Admin
 */
public class ColorDAO extends DBContext {

    public ArrayList<Color> getAllColor() {
        ArrayList<Color> list = new ArrayList<>();
        try {
            String sql = "SELECT *\n"
                    + "  FROM [Color]\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Color color = new Color(rs.getInt("ColorId"),
                        rs.getString("Value"));
                list.add(color);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public Color getColerByID(int sizeid) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [Color] where ColorId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sizeid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new Color(rs.getInt("ColorId"),
                        rs.getString("Value"));
            }
        } catch (Exception e) {
            System.out.println("get category by id");
        }
        return null;
    }

    public List<Color> getAllColorByProductId(int productID) {
        ArrayList<Color> list = new ArrayList<>();
        try {
            String sql = "  select distinct pd.Color ,c.Value\n" +
"  from ProductDetail pd   inner join Color c on pd.Color = c.ColorId where productId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1,productID);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Color color = new Color(rs.getInt(1),
                        rs.getString(2));
                list.add(color);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
