<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Product</title>
        <link rel="stylesheet" href="style.css" type="text/css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    </head>
    <body>

        <%@ include file="../../includes/sale_header.jsp" %>
        <%@ include file="../../includes/sale_left.jsp" %>

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
                    <div class="mt-3 d-flex gap-4 align-items-center">
                        <select class="form-select" style="width: 200px;" id="sortOrder">
                            <option value="asc">Sắp xếp tăng dần</option>
                            <option value="desc">Sắp xếp giảm dần</option>
                        </select>
                        <button class="btn btn-primary" id="sortButton">Sắp xếp</button>
                    </div>
                    <table class="table">
                        <thead>
                            <tr style="background-color: #21D19240;">
                                <th scope="col">ID</th>
                                <th scope="col">Ngày đặt hàng</th>

                                <th scope="col">Người đặt hàng</th>
                                <th scope="col">Tên người nhận hàng</th>
                                <th scope="col">Số điện thoại</th>
                                <th scope="col">Địa chỉ giao hàng</th>
                                <th scope="col">Tổng tiền</th>
                                <th scope="col">TT Thanh toán</th>
                                <th scope="col">Trạng thái</th>
                                <th scope="col" style="width: 100px;">Accept/Reject</th>
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
                                            ${ord.user.fullName}
                                        </div>
                                    </td>
                                    <td style="height: 78px;">
                                        <div class="d-flex align-items-center" style="height: 100%;">
                                            ${ord.user.fullName}
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
                                            No
                                        </div>
                                    </td>
                                  
                                    <td style="height: 78px;">
                                        <div class="d-flex align-items-center" style="height: 100%;">
                                            <c:choose>
                                                <c:when test="${ord.status == 0}">
                                                    <span style="color: orange;">Not Accept</span>
                                                </c:when>
                                                <c:when test="${ord.status == 1}">
                                                    <span style="color: green;">Accept</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span style="color: red;">Reject</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </td>

                                    <td class="d-flex gap-2 align-items-center" style="height: 78px;">
                                        <!-- Click this item to show a confirmation dialog -->
                                        <button type="button" class="btn btn-success me-2" onclick="confirmAccept(${ord.orderId})">
                                            <i class="fa-solid fa fa-check"></i>
                                        </button>
                                          
                                        <!-- Click this item to show a confirmation dialog -->
                                        <button type="button" class="btn btn-danger me-2" onclick="confirmReject(${ord.orderId})">
                                            <i class="fa-solid fa fa-close"></i>
                                        </button>
                                    
                                    </td>
                                     

                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="mt-3">
                        <nav aria-label="Page navigation example">
                            <ul class="pagination">
                                <li class="page-item <c:if test="${currentPage eq 1}">disabled</c:if>">
                                    <a class="page-link" href="?page=${currentPage - 1}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <c:forEach var="pageNum" begin="1" end="${totalPages}">
                                    <li class="page-item <c:if test="${currentPage eq pageNum}">active</c:if>">
                                        <a class="page-link" href="?page=${pageNum}">${pageNum}</a>                
                                    </li>
                                </c:forEach>
                                <li class="page-item <c:if test="${currentPage eq totalPages}">disabled</c:if>">
                                    <a class="page-link" href="?page=${currentPage + 1}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
                <div class="d-flex justify-content-center mt-5">

                </div>
            </div>
            <%@ include file="../../includes/sale_footer.jsp" %>
        </section>
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
    <script>
        function confirmAccept(orderId) {
            // Display a confirmation dialog
            if (confirm("Do you want to accept this order?")) {
                // If the user clicks "OK", redirect to the AcceptRequest servlet
                window.location.href = "./AcceptRequest?orderIdAccept=" + orderId;
            } else {
                // If the user clicks "Cancel", do nothing or provide additional logic
            }
        }
        document.getElementById("sortButton").addEventListener("click", function () {
            var selectedSortOrder = document.getElementById("sortOrder").value;
            // Redirect đến URL với tham số sắp xếp
            window.location.href = "?sortOrder=" + selectedSortOrder;
        });
        
        function confirmReject(orderId) {
            // Display a confirmation dialog
            if (confirm("Are you sure reject order ?")) {
                // If the user clicks "OK", redirect to the AcceptRequest servlet
                window.location.href = "./AcceptRequest?orderIdReject=" + orderId;
            } else {
                // If the user clicks "Cancel", do nothing or provide additional logic
            }
        }
    </script>
</html>