<%-- 
    Document   : menu
    Created on : 9 thg 7, 2023, 05:19:09
    Author     : milvh
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Topbar Start -->
    <div class="container-fluid">
        
        <div class="row align-items-center py-3 px-xl-5">
            <div class="col-lg-3 d-none d-lg-block">
                <a href="home" class="text-decoration-none">
                    <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border px-3 mr-1">PEACH</span>Shop</h1>
                </a>
            </div>
            <div class="col-lg-6 col-6 text-left">
                <form action="search" method="get">
                    <div class="input-group">
                        <input name="txtSearch" type="text" class="form-control" placeholder="Search for products" value="${txtSearch}">
                        <div class="input-group-append">
                            <span class="input-group-text bg-transparent text-primary">
                            <button type="submit" class="btn btn-light py-0 px-0">
                                <i class="fa fa-search"></i>
                            </button>
                            </span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-lg-3 col-6 text-right">
                <a href="" class="btn border">
                    <i class="fas fa-heart text-primary"></i>
                    <span class="badge">0</span>
                </a>
                <c:choose>
                    <c:when test="${sessionScope.accLogin == null}">
                        <a href="login" class="btn border">
                            <i class="fas fa-shopping-cart text-primary"></i>
                            <span class="badge">0</span>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="cart" class="btn border">
                            <i class="fas fa-shopping-cart text-primary"></i>
                            <span class="badge">0</span>
                        </a>

                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <!-- Topbar End -->


    <!-- Navbar Start -->
    <div class="container-fluid mb-5">
        <div class="row border-top px-xl-5">
            <div class="col-lg-3 d-none d-lg-block">
                <a class="btn shadow-none d-flex align-items-center justify-content-between bg-primary text-white w-100" data-toggle="collapse" href="#navbar-vertical" style="height: 65px; margin-top: -1px; padding: 0 30px;">
                    <h6 class="m-0">Categories</h6>
                    <i class="fa fa-angle-down text-dark"></i>
                </a>
                <nav class="collapse show navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0" id="navbar-vertical">
                    
                        <div class="navbar-nav w-100 overflow-hidden" style="height: 165px">
                            <c:forEach items="${listC}" var="cat">
                                <a href="category?catid=${cat.id}" class="nav-item nav-link ${act == cat.id ? "active":""}">${cat.name}</a>
                            </c:forEach>
                        </div>
                    
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
                            <a href="shop.jsp" class="nav-item nav-link">Shop</a>
                            <div class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Pages</a>
                                <div class="dropdown-menu rounded-0 m-0">
                                    <a href="pay?uid=${sessionScope.accLogin.uid}" class="dropdown-item">Shopping Cart</a>
                                    <a href="checkout.html" class="dropdown-item">Checkout</a>
                                </div>
                            </div>
                            <c:if test="${sessionScope.accLogin.isSell == 1 or sessionScope.accLogin.isAdmin ==1}">
                                <div class="nav-item dropdown">
                                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Manager</a>
                                    <div class="dropdown-menu rounded-0 m-0">
                                        <c:if test="${sessionScope.accLogin.isAdmin == 1}">
                                            <a href="cart.html" class="dropdown-item">Manager Account</a>
                                        </c:if>

                                            <a href="manager" class="dropdown-item">Manager Product</a>

                                    </div>
                                </div>
                            </c:if>
                            <div class=" nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Categories</a>
                                <div class="dropdown-menu rounded-0 m-0">
                                    <c:forEach items="${listC}" var="cat">
                                        <a href="category?catid=${cat.id}" class="nav-item nav-link ${act == cat.id ? "active":""}">${cat.name}</a>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="navbar-nav ml-auto py-0">
                            
                            <c:if test="${sessionScope.accLogin != null}">
                                <a href="" class="nav-item nav-link">Hello ${sessionScope.accLogin.user}!</a>
                                <a href="logout" class="nav-item nav-link">Logout</a>
                            </c:if>
                            <c:if test="${sessionScope.accLogin == null}">
                                <a href="login" class="nav-item nav-link">Login</a>
                                <a href="login" class="nav-item nav-link">Signup</a>
                            </c:if>
                        </div>
                    </div>
                </nav>
            </div>        
        </div>
    </div>
    <!-- Navbar End -->
