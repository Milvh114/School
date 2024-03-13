<%-- 
    Document   : ProductManagement
    Created on : Dec 7, 2023, 12:53:15 AM
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
        <title>Product Management</title>
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
                                <th scope="col">Tên Sản Phẩm</th>
                                <th scope="col">Gía</th>
                                <th scope="col">Trạng thái</th>
                                <th scope="col">Ngày Tạo</th>
                                <th scope="col">Loại</th>
                                <th scope="col">Descriptin</th>
                                <th scope="col" style="width: 110px;">Hành động</th>
                            </tr>
                        </thead>
                        <tbody >
                            <c:forEach var="p" items="${listP}">
                                <tr >
                                    <th class="d-flex align-items-center" style="height: 78px;" scope="row">${p.productId}</th>
                                    <td style="width: 30%;height: 78px;">
                                        <div class="d-flex gap-2 align-items-center">
                                            <img src="${p.images.get(0).image}"
                                                 class="rounded-2" style="width: 60px; height: 60px; object-fit: contain;" />
                                            <h6>${p.productName}</h6>
                                        </div>
                                    </td>
                                    <td style="height: 78px;">
                                        <div class="d-flex align-items-center" style="height: 100%;">
                                            ${p.price}
                                        </div>
                                    </td>
                                    <td style="height: 78px;">
                                        <div class="d-flex align-items-center" style="height: 100%;">
                                            ${p.status}
                                        </div>
                                    </td>
                                    <td style="height: 78px;">
                                        <div class="d-flex align-items-center" style="height: 100%;">
                                            ${p.createDate}
                                        </div>
                                    </td>
                                    <td style="height: 78px;">
                                        <div class="d-flex align-items-center" style="height: 100%;">
                                            ${p.category.categoryName}
                                        </div>
                                    </td>
                                    <td style="width: 30%;height: 78px;">
                                        <div class="d-flex align-items-center" style="height: 100%;">
                                            ${p.description}
                                        </div>
                                    </td>
                                    <td class="d-flex gap-2 align-items-center" style=" height: 78px;">

                                        <!--click this item to go to OrderDetail.jsp-->
                                        <button href="/admin-product-detail.html" type="button" class="btn btn-success me-2" data-bs-toggle="modal"
                                                data-bs-target="#itemDetail_${p.productId}">
                                            <i class="fa-solid fa-eye"></i>
                                        </button>    
                                        <button type="button" class="btn btn-success me-2" data-bs-toggle="modal"
                                                data-bs-target="#updateItem_${p.productId}">
                                            <i class="fa-solid fa-pen-to-square"></i>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>

                <nav class="d-flex justify-content-center wow fadeIn">
                    <ul class="pagination pg-blue">

                        <!--Arrow left-->
                        <li class="page-item ">
                            <a class="page-link" href="/SWP391_Bl5_Gr4/productmanage?page=${page-1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                            </a>
                        </li>
                        <c:forEach begin="1" end="${totalPage}" var="i">


                            <li class="page-item ${i==page?"active":""}">
                                <a class="page-link " href="/SWP391_Bl5_Gr4/productmanage?page=${i}">${i}</a> 


                            </li>
                        </c:forEach>

                        <li class="page-item">
                            <a class="page-link" href="/SWP391_Bl5_Gr4/productmanage?page=${page+1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                            </a>
                        </li>
                    </ul>
                </nav>
                <div class="d-flex justify-content-center mt-5">

                </div>
            </div>

        </section>
        <c:forEach var="item" items="${listP}">
            <div class="modal fade" id="itemDetail_${item.productId}" tabindex="-1"
                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Chi tiết sản phẩm</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>

                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-4 col-12">
                                    <div>
                                        <img src="${item.images.get(0).image}"
                                             class="rounded-2" style="width: 60px; height: 60px; object-fit: contain;" />
                                    </div>
                                </div>
                                <div class="col-md-8 col-12">
                                    <div
                                        style="font-size: 18px; color: #333; margin-bottom: 10px; font-weight: bold;">
                                        Category: ${item.category.categoryName}
                                    </div>
                                    <h3 style="font-weight: 400; margin-bottom: 8px;"> ${p.name}</h3>
                                    <div
                                        class="d-flex align-items-center gap-3 justify-content-between">
                                        <p class="mb-0"
                                           style="color: #c83252; font-weight: 700; font-size: 24px;">
                                            ${item.price} đ</p>
                                        <p class="mb-0">
                                            <span style="font-weight: bold;">Số lượng còn
                                                lại:</span> ${item.quantity}
                                        </p>
                                    </div>
                                    <div
                                        style="height: 1px; width: 100%; background-color: #33333330;">
                                    </div>
                                    <div
                                        style="font-size: 18px; color: #333; margin-bottom: 10px;">
                                        Mô tả: ${item.description}
                                    </div>
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
            <div class="modal fade" id="updateItem_${item.productId}" tabindex="-1"
                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Cập nhật sản phẩm</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>

                        <div class="modal-body">
                            <form method="post" action="updateProduct" class="register-form"  id="register-form" >
                                <input value="${item.productId}" type="hidden" name="productID">
                                <div class="col">
                                    <div class="row">
                                        <div class="col-6">
                                            <label for="category-film" class="col-form-label">Tên
                                                sản phẩm:</label>
                                            <input value="${item.productName}" type="text" class="form-control" id="category-film" name="name" required>
                                        </div>
                                        <div class="col-6">
                                            <label for="category-film" class="col-form-label">Giá:</label>
                                            <input value="${item.price}" type="text" class="form-control" id="category-film" name="price" required>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-6">
                                            <label for="category-film" class="col-form-label">Loại sản phẩm: </label>
                                            <select class="form-select" aria-label="Default select example" id="theloai"
                                                    name="category" required>

                                                <c:forEach var="c" items="${listCategory}">
                                                    <option value="${c.categoryId}"
                                                            <c:if test="${c.categoryId == item.category.categoryId}">
                                                                selected
                                                            </c:if>
                                                            >${c.categoryName}</option>
                                                </c:forEach>

                                            </select>
                                        </div>
                                    </div>
                                    <div class="row mt-2">
                                        <div class="col-6">
                                            <label for="category-film" class="col-form-label">Số lượng:</label>
                                            <input value="${item.quantity}" type="number" class="form-control" id="category-film" name="quantity" required>
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
                                    <div class="mb-3 mt-3">
                                        <label for="exampleFormControlFile1">Thay ảnh mới:</label>
                                        <input type="file" class="form-control-file mt-2" name="file" id="file" accept="image/*" multiple>
                                    </div>
                                    <div class="mb-3">
                                        <label for="category-film" class="col-form-label">Mô
                                            tả:</label>
                                        <textarea class="form-control" cols="30" rows="5" name="description">${item.description}</textarea>
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
                        <h5 class="modal-title" id="exampleModalLabel">Thêm sản phẩm mới</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>

                    <div class="modal-body">
                        <form method="post" action="addProduct" class="register-form" id="register-form"  >
                            <input name="action" value="add" type="hidden">
                            <div class="col">
                                <div class="row">
                                    <div class="col-6">
                                        <label for="category-film" class="col-form-label">Tên
                                            sản phẩm:</label>
                                        <input type="text" class="form-control" id="category-film" name="name" required>
                                    </div>
                                    <div class="col-6">
                                        <label for="category-film" class="col-form-label">Giá:</label>
                                        <input type="number" step="0.1" class="form-control" id="category-film" min="0" name="price" required>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-6">
                                        <label for="category-film" class="col-form-label">Loại sản phẩm: </label>
                                        <select class="form-select" aria-label="Default select example" id="theloai"
                                                name="category" required>
                                            <option disabled selected value="">Chọn loại sản phẩm</option>
                                            <c:forEach var="c" items="${listCategory}">
                                                <option value="${c.categoryId}">${c.categoryName}</option>
                                            </c:forEach>

                                        </select>
                                    </div>
                                </div>
                                <div class="row mt-2">
                                    <div class="col-6">
                                        <label for="category-film" class="col-form-label">Số lượng:</label>
                                        <input type="number" class="form-control" id="category-film" name="quantity" min="0" required>
                                    </div>
                                    <div class="col-6">
                                        <label for="category-film" class="col-form-label">Trạng thái:</label>
                                        <select name="status" class="form-select" aria-label="Default select example" id="theloai" required>
                                            <option value="true">
                                                Hiển thị
                                            </option>
                                            <option value="false">
                                                Ẩn
                                            </option>
                                        </select>
                                    </div>
                                </div>
                                <div id="type">

                                </div>

                                <div class="mb-3 mt-3">
                                    <label for="exampleFormControlFile1">Ảnh:</label>
                                    <input type="file" class="form-control-file mt-2" name="file" id="file" accept="image/*"
                                           multiple required>
                                </div>
                                <div class="mb-3">
                                    <label for="category-film" class="col-form-label">Mô
                                        tả:</label>
                                    <textarea class="form-control" cols="30" rows="5" name="description"></textarea>
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
