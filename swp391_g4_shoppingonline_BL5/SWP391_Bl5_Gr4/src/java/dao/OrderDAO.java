/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import model.Order;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.OrderDetail;
import model.Role;
import model.User;

/**
 *
 * @author Admin
 */
public class OrderDAO extends DBContext {

    public List<Order> getAllOrder(int offset, int recordsPerPage) {
        ArrayList<Order> list = new ArrayList<>();
        try {
            HashMap<Integer, Object> setter = new HashMap<>();
            int count = 0;
            String sql = "select * from [Order] where [Status]=1";

            sql += " order by OrderID offset ? Row\n"
                    + "  Fetch next ? rows only";
            setter.put(++count, offset);
            setter.put(++count, recordsPerPage);

            PreparedStatement stm = connection.prepareStatement(sql);
            for (Map.Entry<Integer, Object> entry : setter.entrySet()) {
                stm.setObject(entry.getKey(), entry.getValue());
            }
            ResultSet rs = stm.executeQuery();
            UserDAO uDao = new UserDAO();
            OrderDetailDAO oDao = new OrderDetailDAO();
            while (rs.next()) {
                User fromUser = uDao.getUserByID(rs.getInt("User"));
                User sale = null;
                try {
                    sale = uDao.getUserByID(rs.getInt("SaleUser"));
                } catch (Exception e) {
                }
                List<OrderDetail> listOrderDetailDAO = oDao.getOrderDetailsByOrderId(rs.getInt("OrderID"));
                list.add(new Order(rs.getInt("OrderID"),
                        rs.getDate("DateTime"),
                        rs.getDouble("TotalOrder"),
                        rs.getInt("Status"),
                        fromUser,
                        sale,
                        listOrderDetailDAO));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Order getOrderByID(int orderId) {
        UserDAO uDao = new UserDAO();
        OrderDetailDAO oDao = new OrderDetailDAO();
        List<OrderDetail> listOrderDetailDAO = oDao.getOrderDetailsByOrderId(orderId);
        try {
            String sql = "SELECT *\n"
                    + "  FROM [Order] Where OrderID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, orderId);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
//                User fromUser = new User();
//                fromUser.setUserId(rs.getInt("userId"));
                User sale = uDao.getUserByID(rs.getInt("SaleUser"));
                User user = uDao.getUserByID(rs.getInt("OrderID"));
                return new Order(rs.getInt("OrderID"),
                        rs.getDate("DateTime"),
                        rs.getDouble("TotalOrder"),
                        rs.getInt("Status"),
                        sale,
                        user,
                        listOrderDetailDAO);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Order> getOrderByUserId(int userId, int page, int LAST_PAGE) {
        ArrayList<Order> listOrder = new ArrayList<>();
        UserDAO uDao = new UserDAO();
        try {
            String sql = "SELECT *  FROM [Order] where [User] = ? order by OrderID offset (?-1) * ? row fetch next ? rows only";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, page);
            stm.setInt(3, LAST_PAGE);
            stm.setInt(4, LAST_PAGE);
            ResultSet rs = stm.executeQuery();
            OrderDetailDAO oDao = new OrderDetailDAO();

            while (rs.next()) {
//                User fromUser = new User();
//                fromUser.setUserId(rs.getInt("userId"));
                User sale = uDao.getUserByID(rs.getInt("SaleUser"));
                User user = uDao.getUserByID(rs.getInt("User"));
                listOrder.add(new Order(rs.getInt("OrderID"),
                        rs.getDate("DateTime"),
                        rs.getDouble("TotalOrder"),
                        rs.getInt("Status"),
                        user,
                                                sale,

                        oDao.getOrderDetailsByOrderId(rs.getInt("OrderID"))
                ));

            }
            return listOrder;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getTotalOrder(int userid) {

        try {

            String query = "  select count(OrderID) from Order where User = ?";
            PreparedStatement stm = connection.prepareStatement(query);
              stm.setInt(1, userid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<Order> getOrderByUserIdAndSaleUserId(int userId, int saleUserId) {
        List<Order> listOrder = new ArrayList<>();
        UserDAO uDao = new UserDAO();
        try {
            String sql = "SELECT *\n"
                    + "  FROM [Order] Where [User] = ? and SaleUser = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, saleUserId);

            ResultSet rs = stm.executeQuery();
            OrderDetailDAO oDao = new OrderDetailDAO();

            while (rs.next()) {

                User sale = uDao.getUserByID(rs.getInt("SaleUser"));
                User user = uDao.getUserByID(rs.getInt("OrderID"));
                listOrder.add(new Order(rs.getInt("OrderID"),
                        rs.getDate("DateTime"),
                        rs.getDouble("TotalOrder"),
                        rs.getInt("Status"),
                        sale,
                        user,
                        oDao.getOrderDetailsByOrderId(rs.getInt("OrderID"))
                ));

            }
            return listOrder;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateCancelOrder(int orderid) {

        try {
            String sql = "UPDATE [dbo].[Order]\n"
                    + "   SET [Status] = 0\n"
                    + " WHERE [OrderID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, orderid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //List order that sale staff have been asigned
    public List<Order> getAllOfSaleStaffOrder(int saleUser, int offset, int recordsPerPage) {
        ArrayList<Order> list = new ArrayList<>();
        try {
            HashMap<Integer, Object> setter = new HashMap<>();
            int count = 0;
            String sql = "SELECT * FROM [Order] WHERE [Status] = 1 AND SaleUser = ?"
                    + " ORDER BY OrderID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

            setter.put(++count, saleUser);
            setter.put(++count, offset);
            setter.put(++count, recordsPerPage);

            PreparedStatement stm = connection.prepareStatement(sql);

            for (Map.Entry<Integer, Object> entry : setter.entrySet()) {
                stm.setObject(entry.getKey(), entry.getValue());
            }

            ResultSet rs = stm.executeQuery();
            UserDAO uDao = new UserDAO();
            OrderDetailDAO oDao = new OrderDetailDAO();
            while (rs.next()) {
                User fromUser = uDao.getUserByID(rs.getInt("User"));
                User sale = uDao.getUserByID(rs.getInt("SaleUser"));
                List<OrderDetail> listOrderDetailDAO = oDao.getOrderDetailsByOrderId(rs.getInt("OrderID"));
                list.add(new Order(rs.getInt("OrderID"),
                        rs.getDate("DateTime"),
                        rs.getDouble("TotalOrder"),
                        rs.getInt("Status"),
                        fromUser,
                        sale,
                        listOrderDetailDAO));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
//Accept an order

    public void updateOrderStatus(int status, int orderID) {

        try {
            String sql = "UPDATE [Order] SET [Status] = ? WHERE [OrderID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, status);
            stm.setInt(2, orderID);
            ResultSet rs = stm.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //View request list
    //View request list
    public List<Order> getAllOrderNotAccept(int offset, int recordsPerPage, String sortOrder) {
        ArrayList<Order> list = new ArrayList<>();
        try {
            HashMap<Integer, Object> setter = new HashMap<>();
            int count = 0;
            String sql = "SELECT * FROM [Order] WHERE [Status] = 0";

            // Thêm điều kiện sắp xếp nếu sortOrder có giá trị
            if (sortOrder != null && !sortOrder.isEmpty()) {
                sql += " ORDER BY DateTime " + sortOrder;
            }

            sql += " OFFSET ? ROWS\n"
                    + " FETCH NEXT ? ROWS ONLY";

            setter.put(++count, offset);
            setter.put(++count, recordsPerPage);

            PreparedStatement stm = connection.prepareStatement(sql);
            for (Map.Entry<Integer, Object> entry : setter.entrySet()) {
                stm.setObject(entry.getKey(), entry.getValue());
            }
            ResultSet rs = stm.executeQuery();
            UserDAO uDao = new UserDAO();
            OrderDetailDAO oDao = new OrderDetailDAO();
            while (rs.next()) {
                User fromUser = uDao.getUserByID(rs.getInt("User"));
                User sale = uDao.getUserByID(rs.getInt("SaleUser"));
                List<OrderDetail> listOrderDetailDAO = oDao.getOrderDetailsByOrderId(rs.getInt("OrderID"));
                list.add(new Order(
                        rs.getInt("OrderID"),
                        rs.getDate("DateTime"),
                        rs.getDouble("TotalOrder"),
                        rs.getInt("Status"),
                        fromUser,
                        sale,
                        listOrderDetailDAO
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //Assign order to sale staff
    public void updateSaleUser(int orderId, int saleUserId) {
        try {
            String sql = "UPDATE [Order] SET SaleUser = ? WHERE OrderID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, saleUserId);
            stm.setInt(2, orderId);
            ResultSet rs = stm.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getNoOfOrderRecordsExceptPending() {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM [Order] WHERE [Status] = 1"; // Assuming 1 means "Not Pending"

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public int getCountOfSaleStaffOrder(int saleStaff) {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) AS total FROM [Order] WHERE [Status] = 1 AND SaleUser = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, saleStaff);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public int getMaxOrderID() {
        try {
            String sql = "SELECT Max(OrderID) as 'max'\n"
                    + "  FROM [Order] ";
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

    public int createByOrderId(Order order) {
        try {
          
        
            String sql = "INSERT INTO [dbo].[Order]   ([DateTime]   ,[TotalOrder]      ,[Status]     ,[User]      )  VALUES        (?     ,?,?  ,?  )";
            PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stm.setDate(1, order.getCreateDate());
            stm.setDouble(2, order.getTotalMoney());
            stm.setInt(3, order.getStatus());
            stm.setInt(4, order.getUser().getUserId());
           
            // Execute the insert statement
            int affectedRows = stm.executeUpdate();

            // Check if the insertion was successful
            if (affectedRows > 0) {
                // Retrieve the generated keys
                ResultSet rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("Failed to retrieve generated CartDetail ID.");
                }
            } else {
                throw new SQLException("Failed to create a new CartDetail.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public static void main(String[] args) {
        OrderDAO dao = new OrderDAO();

        Order o = dao.getOrderByID(2);
        System.out.println(dao.createByOrderId(o));

    }

}
