<%-- 
    Document   : UserOrder
    Created on : Dec 14, 2023, 11:42:22 PM
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
        <link rel="stylesheet" href="style.css" type="text/css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

    </head>
    <body>
        <%@ include file="../HeaderUser.jsp" %>
        <section style="margin-left: 280px; height: calc(100vh - 83px); overflow-y: auto;">
            <div class="p-4 mb-5">
                <div class="mt-3">

                    <table class="table">
                        <thead>
                            <tr style="background-color: #21D19240;">
                                <th scope="col">ID</th>
                                <th scope="col">Ngày đặt hàng</th>
                                <th scope="col">Người đặt hàng</th>
                                <th scope="col">Người phụ trách</th>
                                <th scope="col">Số điện thoại</th>
                                <th scope="col">Địa chỉ giao hàng</th>
                                <th scope="col">Tổng tiền</th>
                                <th scope="col">TT Thanh toán</th>
                                <th scope="col">Trạng thái</th>
                                <th scope="col" style="width: 110px;">Hành động</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="ord" items="${orders}">
                            <form method="post" action="updatecancel">
                                <input name="orderid" value="${ord.orderId}" type="hidden">
                                <tr>
                                    <th class="d-flex align-items-center" style="height: 78px; display: none;" >${ord.orderId}</th>

                                    <td style="height: 78px;">
                                        <div class="d-flex align-items-center" style="height: 100%;">
                                            ${ord.createDate}
                                        </div>
                                    </td>
                                    <td style="height: 78px;">
                                        <div class="d-flex align-items-center" style="height: 100%;">
                                            ${ord.user.fullName}
                                        </div>
                                    </td>
                                    <td style="height: 78px;">
                                        <div class="d-flex align-items-center" style="height: 100%;">
                                            ${ord.saleUser.fullName}
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
                                        <div class="d-flex align-items-center" style="height: 100%;">
                                            NO
                                        </div>
                                    </td>
                                    <td style="height: 78px;">

                                        <div class="d-flex align-items-center h-100" style="height: 100%; color: ${ord.status == 0 ? 'red' : 'green'};">
                                            <c:if test="${ord.status ==1}">
                                                Accept
                                            </c:if>
                                            <c:if test="${ord.status==0}">
                                                Not Accept
                                            </c:if>
                                        </div>
                                    </td>

                                    <td class="d-flex gap-2 align-items-center" style=" height: 78px;">

                                        <!--click this item to go to OrderDetail.jsp-->
                                        <button  type="submit"
                                                 class="btn btn-outline-success me-2" >
                                            <i class="fa-solid fa-pen-to-square"></i>
                                        </button>
                                        <a  href="./orderuserdetail?orderId=${ord.orderId}" class="btn btn-success me-2">

                                            <i class="fa-solid fa-eye"></i>
                                            </button>
                                        </a>
                                    </td>


                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </table>
                </div> 
                <nav class="d-flex justify-content-center wow fadeIn">
                    <ul class="pagination pg-blue">

                        <!--Arrow left-->
                        <li class="page-item ">
                            <a class="page-link" href="/SWP391_Bl5_Gr4/orderuser?page=${page-1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                            </a>
                        </li>
                        <c:forEach begin="1" end="${totalPage}" var="i">


                            <li class="page-item ${i==page?"active":""}">
                                <a class="page-link " href="/SWP391_Bl5_Gr4/orderuser?page=${i}">${i}</a> 


                            </li>
                        </c:forEach>

                        <li class="page-item">
                            <a class="page-link" href="/SWP391_Bl5_Gr4/orderuser?page=${page+1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                            </a>
                        </li>
                    </ul>
                </nav>
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
                                            <th style="width: 50px;">Màu sắc</th>
                                            <th style="width: 50px;">Kích cỡ</th>
                                            <th style="width: 50px;">Số lượng</th>
                                            <th style="width:50px;">Giá</th>
                                        </tr>
                                    </thead>

                                    <c:forEach var="od" items="${item.orderDetails}"> 
                                        <tbody>
                                            <tr>
                                                <td style="width: 300px; ">${od.quantity}</td>

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
    <%@ include file="../Footer.jsp" %>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
    crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/8d39de38b8.js" crossorigin="anonymous"></script>
</html>
