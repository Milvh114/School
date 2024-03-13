<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Order</title>
        <link rel="stylesheet" href="style.css" type="text/css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    </head>
    <body>

        <%@ include file="../../includes/sale_header.jsp" %>

        <!-- Admin Products wrapper -->
        <section style="margin-left: 280px; height: calc(100vh - 83px); overflow-y: auto;">
            <div class="p-4 mb-5">
                <h5>
                    Quản lý đơn đặt hàng
                </h5>
                <!--                <div class="mt-3 d-flex gap-4 align-items-center">
                                    <div class="form-outline" style="width: 100%;">
                                        <input type="username" id="form12" class="form-control" placeholder="Tìm kiếm..." />
                                    </div>
                                    <select class="form-select" style="width: 200px;" id="city" required="">
                                        <option value="">Chọn loại sản phẩm</option>
                                        <option>Tất cả</option>
                                        <option>Chim cảnh</option>
                                    </select>
                                </div>-->
                <div class="mt-3">
                    <table class="table">
                        <thead>
                            <tr style="background-color: #21D19240;">
                                <th scope="col">ID</th>
                                <th scope="col">Ngày đặt hàng</th>


                                <th scope="col">Số điện thoại</th>
                                <th scope="col">Địa chỉ giao hàng</th>
                                <th scope="col">Tổng tiền</th>
                                <!--                                <th scope="col">TT Thanh toán</th>-->
                                <th scope="col">Trạng thái</th>
                                <th scope="col" style="width: 110px;">Hành động</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="ord" items="${orders}">
                                <tr>
                                    <th class="d-flex align-items-center" style="height: 78px;" scope="row">${ord.orderId}</th>
                                    <td style="height: 78px;">
                                        <div class="d-flex align-items-center" style="height: 100%;">
                                            ${ord.createDate}
                                        </div>
                                    </td>

                                    <td style="height: 78px;">
                                        <div class="d-flex align-items-center" style="height: 100%;">
                                            ${ord.user.phone}
                                        </div>
                                    </td>
                                    <td style="height: 78px;">
                                        <div class="d-flex align-items-center" style="height: 100%;">
                                            ${ord.user.address}
                                        </div>
                                    </td>
                                    <td style="height: 78px;">
                                        <div class="d-flex align-items-center" style="height: 100%;">
                                            <fmt:formatNumber value=" ${ord.totalMoney}" pattern="#,##0.000" var="formattedNumber" />
                                            ${formattedNumber}đ
                                        </div>
                                    </td>
                                    <td style="height: 78px;">
                                        <div class="d-flex align-items-center h-100">

                                            <c:if test="${ord.status==1}">
                                                <label class="switch" id="statusSwitch" data-userId="1">

                                                    <span class="slider round"></span>
                                                </label>
                                                Được duyệt
                                            </c:if>
                                            <c:if test="${ord.status== 0}">
                                                <label class="switch" id="statusSwitch" data-userId="1">

                                                    <span class="slider round"></span>
                                                </label>
                                                Chưa được duyệt
                                            </c:if>
                                            <c:if test="${ord.status== 2}">
                                                <label class="switch" id="statusSwitch" data-userId="1">

                                                    <span class="slider round"></span>
                                                </label>
                                                Huy don
                                            </c:if>
                                        </div>
                                    </td>
                                    <td class="d-flex gap-2 align-items-center" style=" height: 78px;">

                                        <!--click this item to go to OrderDetail.jsp-->
                                        <button type="button" class="btn btn-success me-2" data-bs-toggle="modal"
                                                data-bs-target="#detailOrder_${ord.orderId}">
                                            <i class="fa-solid fa-eye"></i>
                                        </button>                              
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
                <div class="d-flex justify-content-center mt-5">

                </div>

                <div class="d-flex justify-content-center mt-1">
                    <nav aria-label="Page navigation example col-12">
                        <ul class="pagination">
                            <%--For displaying Previous link except for the 1st page --%>
                            <c:if test="">
                                <li class="page-item">
                                    <a class="page-link" href="listAllProductAdmin?page=" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>

                            <%--For displaying Page numbers. The when condition does not display
                                        a link for the current page--%>
                            <c:forEach begin="1" end="" var="i">
                                <c:choose>
                                    <c:when test=""> 
                                        <li class="page-item"><a class="page-link bg-light" href="#">${i}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                        <li class="page-item"><a class="page-link" href="listAllProductAdmin?page=${i}">${i}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>

                            <%--For displaying Next link --%>
                            <c:if test="">
                                <li class="page-item">
                                    <a class="page-link" href="listAllProductAdmin?page=" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
                </div>
            </div>

        </section>
        <c:forEach var="item" items="${orders}">
            <div class="modal fade" id="detailOrder_${item.orderId}" tabindex="-1"
                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Chi tiết Khách hàng</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>

                        <div class="modal-body">
                            <div class="row">
                                <table class="table">
                                    <thead>
                                        <tr style="background-color: #21D19240;">
                                            <th style="width: 300px;">Tên sản phẩm</th>
                                            <th style="width: 50px;">Số lượng</th>
                                            <th style="width:50px;">Giá</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="od" items="${item.orderDetails}">
                                            <tr>
                                                <td style="width: 300px; ">${od.product.productName}</td>
                                                <td style="width: 50px;">${od.quantity} </td> 
                                                <td style="width: 50px;">${od.product.price}</td>
                                            </tr>
                                        </tbody>
                                    </c:forEach>
                                </table>
                            </div>

                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                Đóng
                            </button>
                        </div>
                    </div>
                </div>
            </div>


        </c:forEach>
    </body>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
    crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/8d39de38b8.js" crossorigin="anonymous"></script>
    <script>
        function changeSale(id) {
            document.getElementById("change-sale-" + id).submit();
        }
    </script>
</html>
