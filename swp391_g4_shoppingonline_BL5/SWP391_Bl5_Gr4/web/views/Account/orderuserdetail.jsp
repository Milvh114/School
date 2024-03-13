<%-- 
    Document   : orderuserdetail
    Created on : Dec 15, 2023, 12:51:46 AM
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <link rel="stylesheet" href="style.css" type="text/css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
   
    </head>
    <body>
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
                                            ${ordDetail.quantity}
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
</html>
