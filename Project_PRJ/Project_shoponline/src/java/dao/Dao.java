/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Account;
import entity.Category;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * @author milvh
 */
public class Dao extends DBContext{
//    Connection con = null;//connection to sql server
    PreparedStatement stm = null; //ném câu lệnh query tới sql server
    ResultSet rs = null;// nhận kết quả trả về
    
    //ACCOUNT
    public Account getAccount(String user, String pass){
        String sql = "SELECT * FROM Account WHERE username=? AND password=?";
        try {
            stm = con.prepareStatement(sql);
            stm.setString(1, user);
            stm.setString(2, pass);
            rs = stm.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
 
    
    public boolean checkAccount(String user){
        String sql = "SELECT * FROM Account WHERE username=?";
        try {
            stm = con.prepareStatement(sql);
            stm.setString(1, user);
            rs = stm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void createNewAccount(String user, String pass){
        String sql = "INSERT INTO Account (username, password, isAdmin, isSell) VALUES (?, ?, 0, 0);";
        try {
            stm = con.prepareStatement(sql);
            stm.setString(1, user);
            stm.setString(2, pass);
//            rs = stm.executeQuery();//do câu lệnh sql không trả về gì cả nên không cần câu lệnh rs
            stm.executeUpdate();//phải dùng câu lệnh này để thực thi câu lênh sql trên
            
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }
    
    
    //PRODUCT
    
    public ArrayList<Product> getAllProduct(){
        ArrayList<Product> list = new ArrayList<Product>();
        String sql = "SELECT * FROM Product";
        
        try {
//            con = new DBContext().makeConnection();// mở kết nối đến sql server
            stm = con.prepareStatement(sql); //ném câu lệnh query sang bên sql
            rs = stm.executeQuery();//chạy câu lệnh query => nó sẽ trả về kết quả bảng 
            //chúng ta lấy data trả về add vào list
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(6), rs.getFloat(4), rs.getInt(5), rs.getInt(7)));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Category> getAllCategory(){
        ArrayList<Category> list = new ArrayList<Category>();
        String sql = "SELECT * FROM Categories";
        
        try {
//            con = new DBContext().makeConnection();// mở kết nối đến sql server
            stm = con.prepareStatement(sql); //ném câu lệnh query sang bên sql
            rs = stm.executeQuery();//chạy câu lệnh query => nó sẽ trả về kết quả bảng 
            //chúng ta lấy data trả về add vào list
            while (rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString(2)));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Product getTrendProduct(){
        String sql = "SELECT top 1 * FROM Product ORDER BY quantity DESC";
        
        try {
//            con = new DBContext().makeConnection();// mở kết nối đến sql server
            stm = con.prepareStatement(sql); //ném câu lệnh query sang bên sql
            rs = stm.executeQuery();//chạy câu lệnh query => nó sẽ trả về kết quả bảng 
            while (rs.next()) {
                Product trend = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(6), rs.getFloat(4), rs.getInt(5), rs.getInt(7));
                return trend;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Product> getProductByCategory(int cid){
        ArrayList<Product> list = new ArrayList<Product>();
        String sql = "SELECT * FROM Product WHERE cID=?";
        
        try {
//            con = new DBContext().makeConnection();// mở kết nối đến sql server
            stm = con.prepareStatement(sql); //ném câu lệnh query sang bên sql
            stm.setInt(1, cid);
            rs = stm.executeQuery();//chạy câu lệnh query => nó sẽ trả về kết quả bảng 
            //chúng ta lấy data trả về add vào list
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(6), rs.getFloat(4), rs.getInt(5), rs.getInt(7)));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Product getProductById(int pid){
        String sql = "SELECT * FROM Product WHERE pid=?";
        
        try {
//            con = new DBContext().makeConnection();// mở kết nối đến sql server
            stm = con.prepareStatement(sql); //ném câu lệnh query sang bên sql
            stm.setInt(1, pid);
            rs = stm.executeQuery();//chạy câu lệnh query => nó sẽ trả về kết quả bảng 
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(6), rs.getFloat(4), rs.getInt(5), rs.getInt(7));
                return p;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Product> getProductMayLike(){
        ArrayList<Product> list = new ArrayList<Product>();
        String sql = "SELECT top 3 * FROM Product ORDER BY price";
        
        try {
//            con = new DBContext().makeConnection();// mở kết nối đến sql server
            stm = con.prepareStatement(sql); //ném câu lệnh query sang bên sql
            rs = stm.executeQuery();//chạy câu lệnh query => nó sẽ trả về kết quả bảng 
            //chúng ta lấy data trả về add vào list
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(6), rs.getFloat(4), rs.getInt(5), rs.getInt(7)));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Product> searchByName(String name){
        ArrayList<Product> list = new ArrayList<Product>();
        String sql = "SELECT * FROM Product WHERE pname LIKE ?";
        
        try {
//            con = new DBContext().makeConnection();// mở kết nối đến sql server
            stm = con.prepareStatement(sql); //ném câu lệnh query sang bên sql
            stm.setString(1,"%" + name + "%");
            rs = stm.executeQuery();//chạy câu lệnh query => nó sẽ trả về kết quả bảng 
            //chúng ta lấy data trả về add vào list
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(6), rs.getFloat(4), rs.getInt(5), rs.getInt(7)));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void deleteProduct(int pid){
        String sql = "DELETE  FROM Product WHERE pid = ?;";
        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, pid);
//            rs = stm.executeQuery();//do câu lệnh sql không trả về gì cả nên không cần câu lệnh rs
            stm.executeUpdate();//phải dùng câu lệnh này để thực thi câu lênh sql trên
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }
    
    public void addProduct(String pname, String description, float price, int quantity, String image, int cID){
        String sql = "INSERT INTO Product (pname, description, price, quantity, image, cID) VALUES (?, ?, ?, ?, ?, ?);";
        try {
            stm = con.prepareStatement(sql);
            stm.setString(1, pname);
            stm.setString(2, description);
            stm.setFloat(3, price);
            stm.setInt(4, quantity);
            stm.setString(5, image);
            stm.setInt(6, cID);
//            rs = stm.executeQuery();//do câu lệnh sql không trả về gì cả nên không cần câu lệnh rs
            stm.executeUpdate();//phải dùng câu lệnh này để thực thi câu lênh sql trên
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }
    
    public void updateProduct(Product p){
        String sql = "UPDATE Product SET pname=?, description=?, price=?, quantity=?, image=?, cID=? WHERE pid = ?";
        try {
            stm = con.prepareStatement(sql);
            stm.setString(1, p.getName());
            stm.setString(2, p.getDescription());
            stm.setFloat(3, p.getPrice());
            stm.setInt(4, p.getQuantity());
            stm.setString(5, p.getImage());
            stm.setInt(6, p.getCid());
            stm.setInt(7, p.getPid());
//            rs = stm.executeQuery();//do câu lệnh sql không trả về gì cả nên không cần câu lệnh rs
            stm.executeUpdate();//phải dùng câu lệnh này để thực thi câu lênh sql trên
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    public int checkProductInCart(int pid, int uid){
        String sql = "SELECT * FROM Cart WHERE pid=? and uid=?;";
        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, pid);
            stm.setInt(2, uid);
            rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public void addProductToCart(int pid, int uid, int amount){
        String sql = "INSERT INTO Cart (uid, pid, amount) VALUES (?, ?, ?);";
        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, uid);
            stm.setInt(2, pid);
            stm.setInt(3, amount);
//            rs = stm.executeQuery();//do câu lệnh sql không trả về gì cả nên không cần câu lệnh rs
            stm.executeUpdate();//phải dùng câu lệnh này để thực thi câu lênh sql trên
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    public void updateAmountProduct(int pid, int uid, int amount){
        String sql = "UPDATE Cart SET amount=? WHERE pid=? AND uid=?";
        try {
            stm = con.prepareStatement(sql);
            stm.setInt(3, uid);
            stm.setInt(2, pid);
            stm.setInt(1, amount);
//            rs = stm.executeQuery();//do câu lệnh sql không trả về gì cả nên không cần câu lệnh rs
            stm.executeUpdate();//phải dùng câu lệnh này để thực thi câu lênh sql trên
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    public HashMap<Integer, Integer> getCartByUser(int uid){
        String sql = "SELECT * FROM Cart WHERE uid=?;";
        HashMap<Integer, Integer> cartList = new HashMap<Integer, Integer>();
        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, uid);
            rs = stm.executeQuery();//do câu lệnh sql không trả về gì cả nên không cần câu lệnh rs
            while (rs.next()) {
                cartList.put(rs.getInt(2), rs.getInt(3));
            }
            return cartList;
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return null;
    }
    
    
}
