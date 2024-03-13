
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
                                    <
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