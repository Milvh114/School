<%-- 
    Document   : Header
    Created on : Dec 2, 2023, 9:06:26 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>
        <!-- Topbar Start -->
        <div class="container-fluid">
            <div class="row align-items-center py-3 px-xl-5 border-bottom">
                <div class="col-lg-3 d-none d-lg-block">
                    <a href="home" class="text-decoration-none">
                        <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border px-3 mr-1">E</span>Shopper</h1>
                    </a>
                </div>
                <div class="col-lg-3 col-4 text-left">

                </div>

                <div class="col-lg-6 col-8 text-right">

                    <a href="" class="btn border">
                        <i class="fas fa-heart text-primary"></i>
                        <!--<span class="badge">0</span>-->
                    </a>
                    <a href="carts" class="btn border">
                        <i class="fas fa-shopping-cart text-primary"></i>
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
                            <div href="#123" class="nav-link dropdown-toggle border" data-toggle="dropdown">${sessionScope.account.fullName}</div>
                            <div class="dropdown-menu rounded-0 m-0 ">
                                <c:if test="${sessionScope.account.role.roleId == 1}">
                                    <a href="userList" class="dropdown-item">User Management</a>
                                </c:if>
                                <c:if test="${sessionScope.account.role.roleId == 2}">
                                    <a href="orderuser" class="dropdown-item">Order List</a>

                                </c:if>
                                <c:if test="${sessionScope.account.role.roleId == 3}">
                                    <a href="customers" class="dropdown-item">Customers Management</a>
                                    <a href="Orders" class="dropdown-item">Orders Management</a>
                                </c:if>
                                <c:if test="${sessionScope.account.role.roleId == 4}">
                                    <a href="categorymanage" class="dropdown-item">Category Management</a>
                                    <a href="productmanage" class="dropdown-item">Product Management</a>
                                </c:if>
                                <c:if test="${sessionScope.account.role.roleId == 5}">
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

        <!-- Navbar End -->
    </body>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>

</html>