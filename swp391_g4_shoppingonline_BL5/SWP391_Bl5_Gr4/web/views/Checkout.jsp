<%-- 
    Document   : Checkout
    Created on : Dec 14, 2023, 12:29:04 AM
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
                    <div class="col-md-5 col-lg-4 order-md-last">
                        <h4 class="d-flex justify-content-between align-items-center mb-3">
                            <span class="text-dark">Đơn hàng</span>
                            <!-- Number of items in cart  -->
                            <span class="badge bg-dark rounded-pill">${sessionScope.totalProduct}</span>
                        </h4>
                        <c:if test="${overQuantity != null}">
                            <span style="color: red">Một số sản phẩm bị thay đổi số lượng số lượng sản phẩm nhà bán hàng không đủ hoặc không tồn tại!</span>
                        </c:if>
                        <ul class="list-group mb-3">
                            <form method='post'action="checkout"> 
                            <c:forEach items="${listcarts}" var="ord">
                        
                                        <input type="hidden" name="productId" value="${ord.value.product.productId}"> 
                                         <li class="list-group-item d-flex justify-content-between align-items-center lh-sm">
                                        <div class="d-flex align-items-center gap-4">
                                            <img src="${ord.value.product.images.get(0).image}"
                                                 class=""
                                                 style="width: 50px; height: 50px; object-fit: cover; border-radius: 8px;" />
                                            <span class="product-thumbnail__quantity">${ord.value.quantity }</span>
                                            <div>
                                                <h6 class="my-0">  ${ord.value.product.productName}</h6>

                                            </div>
                                        </div>
                                        <span class="text-muted">
                                            <fmt:formatNumber value="${ord.value.quantity * ord.value.product.price}" pattern="#,###" var="formattedNumber" />
                                            ${formattedNumber}đ
                                        </span>
                                    </li>
                                    
                            </c:forEach>
                            <li class="list-group-item d-flex justify-content-between">
                                <span>Total (VND) ${Total}</span>
                                <strong class="pr-0 mr-0" id="totalOrderProduct"></strong>
                            </li>
                            
                        </ul>

                        <div class="p-2 d-flex justify-content-between align-items-center gap-2">
                            <a href="home" style="color: black; text-decoration: none;">
                                <i class="fa-solid fa-arrow-left-long"></i>
                                Quay về trang chủ
                            </a>
                            <button type="submit" class="btn btn-dark">Đặt hàng</button>
                        </div></form>
                    </div>
                    <div class="col-md-7 col-lg-8 row g-5 mt-0">
                        <form class="row" action="orderCustomer" method="post" id="orderDetailCustomer">
                            <div class="col-lg-6 col-12">
                                <h4 class="mb-3">Thông tin mua hàng</h4>
                                <input type="hidden" id="totalOrder" name="totalOrder"/>
                                <div class="row g-3">
                                    <div class="col-12">
                                        <label for="email" class="form-label">Email</label>
                                        <input type="email" class="form-control" name="email" id="input-email" placeholder="you@example.com" oninput="validateEmail(this)"
                                               required value="${account.email}">
                                        <span style="color: red;display: none" id="error-email">Ðịa chỉ email sai định dạng</span>
                                    </div>
                                    <div class="col-12">
                                        <label for="username" class="form-label">Số điện thoại</label>
                                        <input type="text" class="form-control" name="phone" id="input-phone" placeholder="Số điện thoại người nhận hàng"
                                               oninput="validatePhone(this)" required value="${account.phone}"/>
                                        <span id="error-phone" style="color: red; display: none;"></span>
                                    </div>
                                    <div class="col-12">
                                        <label for="username" class="form-label">Họ và tên</label>
                                        <input id="input-fullName" type="text" name="fullName" required maxlength="255" oninput="validateName(this)"
                                               class="form-control mb-3" placeholder="Nhập Họ và Tên" value="${account.fullName}"
                                               aria-label="Username">
                                        <span id="error-fullName" style="color: red; display: none;"></span>
                                    </div>

                                    <div class="col-12">
                                        <label for="address" class="form-label">Địa chỉ</label>
                                        <input type="text" class="form-control" name="address" id="input-address" placeholder="Địa chỉ nhận hàng" oninput="validateAddress(this)"
                                               required value="${account.address}">
                                        <span style="color: red;display: none" id="error-address"></span>
                                    </div>
                                </div>
                                <hr class="my-4">
                            </div>
                            <div class="col-lg-6 col-12">
                                <h4 class="mb-3">Vận chuyển</h4>
                                <div class="d-flex justify-content-between align-items-center mb-4"
                                     style="padding: 8px 12px; border: 1px solid #00000020; border-radius: 9px;">
                                    <div class="d-flex gap-2 align-items-center">
                                        <input checked type="radio" class="form-check-input"
                                               id="exampleRadio1">
                                        <span class="d-flex justify-content-between align-items-center" for="exampleRadio1">
                                            Giao hàng tận nơi
                                    </div>
                                    <div style="font-weight: bold;">20.000 đ</div>
                                </div>
                                <h4 class="mb-3">Thanh toán</h4>
                                <div class="accordion" id="accordionExample">
                                    <div class="accordion-item">
                                        <div class="p-3 d-flex align-items-center justify-content-between gap-3"
                                             id="headingOne" data-bs-toggle="collapse" data-bs-target="#collapseOne">
                                            <div class="d-flex gap-2">
                                                <input class="form-check-input" type="radio" name="payment" id="bank"
                                                       value="2">
                                                <p class="mb-0">Chuyển khoản qua ngân hàng</p>
                                            </div>
                                            <i class="fa-solid fa-money-check-dollar fa-xl"></i>
                                        </div>
                                        <div id="collapseOne" class="accordion-collapse collapse show"
                                             aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                                            <div class="accordion-body" style="background-color: #f8f8f8;">
                                                Ngân Hàng Á Châu (ACB) <br />
                                                STK: 338007 <br />
                                                Vu Ngoc Minh <br /><br /> <br />
                                                Nội dung chuyển khoản: "Số điện thoại bạn dùng để nhận hàng"
                                            </div>
                                        </div>
                                    </div>
                                    <div class="accordion-item">
                                        <div class="p-3 d-flex align-items-center justify-content-between gap-3"
                                             id="headingTwo" data-bs-toggle="collapse" data-bs-target="#collapseTwo">
                                            <div class="d-flex gap-2">
                                                <input class="form-check-input" type="radio" name="payment" id="cod"
                                                       value="1" checked>
                                                <p class="mb-0">Thanh toán khi giao hàng (COD)</p>
                                            </div>
                                            <i class="fa-solid fa-money-check-dollar fa-xl"></i>
                                        </div>
                                        <div id="collapseTwo" class="accordion-collapse collapse"
                                             aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
                                            <div class="accordion-body"
                                                 style="background-color: #f8f8f8; padding-top: 20px; padding-bottom: 20px;">
                                                Bạn chỉ phải thanh toán khi nhận được hàng
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </main>
        </div>
    </body>
</html>
