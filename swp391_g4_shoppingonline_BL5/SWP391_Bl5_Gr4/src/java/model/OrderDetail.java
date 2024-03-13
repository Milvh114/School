/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class OrderDetail {

    private int orderDetailId;
    private int orderId;
    private ProductDetail productDetail;
    private int quantity;
    private Payment payment;

    public OrderDetail(int orderDetailId, int orderId, ProductDetail productDetail, int quantity, Payment payment) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.productDetail = productDetail;
        this.quantity = quantity;
        this.payment = payment;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public OrderDetail(int orderDetailId, int orderId, ProductDetail productDetail, int quantity) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.productDetail = productDetail;
        this.quantity = quantity;
    }

    public OrderDetail(int orderId, ProductDetail productDetail, int quantity) {
        this.orderId = orderId;
        this.productDetail = productDetail;
        this.quantity = quantity;
    }

    

  
}