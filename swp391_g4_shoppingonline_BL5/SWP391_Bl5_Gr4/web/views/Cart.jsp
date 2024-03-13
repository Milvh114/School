<%-- 
    Document   : Cart.jsp
    Created on : Dec 13, 2023, 11:26:13 PM
    Author     : User
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    </head>
    <body>
        <div class="container" style="margin-top: 5%;margin-bottom: 3%">
            <main>
                <!--                <div class="py-5 text-center">
                                    <a href="/">
                                        <img src="../../images/banner_logo.png" alt="Meeko" style="width: 340px;"/>
                                    </a>
                                </div>-->
                <div class="row g-5">
                    <div class=" order-md-last">
                        <h4 class="d-flex justify-content-between align-items-center mb-3">
                            <span class="text-dark">Đơn hàng </span>
                            <!-- Number of items in cart  -->
                            <span class="badge bg-dark rounded-pill"></span>
                        </h4>
                        <c:choose>
                            <c:when test="${listcd.size() == 0}">
                                <h1> List empty</h1>
                            </c:when>
                            <c:otherwise>
                                <ul class="list-group ">
                                    <c:forEach items="${listcd}" var="ord">
                                        <div>
                                            <form action="update_quantity">
                                                <input type="hidden" name="productId" value="${ord.productdetail.product.productId}"> 
                                                <li class="list-group-item d-flex justify-content-between align-items-center lh-sm">
                                                    <div class="d-flex align-items-center gap-4">
                                                        <img src=""
                                                             class=""
                                                             style="width: 50px; height: 50px; object-fit: cover; border-radius: 8px;" />
                                                        <span class="product-thumbnail__quantity"><input onchange="this.form.submit()" type="number" min="0" max="10" name="quantity" value="${ord.quantity }" style="width: 50px ; border-radius: 1px"></span>

                                                    </div>
                                                    <div>
                                                            <h6 class="my-0">  ${ord.productdetail.product.productName}</h6>
                                                    </div>
                                                    <div>
                                                        <h6 class="my-0">  ${ord.productdetail.size.value}</h6>
                                                    </div>
                                                    <div>
                                                        <h6 class="my-0">  ${ord.productdetail.color.value}</h6>
                                                    </div>
                                                    <span class="text-muted">
                                                        <fmt:formatNumber value="${ord.productdetail.quantity * ord.productdetail.product.price}" pattern="#,###" var="formattedNumber" />
                                                        ${formattedNumber}đ
                                                    </span>
                                                    <div class="col-md-2 d-flex justify-content-center">
                                                        <div>

                                                            <a href="deletecart?cartdetailId=${ord.cartDetailId}"  class="lead fw-normal mb-0 btn btn-outline-success"  >Delete</a>
                                                        </div>
                                                    </div>
                                                </li>
                                            </form>


                                        </div>
                                    </c:forEach>
                                    <li class="list-group-item d-flex justify-content-between">
                                        <span>Total (VND) :  ${Total} đ</span>

                                    </li>
                                </ul>
                            </c:otherwise>
                        </c:choose>


                        <div class="p-2 d-flex justify-content-between align-items-center gap-2">
                            <a href="home" style="color: black; text-decoration: none;">
                                <i class="fa-solid fa-arrow-left-long"></i>
                                Quay về trang chủ
                            </a>
                            <c:if test="${listcd.size() != 0}">
                                <a class="btn btn-primary btn-lg ms-3" href="checkout" >Checkout</a>
                            </c:if>

                        </div>
                    </div>

                </div>
            </main>
        </div>
    </body>
</html>
