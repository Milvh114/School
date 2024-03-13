<%-- 
    Document   : CategoryManagement
    Created on : Dec 11, 2023, 11:31:30 PM
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
        <title>Category Management</title>
        <link rel="stylesheet" href="../css/style.css" type="text/css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

    </head>
    <body>
        <%@ include file="../../includes/stock_header.jsp" %>
        <%@ include file="../../includes/stock_left.jsp" %>
        <!-- Admin Products wrapper -->
        <section style="margin-left: 280px; height: calc(100vh - 83px); overflow-y: auto;">
            <div class="p-4 mb-5">
                <h5>
                    Quản lý sản phẩm
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
                <div class="d-flex justify-content-end mt-4">
                    <a style="width: 200px;" data-bs-target="#addItem" data-bs-toggle="modal" type="button"
                       class="btn btn-success">
                        <i class="fa-solid fa-plus"></i>
                        Thêm sản phẩm</a>
                </div>
                <div class="mt-3">
                    <table class="table" >
                        <thead>
                            <tr style="background-color: #21D19240;">
                                <th scope="col">ID</th>
                                <th scope="col">Category Name</th>
                                <th scope="col">Description</th>
                                <th scope="col" style="width: 110px;">Hành động</th>
                            </tr>
                        </thead>
                        <tbody >
                            <c:forEach var="p" items="${listCate}">
                                <tr >
                                    <th class="d-flex align-items-center" style="height: 78px;" scope="row">${p.categoryId}</th>
                                    <td style="height: 78px;">
                                        <div class="d-flex align-items-center" style="height: 100%;">
                                            ${p.categoryName}
                                        </div>
                                    </td>
                                    <td style="height: 78px;">
                                        <div class="d-flex align-items-center" style="height: 100%;">
                                            ${p.description}
                                        </div>
                                    </td>
                                    <td class="d-flex gap-2 align-items-center" style=" height: 78px;">
                                        <!--                                        <button href="/admin-product-detail.html" type="button" class="btn btn-success me-2" data-bs-toggle="modal"
                                                                                        data-bs-target="#itemDetail_${p.categoryId}">
                                                                                    <i class="fa-solid fa-eye"></i>
                                                                                </button>    -->
                                        <button type="button" class="btn btn-success me-2" data-bs-toggle="modal"
                                                data-bs-target="#updateItem_${p.categoryId}">
                                            <i class="fa-solid fa-pen-to-square"></i>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
                <div class="d-flex justify-content-center mt-5">

                </div>
            </div>

        </section>
        <c:forEach var="item" items="${listCate}">
            <div class="modal fade" id="updateItem_${item.categoryId}" tabindex="-1"
                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Cập nhật sản phẩm</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form method="post" action="updateCate" class="register-form"  id="register-form" >
                                <input value="${item.categoryId}" type="hidden" name="categoryId">
                                <div class="col">
                                    <div class="row">
                                        <div class="col-6">
                                            <label for="category-film" class="col-form-label">Tên danh mục:</label>
                                            <input value="${item.categoryName}" type="text" class="form-control" id="category-film" name="catename" required>
                                        </div>
                                        <div class="col-6">
                                            <label for="category-film" class="col-form-label">Description :</label>
                                            <input value="${item.description}" type="text" class="form-control" id=" category-film" name="catedes" required>
                                        </div>
                                    </div>
                                    <div class="col-6">
                                        <label for="category-film" class="col-form-label">Trạng thái:</label>
                                        <select class="form-select" aria-label="Default select example" id="theloai"
                                                name="status" required>
                                            <option value="true"
                                                    <c:if test="${true == item.status}">
                                                        selected
                                                    </c:if>
                                                    >Hiển thị</option>

                                            <option value="false"
                                                    <c:if test="${false == item.status}">
                                                        selected
                                                    </c:if>
                                                    >Ẩn</option>
                                        </select>
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
        </c:forEach>

        <div class="modal fade" id="addItem" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Thêm mới danh mục</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>

                    <div class="modal-body">
                        <form method="post" action="addcate" class="register-form" id="register-form"  >
                            <div class="col">
                                <div class="row">
                                    <div class="col-6">
                                        <label for="category-film" class="col-form-label">Tên danh mục:</label>
                                        <input value="${item.categoryName}" type="text" class="form-control" id="category-film" name="catename" required>
                                    </div>
                                    <div class="col-6">
                                        <label for="category-film" class="col-form-label">Description :</label>
                                        <input value="${item.description}" type="text" class="form-control" id=" category-film" name="catedes" required>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <label for="category-film" class="col-form-label">Trạng thái:</label>
                                    <select class="form-select" aria-label="Default select example" id="theloai"
                                            name="status" required>
                                        <option value="true"
                                                <c:if test="${true == item.status}">
                                                    selected
                                                </c:if>
                                                >Hiển thị</option>

                                        <option value="false"
                                                <c:if test="${false == item.status}">
                                                    selected
                                                </c:if>
                                                >Ẩn</option>
                                    </select>
                                </div>
                            </div>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                Đóng
                            </button>
                            <button type="submit" class="btn btn-primary">Lưu</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
    crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/8d39de38b8.js" crossorigin="anonymous"></script>
    <script>
        function changeSale(id) {
            document.getElementById("change-sale-" + id).submit();
        }
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
