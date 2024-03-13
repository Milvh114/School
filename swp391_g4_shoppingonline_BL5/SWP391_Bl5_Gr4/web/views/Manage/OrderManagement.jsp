<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title style="text-align:  center">Order Management</title>
        <link rel="stylesheet" href="style.css" type="text/css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    </head>
    <body>




        <c:choose>
            <c:when test="${user.role.roleId == 5}">
                <%@ include file="../../includes/sale_header.jsp" %>

                <%@ include file="../../includes/sale_left.jsp" %>
            </c:when>
            <c:otherwise>
                <%@ include file="../../includes/saleStaff_header.jsp" %>

                <%@ include file="../../includes/saleStaff_left.jsp" %>
            </c:otherwise>
        </c:choose>
        <!-- Admin Products wrapper -->
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
                    <c:choose>
                        <c:when test="${user.role.roleId == 5}">
                            <div class="mt-3 d-flex gap-4 align-items-center">
                                <select class="form-select" style="width: 200px;" id="salesFilter">
                                    <option value="">Chọn Người Phụ Trách</option>
                                    <c:forEach var="saleAdmin" items="${salesAdminList}">
                                        <option value="${saleAdmin.userId}">${saleAdmin.fullName}</option>
                                    </c:forEach>
                                </select>
                                <button class="btn btn-primary" id="searchButton">Tìm kiếm</button>
                            </div>
                            <div class="mt-3 d-flex gap-4 align-items-center">
                                <select class="form-select" style="width: 200px;" id="statusFilter">
                                    <option value="">Chọn Trạng thái</option>
                                    <option value="0">All</option>
                                    <option value="1">Accept</option>
                                    <option value="2">Reject</option>
                                  
                                </select>
                                <button class="btn btn-primary" id="filterButton">Lọc</button>
                            </div>

                        </c:when>
                        <c:otherwise>
                            <!-- Phần này sẽ được hiển thị khi user.id == 1 -->
                            <!-- Có thể để trống hoặc bạn có thể thêm nội dung bạn muốn hiển thị -->
                        </c:otherwise>
                    </c:choose>
                </div>


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

                        <div class="modal fade" id="salesAdminDialog" tabindex="-1" aria-labelledby="salesAdminDialogLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="salesAdminDialogLabel">Select Sales Admin</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <form id="salesAdminForm" action="OrderRequest" method="post">
                                            <!-- Add an input field to store orderId -->
                                            <input type="hidden" id="modalOrderId" name="modalOrderId" value="">

                                            <label for="salesAdmin">Chọn Sales Admin:</label>
                                            <select class="form-select" id="salesAdmin" name="salesAdmin">
                                                <option value="">Chọn Sales Admin</option>
                                                <c:forEach var="saleAdmin" items="${salesAdminList}">
                                                    <option value="${saleAdmin.userId}">${saleAdmin.fullName}</option>
                                                </c:forEach>
                                            </select>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                        <button type="button" class="btn btn-primary" onclick="redirectToOrderDetail()">Save changes</button>
                                    </div>
                                </div>
                            </div>
                        </div>


                        </td>
                       
                        <td class="d-flex gap-2 align-items-center" style=" height: 78px;">
                            <!--click this item to go to OrderDetail.jsp-->
                            <a  href="./OderDetail?orderId=${ord.orderId}" class="btn btn-success me-2">

                                <i class="fa-solid fa-eye"></i>
                                </button>
                            </a>
                            <c:if test="${user.role.roleId == 5 }">                                
                                <c:if test="${ord.status == 1 }">
                                <button type="button" class="btn btn-primary" 
                                        data-bs-toggle="modal" 
                                        data-bs-target="#salesAdminDialog"
                                        data-order-id="${ord.orderId}"
                                        onclick="updateModalOrderId('${ord.orderId}')">
                                    <i class="fa fa-user" aria-hidden="true"></i>
                                </button>
                                     </c:if>
                            </c:if>
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
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

<script>
                                            function updateModalOrderId(orderId) {
                                                // Set the orderId in the modal form
                                                document.getElementById("modalOrderId").value = orderId;
                                            }

                                            function redirectToOrderDetail() {
                                                var selectedSalesAdmin = document.getElementById("salesAdmin").value;
                                                var orderId = document.getElementById("modalOrderId").value;

                                                // Validate if the sales admin is selected
                                                if (selectedSalesAdmin && orderId) {
                                                    window.location.href = "./Assigned?orderId=" + orderId + "&saleUser=" + selectedSalesAdmin;
                                                } else {
                                                    // Handle the case where no sales admin or orderId is selected
                                                    alert("Please select a sales admin and order before proceeding.");
                                                }
                                            }
                                            document.getElementById("searchButton").addEventListener("click", function () {
                                                var selectedUserId = document.getElementById("salesFilter").value;
// Kiểm tra xem có lựa chọn nào được chọn hay không
                                                if (selectedUserId) {
                                                    // Redirect đến URL với salesAdminFilter
                                                    window.location.href = "?saleAdminFilter=" + selectedUserId;
                                                } else {
                                                    alert("Vui lòng chọn Người Phụ Trách trước khi tìm kiếm.");
                                                }
                                            });

                                            document.getElementById("filterButton").addEventListener("click", function () {
                                                var selectedStatus = document.getElementById("statusFilter").value;

                                                // Validate if the status is selected
                                                if (selectedStatus !== "") {
                                                    // Redirect to URL with the statusFilter
                                                    window.location.href = "?statusFilter=" + selectedStatus;
                                                } else {
                                                    alert("Vui lòng chọn Trạng thái trước khi lọc.");
                                                }
                                            });
</script>




</html>