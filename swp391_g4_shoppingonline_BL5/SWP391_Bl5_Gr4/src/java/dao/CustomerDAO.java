/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Customer;
import model.User;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class CustomerDAO extends DBContext {

    private Connection conn = null;//CONNECT VOI SQL 
    private PreparedStatement ps = null;//CHUYEN CAU LENH SANG SQL
    private ResultSet rs = null; //nhan ket qua tra ve

    public List<Customer> getAllCustomer() {
        List<Customer> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM [User] where Role = 2";
            PreparedStatement stm = connection.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer(rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Phone"),
                        rs.getDate("DOB"),
                        rs.getString("Address"),
                        rs.getString("Avatar"),
                        rs.getBoolean("Status"),
                        rs.getString("Description"));
                list.add(customer);
            }
        } catch (Exception ex) {
        }
        return list;
    }

    public Customer getCustomerById(int userId) {
        Customer c = new Customer();
        try {
            String sql = "SELECT * FROM [Users] where UserID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new Customer(rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Phone"),
                        rs.getDate("DOB"),
                        rs.getString("Address"),
                        rs.getString("Avatar"),
                        rs.getBoolean("Status"),
                        rs.getString("Description"));
            }
        } catch (Exception ex) {
        }
        return null;
    }

    public void updateStatusCustomerFalse(int customerId) {

        try {
            String sql = "UPDATE [dbo].[User]\n"
                    + "   SET [Status] = 1\n"
                    + "    \n"
                    + " WHERE UserID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, customerId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public void updateStatusCustomerTrue(int customerId) {

        try {
            String sql = "UPDATE [dbo].[User]\n"
                    + "   SET [Status] = 0\n"
                    + "    \n"
                    + " WHERE UserID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, customerId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        CustomerDAO c = new CustomerDAO();
        List<Customer> list = c.getAllCustomer();
//        System.out.println(c.getCustomerById(2));
//        for (Customer o : list) {
//            System.out.println(o);
//        }
//        c.updateStatusCustomerFalse(6);

    }
}
