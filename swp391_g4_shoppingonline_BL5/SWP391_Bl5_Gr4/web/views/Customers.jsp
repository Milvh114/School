<%-- 
    Document   : Customers
    Created on : Dec 3, 2023, 5:07:08 PM
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Manager</title>
        <link rel="stylesheet" href="../css/style.css" type="text/css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

    </head>
    <body>
        <%@ include file="../../includes/saleStaff_header.jsp" %>
       <%@ include file="../../includes/saleStaff_left.jsp" %>

        <!-- Admin Products wrapper -->
        <section style="margin-left: 280px; height: calc(100vh - 83px); overflow-y: auto;">
            <div class="p-4 mb-5">
                <h5>
                    Danh sách khách hàng
                </h5>
                </select>
            </div>
            <div class="mt-4">
                <table class="table">
                    <thead>
                        <tr style="background-color: #21D19240;">
                            <th scope="col">ID</th>
                            <th scope="col">Họ tên</th>
                            <th scope="col">Ngày sinh</th>
                            <th scope="col">Email</th>
                            <th scope="col">Di động</th>
                            <th scope="col">Trạng thái</th>
                            <th scope="col" style="width: 180px;">Hành động</th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="item" items="${listCustomers}">
                        <form method="post" action="changeCusStatus">
                            <tr>
                            <input name="userid" value="${item.userID}" type="hidden">
                            <th class="d-flex align-items-center" style="height: 78px;" scope="row">${item.userID}</thc>
                            <td style=" height: 78px;">
                                <div class="d-flex align-items-center h-100">
                                    ${item.fullName}
                                </div>
                            </td>
                            <td style="height: 78px;">
                                <div class="d-flex align-items-center h-100">
                                    ${item.DOB}
                                </div>
                            </td>
                            <td style="height: 78px;">
                                <div class="d-flex align-items-center h-100">
                                    ${item.email}
                                </div>
                            </td>
                            <td style="height: 78px;">
                                <div class="d-flex align-items-center h-100">
                                    ${item.phone}
                                </div>
                            </td>

                            <td style="height: 78px;">
                                <div class="d-flex align-items-center h-100">

                                    <c:if test="${item.status=='true'}">
                                        <label class="switch" id="statusSwitch" data-userId="1">
                                            <input type="checkbox" name="statusCheckbox"   checked>
                                            <span class="slider round"></span>
                                        </label>
                                        Hợp lệ
                                    </c:if>
                                    <c:if test="${item.status=='false'}">
                                        <label class="switch" id="statusSwitch" data-userId="1">
                                            <input  type="checkbox" name="statusCheckbox" >
                                            <span class="slider round"></span>
                                        </label>
                                        Không hợp lệ
                                    </c:if>
                                </div>
                            </td>
                            <td class="d-flex gap-2 align-items-center" style="width: 180px; height: 78px;">

                                <button  type="button"
                                         class="btn btn-outline-success me-2" data-bs-toggle="modal"
                                         data-bs-target="#itemDetail_${item.userID}" >
                                    <i class="fa-solid fa-eye"></i>
                                </button>
                                <!--Modal View Detail-->
                                <button  type="submit"
                                         class="btn btn-outline-success me-2" >
                                    <i class="fa-solid fa-pen-to-square"></i>
                                </button>

                            </td>

                            </tr>
                        </form>
                    </c:forEach>


                    </tbody>
                </table>
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
    <!-- This container belong to FOOTER of ADMIN, DIFFERENT FROM NORMAL FOOTER; DONT copy to JSP, JUST INCLUDE -->

    <!-- Modal Add Product Detail-->


    <c:forEach var="item" items="${listCustomers}">
        <div class="modal fade" id="itemDetail_${item.userID}" tabindex="-1"
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
                            <div class="col-md-4 col-12">
                                <div>
                                    <img src="${item.avatar}"
                                         class="rounded-2" style="width: 120px; height: 120px; object-fit: contain;" />
                                </div>
                            </div>
                            <div class="col-md-8 col-12">
                                <div
                                    style="font-size: 20px; color: #333; margin-bottom: 10px; font-weight: bold;">
                                    Họ và tên: ${item.fullName}
                                </div>
                                <h4 style="font-weight: 200; margin-bottom: 8px;">Email: ${item.email}</h4>
                                <h4 style="font-weight: 200; margin-bottom: 8px;">Số điện thoại: ${item.phone}</h4>
                                <h4 style="font-weight: 200; margin-bottom: 8px;">Ngày sinh: ${item.DOB}</h4>
                                <h4 style="font-weight: 200; margin-bottom: 8px;">Địa chỉ: ${item.address}</h4>
                                <h4 style="font-weight: 200; margin-bottom: 8px;">Trạng thái: <c:if test="${item.status=='true'}">
                                        Hợp lệ
                                    </c:if>
                                    <c:if test="${item.status=='false'}">
                                        Không hợp lệ
                                    </c:if></h4> 
                            </div>
                        </div>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-12">
                                <div>
                                    <h3>Danh sách hàng đã mua</h3> 
                                </div>
                            </div><table class="table">
                                <thead>
                                    <tr style="background-color: #21D19240;">
                                        <th>Order ID</th>
                                        <th scope="col">Ngày Order</th>
                                        <th scope="col" style="text-align: center;">
                                            <table >
                                                <tr >Chi tiết</tr>
                                                <tr>
                                                    <td style="width: 350px;border: 1px solid #000;">Tên sản phẩm</td>
                                                    <td style="width: 50px;border: 1px solid #000;">Số lượng</td>
                                                    <td style="width:50px;border: 1px solid #000;">Giá</td>
                                                </tr>
                                            </table>
                                        </th>

                                        <th scope="col">Tổng Tiền</th>

                                        <td></td>
                                    </tr>
                                </thead>

                                <c:forEach var="o" items="${item.orders}">
                                    <tbody>
                                    <td>${o.orderId}</td>
                                    <td>${o.createDate}</td>
                                    <td>
                                        <c:forEach var="od" items="${o.orderDetails}">
                                            <table>
                                                <tr>
                                                    <td style="width: 350px;border: 1px solid #000; ">${od.product.productName}</td>
                                                    <td style="width: 50px;border: 1px solid #000;">${od.quantity} </td><!-- <td>1</td> -->
                                                    <td style="width: 50px;border: 1px solid #000;">${od.product.price}</td>
                                                </tr>
                                            </table>

                                        </c:forEach>

                                    </td>
                                    <td>${o.totalMoney}</td>
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


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
    crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/8d39de38b8.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        const checkboxes = document.querySelectorAll('.statusCheckbox');

        checkboxes.forEach(checkbox => {
            checkbox.addEventListener('change', function () {

                const columnId = this.dataset.columnid;


                if (this.checked) {

                    console.log(columnId);
                    redirectToServletWithColumnIdTrue(columnId)
                } else {
                    console.log(columnId);
                    redirectToServletWithColumnIdFalse(columnId)
                }
            });
        });
        function redirectToServletWithColumnIdTrue(columnId) {
            const servletURL = `/SWP391_Bl5_Gr4/changestatus?columnIdTrue=2`;
            window.location.href = servletURL;
        }
        function redirectToServletWithColumnIdFalse(columnId) {
            const servletURL = `/SWP391_Bl5_Gr4/changestatusfalse?columnIdFalse=2`;
            window.location.href = servletURL;
        }
        function createType() {
            var targetDiv = document.getElementById('type');
            var sourceDiv = document.getElementById('typeValue');

            // Clone khoi div duoc tao san
            var newDiv = sourceDiv.cloneNode(true);
            newDiv.style.display = 'block';
            targetDiv.appendChild(newDiv);
        }
        //                                              
    </script>
</body>
</html>
