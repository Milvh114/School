<%-- 
    Document   : Header
    Created on : Dec 2, 2023, 9:06:26 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome CSS -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet">
        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
        <style>

        </style>
    </head>
    <body>

        <div class="modal fade" id="exampleModalCenter" tabindex="-1" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" >
            <div class="modal-dialog modal-dialog-centered" style="width:800px;display: flex !important; justify-content: end !important; height: 100%; margin: 0px; position:absolute; right: 0;">

                <div class="modal-content" style="height: 100%;">
                    <div class="modal-header" style="display: flex; justify-content: center; position: relative;">
                        <h5 class="modal-title" id="exampleModalCenterTitle">Yêu thích</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" style="position: absolute; top: 50%; left: 20px;"></button>
                    </div>
                    <div class="modal-body"  style="max-height: 400px; overflow-y: auto;">
                        <div style="display: flex; justify-content: space-between;">
                            <p>MÔ TẢ SẢN PHẨM</p>
                            <p style="margin-right:50px;">GIÁ</p>
                        </div>
                        <c:if test="${sessionScope.account != null}">


                            <c:forEach items="${sessionScope.favorList}" var="favor">

                                <div class="favorite-item" style="display: flex; align-items: center; justify-content: space-between; padding: 10px; border-bottom: 1px solid #ccc;">
                                    <img src="${favor.product.images.get(0).image}" class="img-rounded" style="width: 50px; height: 50px; object-fit: cover;">
                                    <div style="flex: 1; margin: 0 10px;">
                                        <p style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis; margin: 0; font-weight: bold;">${favor.product.productName}</p>
                                        <p style="color: #333; font-weight: lighter; margin: 5px 0;">${favor.product.category.categoryName}</p>
                                    </div>
                                    <span style="font-size: 12px; margin-right: 10px;">${favor.product.price} ₫</span>
                                    <button id="deleteButton" onclick="showCustomConfirm(${favor.reviewId})" style="background: none; border: none; cursor: pointer;">
                                        <i class="fa fa-trash" aria-hidden="true" style="font-size: 18px; color: #999;"></i>
                                    </button>
                                </div>

                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal" id="customConfirmModal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Xác nhận</h5>
                        <button type="button" class="btn-close" onclick="proceedAction()" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <p>Bạn chắc chắn muốn bỏ sản phẩm này ra khỏi danh sách yêu thích?</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" onclick="proceedAction()">Huỷ</button>
                        <button type="button" class="btn btn-success" onclick="updateFavor()">Đồng ý</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Topbar Start -->
        <div class="container-fluid">
            <div class="row align-items-center py-3 px-xl-5 border-bottom">
                <div class="col-lg-3 d-none d-lg-block">
                    <a href="home" class="text-decoration-none">
                        <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border px-3 mr-1">E</span>Shopper</h1>
                    </a>
                </div>
                <div class="col-lg-6 col-4 text-left">

                </div>

                <div class="col-lg-3 col-8 text-right">
                    <button type="button" style="background: none; border: none; padding: 0; position: relative;" data-bs-toggle="modal" data-bs-target="#exampleModalCenter">
                        <i class="fas fa-heart text-primary" style="font-size: 18px;">
                            <span style="position: absolute; top: -5px; right: 0; font-size: 15px; font-weight: bold; color: black;">${sessionScope.favorList.size()}</span>
                        </i>
                    </button>


                    <!--<span class="badge">0</span>-->

                    <a href="carts" class="btn border">
                        <i class="fas fa-shopping-cart text-primary"></i>
                        <!--<span class="badge">0</span>-->
                    </a>

                    <c:if test="${sessionScope.account == null}">
                        <a href="login" class="btn border">
                            <i class="fas fa-user text-dark"></i>
                        </a>
                        <a href="register" class="btn text-dark border">
                            Register
                        </a>
                    </c:if>
                    <c:if test="${sessionScope.account != null}">
                        <div class="btn dropdown">
                            <div href="" class="nav-link dropdown-toggle border" data-toggle="dropdown">${sessionScope.account.fullName}</div>
                            <div class="dropdown-menu rounded-0 m-0 ">
                                <c:if test="${sessionScope.account.role.roleId == 1}">
                                    <a href="userList" class="dropdown-item">User Management</a>
                                </c:if>
                                <c:if test="${sessionScope.account.role.roleId == 2}">
                                </c:if>
                                <c:if test="${sessionScope.account.role.roleId == 3}">
                                    <a href="customers" class="dropdown-item">Customers Management</a>
                                    <a href="Orders" class="dropdown-item">Orders Management</a>
                                </c:if>
                                <c:if test="${sessionScope.account.role.roleId == 4}">
                                    <a href="categorymanage" class="dropdown-item">Category Management</a>
                                    <a href="productmanage" class="dropdown-item">Product Management</a>
                                </c:if>
                                <c:if test="${sessionScope.account.role.roleId == 6}">
                                    <a href="Orders" class="dropdown-item">Orders Management</a>
                                </c:if>
                                <a href="userProfile" class="dropdown-item">User Profile</a>
                            </div>
                        </div>
                        <a href="logout" class="btn text-dark border">
                            <i class="fa-solid fa-right-from-bracket"></i>
                        </a>
                    </c:if>

                </div>
            </div>
        </div>
        <!-- Topbar End -->

        <!-- Navbar Start -->
        <div class="container-fluid mb-5">
            <div class="row border-top px-xl-5">
                <div class="col-lg-3 d-none d-lg-block">
                    <a class="btn shadow-none d-flex align-items-center justify-content-between bg-primary text-white w-100" data-toggle="collapse" href="#navbar-vertical" style="height: 65px; margin-top: -1px; padding: 0 30px;">
                        <h6 class="m-0">Sản phẩm</h6>
                        <i class="fa fa-angle-down text-dark"></i>
                    </a>
                    <nav class="collapse navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0" id="navbar-vertical">
                        <div class="navbar-nav w-100 overflow-hidden" style="height: 410px">
                            <c:forEach items="${listCategory}" var="listC">
                                <a href="category?categoryID=${listC.categoryId}" class="nav-item nav-link">${listC.categoryName}</a>

                            </c:forEach>

                    </nav>
                </div>
                <div class="col-lg-9">
                    <nav class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0">
                        <a href="" class="text-decoration-none d-block d-lg-none">
                            <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border px-3 mr-1">E</span>Shopper</h1>
                        </a>
                        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                            <div class="navbar-nav mr-auto py-0">
                                <a href="home" class="nav-item nav-link active">Home</a>
                                <div class="nav-item dropdown">
                                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Pages</a>
                                    <div class="dropdown-menu rounded-0 m-0">
                                        <a href="cart.html" class="dropdown-item">Shopping Cart</a>
                                        <a href="checkout.html" class="dropdown-item">Checkout</a>
                                    </div>
                                </div>

                            </div>
                            <div class="col-lg-6 col-4 text-left">
                                <form id="frm-seachtxt" action="search" method="get">

                                    <div class="input-group">
                                        <input id ="yourSearchInputId" type="text" class="form-control" name="textSearch" placeholder="tìm kiếm">

                                        <div class="input-group-append" onclick="submitTxtSearch()">
                                            <span class="input-group-text bg-transparent text-primary" onclick="submitTxtSearch()">
                                                <i class="fa fa-search" onclick="submitTxtSearch()"></i>
                                            </span>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </nav>

                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Font Awesome JS -->
        <script src="https://kit.fontawesome.com/8d39de38b8.js"></script>
        <script>

                                                    function showCustomConfirm(favorId) {
                                                        favorIdToDelete = favorId;
                                                        $('#customConfirmModal').modal('show');
                                                    }

                                                    var favorIdToDelete;
                                                    function showCustomConfirm(favorId) {
                                                        favorIdToDelete = favorId;
                                                        $('#customConfirmModal').modal('show');
                                                    }
                                                    function updateFavor() {
                                                        window.location.href = "updatefavor?favorId=" + favorIdToDelete;
                                                    }
                                                    // Function to handle the proceed action
                                                    function proceedAction() {
                                                        // Replace this with your actual proceed logic
                                                        console.log('Proceeding...');
                                                        // Close only the custom confirmation modal
                                                        $('#customConfirmModal').modal('hide');
                                                        // Prevent the default behavior of the Bootstrap modal plugin
                                                        return false;
                                                    }

                                                    function submitTxtSearch() {
                                                        var searchInput = document.getElementById("yourSearchInputId"); // Replace "yourSearchInputId" with the actual ID of your input field
                                                        var searchText = searchInput.value.trim();
                                                        if (searchText === "") {
                                                            alert("Please enter a search term.");
                                                        } else
                                                            document.getElementById('frm-seachtxt').submit();

                                                    }

        </script>

        <!-- Navbar End -->
    </body>
</html>