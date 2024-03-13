/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.List;

public class Order {
    private int orderId;
    private Date createDate;
    private double totalMoney;
    private int status;
    private User user;
    private User saleUser ;
    private List<OrderDetail> orderDetails;

    public Order(int orderId, Date createDate, double totalMoney, int status, User user, User saleUser, List<OrderDetail> orderDetails) {
        this.orderId = orderId;
        this.createDate = createDate;
        this.totalMoney = totalMoney;
        this.status = status;
        this.user = user;
        this.saleUser = saleUser;
        this.orderDetails = orderDetails;
    }
    public Order(int orderId, Date createDate, double totalMoney, int status, User user, User saleUser) {
        this.orderId = orderId;
        this.createDate = createDate;
        this.totalMoney = totalMoney;
        this.status = status;
        this.user = user;
        this.saleUser = null;
      
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    

    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getSaleUser() {
        return saleUser;
    }

    public void setSaleUser(User saleUser) {
        this.saleUser = saleUser;
    }
    
}
