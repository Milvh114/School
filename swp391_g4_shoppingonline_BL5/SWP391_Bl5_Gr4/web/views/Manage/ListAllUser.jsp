<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin page</title>
        <link rel="stylesheet" href="style.css" type="text/css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    </head>
    <body>

        <%@ include file="../../includes/admin_header.jsp" %>
        <%@ include file="../../includes/admin_left.jsp" %>
        <!-- Admin Products wrapper -->
        <section style="margin-left: 280px; height: calc(100vh - 83px); overflow-y: auto;">
            <div class="p-4 mb-5">
                <h5>
                    Danh sách người dùng
                </h5>

                </select>
            </div>
            <div class="mt-4">
                <table class="table">
                    <thead>
                        <tr style="background-color: #21D19240;">
                            <th scope="col">ID</th>
                            <th scope="col">Họ tên</th>
                            <th scope="col">Email</th>
                            <th scope="col">Di động</th>
                            <th scope="col">Vai trò</th>
                            <th scope="col">Trạng thái</th>
                            <th scope="col" style="width: 180px;">Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${list}">
                        <form method="post" action="changestatususer">
                            <tr>
                            <input name="userid" value="${item.userId}" type="hidden">
                            <th class="d-flex align-items-center" style="height: 78px;" scope="row">${item.userId}</thc>
                            <td style=" height: 78px;">
                                <div class="d-flex align-items-center h-100">
                                    ${item.fullName}
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
                                    ${item.role}
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
                            <td class="d-flex gap-2 align-items-center" style="width: 180px; height: 78px;">

                                <button href="/admin-product-detail.html" type="button"
                                        class="btn btn-outline-success me-2" data-bs-toggle="modal"
                                        data-bs-target="#itemDetail_${item.userId}">
                                    <i class="fa-solid fa-eye"></i>
                                </button>
                                <!--Modal View Detail-->


                                <!--Edit button--> 
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

    <c:forEach var="item" items="${list}">
        <div class="modal fade" id="itemDetail_${item.userId}" tabindex="-1"
             aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Chi tiết Người dùng</h5>
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
                                <h4 style="font-weight: 200; margin-bottom: 8px;">Vai trò: ${item.role.roleName}</h4>
                                <h4 style="font-weight: 200; margin-bottom: 8px;">Địa chỉ: ${item.address}</h4>  
                                <h4 style="font-weight: 200; margin-bottom: 8px;">Trạng thái: 
                                    <c:if test="${item.status=='true'}">
                                        Hợp lệ
                                    </c:if>
                                    <c:if test="${item.status=='false'}">
                                        Không hợp lệ
                                    </c:if>
                                </h4> 
                            </div>
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
        <!--Modal Edit Detail-->
        <div class="modal fade" id="updateItem_${product.productId}" tabindex="-1"
             aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Cập nhật sản phẩm</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>

                    <div class="modal-body">
                        <form method="POST" action="updateProduct" class="register-form"
                              id="register-form" enctype="multipart/form-data">
                            <input value="${product.productId}" type="hidden" name="productID">
                            <div class="col">
                                <div class="row">
                                    <div class="col-6">
                                        <label for="category-film" class="col-form-label">Tên
                                            sản phẩm:</label>
                                        <input value="${product.productName}" type="text" class="form-control" id="category-film" name="name" required>
                                    </div>
                                    <div class="col-6">
                                        <label for="category-film" class="col-form-label">Giá:</label>
                                        <input value="${product.price}" type="text" class="form-control" id="category-film" name="price" required>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-6">
                                        <label for="category-film" class="col-form-label">Loại sản phẩm: </label>
                                        <select class="form-select" aria-label="Default select example" id="theloai"
                                                name="category" required>
                                            <c:forEach items="${sessionScope.tags}" var="t">
                                                <c:forEach var="c" items="${t.categories}">
                                                    <option value="${c.categoryId}"
                                                            <c:if test="${c.categoryId == product.category.categoryId}">
                                                                selected
                                                            </c:if>
                                                            >${c.categoryName}</option>
                                                </c:forEach>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="row mt-2">
                                    <div class="col-6">
                                        <label for="category-film" class="col-form-label">Số lượng:</label>
                                        <input value="${product.quantity}" type="number" class="form-control" id="category-film" name="quantity" required>
                                    </div>
                                    <div class="col-6">
                                        <label for="category-film" class="col-form-label">Trạng thái:</label>
                                        <select class="form-select" aria-label="Default select example" id="theloai"
                                                name="status" required>
                                            <option value="true"
                                                    <c:if test="${true == product.status}">
                                                        selected
                                                    </c:if>
                                                    >Hiển thị</option>

                                            <option value="false"
                                                    <c:if test="${false == product.status}">
                                                        selected
                                                    </c:if>
                                                    >Ẩn</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="mb-3 mt-3">
                                    <label for="exampleFormControlFile1">Thay ảnh mới:</label>
                                    <input type="file" class="form-control-file mt-2" name="file" id="file" accept="image/*" multiple>
                                </div>
                                <div class="mb-3">
                                    <label for="category-film" class="col-form-label">Mô
                                        tả:</label>
                                    <textarea class="form-control" cols="30" rows="5" name="description">${product.description}</textarea>
                                </div>
                            </div>
                            <button type="button" class="btn btn-secondary"
                                    data-bs-dismiss="modal">
                                Đóng
                            </button>
                            <button type="submit" class="btn btn-primary">Lưu</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Delete Modal -->
        <!--Modal Delete Detail-->
        <div class="modal fade" id="deleteItem_${product.productId}" tabindex="-1"
             aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <input value="${product.productId}" type="hidden" name="productID">
                        <h5 class="modal-title" id="exampleModalLabel">Bạn có chắc chắn muốn xóa
                            sản phẩm
                        </h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>

                    <div class="modal-body">
                        ${product.productName} - ${product.price}d
                    </div>
                    <form action="deleteProduct" method="post">
                        <input value="${product.productId}" type="hidden" name="productID">
                        <div class="modal-footer">
                            <button style="width:100px" type="button" class="btn btn-secondary"
                                    data-bs-dismiss="modal">
                                Đóng
                            </button>
                            <button style="width:100px" type="submit" class="btn btn-danger">
                                Xóa</button>
                        </div>
                    </form>
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
    function createType() {
        var targetDiv = document.getElementById('type');
        var sourceDiv = document.getElementById('typeValue');

        // Clone khoi div duoc tao san
        var newDiv = sourceDiv.cloneNode(true);
        newDiv.style.display = 'block';
        targetDiv.appendChild(newDiv);
    }
</script>
</html>
