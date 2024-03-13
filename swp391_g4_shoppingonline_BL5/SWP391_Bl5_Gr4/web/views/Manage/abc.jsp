<%-- 
    Document   : abc
    Created on : Dec 15, 2023, 10:49:43 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>/*
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

    public OrderDetail() {
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

    public ProductDetail getProductDeteail() {
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

    public OrderDetail(int orderDetailId, int orderId, ProductDetail productDetail, int quantity, Payment payment) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.productDetail = productDetail;
        this.quantity = quantity;
        this.payment = payment;
    }
    public OrderDetail(int orderDetailId, int orderId, ProductDetail productDetail, int quantity) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.productDetail = productDetail;
        this.quantity = quantity;      
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "orderDetailId=" + orderDetailId + ", orderId=" + orderId + ", productDetail=" + productDetail + ", quantity=" + quantity + ", payment=" + payment + '}';
    }

  
}
Nhật
Minh Nhật
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Detail</title>
        <link rel="stylesheet" href="style.css" type="text/css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    </head>
    <body>
        <%@ include file="../../includes/sale_header.jsp" %>
        <c:choose>
            <c:when test="${user.role.roleId == 5}">
                <%@ include file="../../includes/sale_left.jsp" %>
            </c:when>
            <c:otherwise>
                <%@ include file="../../includes/saleStaff_left.jsp" %>
            </c:otherwise>
        </c:choose>

        <!-- Admin Products wrapper -->
        <section style="margin-left: 280px; height: calc(100vh - 83px); overflow-y: auto;">
            <div class="p-4 mb-5">
                <h5>Chi tiết đơn hàng</h5>
                <div class="mt-3">
                    <table class="table">
                        <thead>
                            <tr style="background-color: #21D19240;">
                                <th scope="col" style="height: 50px;">ID </th>
                                <th scope="col" style="height: 50px;">Ảnh sản phẩm</th>
                                <th scope="col" style="height: 50px;">Tên sản phẩm</th>
                                <th scope="col" style="height: 50px;">Số lượng</th>
                                <th scope="col" style="height: 50px;">Ðơn giá</th>
                                <th scope="col" style="height: 50px;">Tổng tiền</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="ordDetail" items="${orderDetails}">
                                <tr>
                                    <td style="height: 50px;">
                                        <div class="d-flex align-items-center" style="height: 100%;">
                                            ${ordDetail.orderDetailId}
                                        </div>
                                    </td>
                                    <td style="height: 50px;">
                                        <div class="d-flex align-items-center" style="height: 100%;">
                                            <!-- Display the image -->
                                            <img src="${ordDetail.productDetail.product.images[0].image}" alt="${ordDetail.productDetail.product.productName}" height="50" width="50">
                                        </div>
                                    </td>
                                    <td style="height: 50px;">
                                        <div class="d-flex align-items-center" style="height: 100%;">
                                            ${ordDetail.productDetail.product.productName}
                                        </div>
                                    </td>
                                    <td style="height: 50px;">
                                        <div class="d-flex align-items-center" style="height: 100%;">
                                            ${ordDetail.productDetail.quantity}
                                        </div>
                                    </td>
                                    <td style="height: 50px;">
                                        <div class="d-flex align-items-center" style="height: 100%;">
                                            <!-- Format and display the price -->
                                            <fmt:formatNumber value="${ordDetail.productDetail.product.price}" pattern="#,##0.000" var="formattedNumber" />
                                            ${formattedNumber}đ
                                        </div>
                                    </td>
                                    <td style="height: 50px;">
                                        <div class="d-flex align-items-center" style="height: 100%;">
                                            <!-- Format and display the total amount -->
                                            <fmt:formatNumber value="${ordDetail.quantity * ordDetail.productDetail.product.price}" pattern="#,##0.000" var="formattedNumber" />
                                            ${formattedNumber}đ
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="5" align="right">
                                    <strong>Tổng cộng:</strong>
                                </td>
                                <td>
                                    <fmt:formatNumber value="${totalAmount}" pattern="#,##0.000" var="formattedTotal" />
                                    ${formattedTotal}đ
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
                <div class="d-flex justify-content-center mt-5"></div>
            </div>

        </section>
    </body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/8d39de38b8.js" crossorigin="anonymous"></script>
    <script>
        function changeSale(id) {
            document.getElementById("change-sale-" + id).submit();
        }
    </script>
</html>
        <h1>Hello World!</h1>
    </body>
</html>
