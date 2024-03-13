<%-- 
    Document   : HomePage
    Created on : Dec 2, 2023, 1:26:40 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>EShopper</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">


    </head>

    <body>

        <%@ include file = "Header.jsp" %>

        <!-- Navbar End -->

        <div>
            <!-- Featured Start -->
            <div class="container-fluid pt-5">
                <div class="row px-xl-5 pb-3">
                    <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    </div>
                    <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    </div>
                    <div class="col-lg-6 col-md-12 col-sm-12 pb-1">
                        <!-- Đánh giá và Sản phẩm nằm trong cùng một hàng -->
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-12 pb-1">
                                <div class="d-flex align-items-center mb-4" style="padding: 30px;">
                                    <i class="fas fa-warehouse m-0 mr-3"></i>
                                    <p class="font-weight-semi-bold m-0">Sản phẩm: <span>${totalProduct}</span></p>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-12 pb-1">
                                <div class="d-flex align-items-center mb-4" style="padding: 30px;">
                                    <i class="far fa-star m-0 mr-3"></i>
                                    <a href="rating" style="cursor: pointer; text-decoration: none;"> <p class="font-weight-semi-bold m-0">Đánh giá: <span> <fmt:formatNumber value="${reviewAvg}" pattern="#.#" /></span>(${totalReview} đánh giá)</p></a>
                                </div>
                            </div>
                        </div>
                        <!-- Kết thúc hàng Đánh giá và Sản phẩm -->
                    </div>
                </div>
            </div>

            <!-- Featured End -->

            <!-- Offer Start -->

            <!-- Offer End -->

            <!-- Products Start -->
            <div class="container-fluid pt-5">

                <div class="text-center mb-4">
                    <h2 class="section-title px-5"><span class="px-2">Sản Phẩm của Shop</span></h2>
                </div>
                <div class="row px-xl-5 pb-3">
                    <c:forEach items="${listAllProducts}" var="listP">
                        <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                            <form id="frm-product-details-${listP.productId}" action="productDetail" method="get">
                                <input type="hidden" value="${listP.productId}" name="productID" id="productID${listP.productId}">
                                <div class="card product-item border-0 mb-4">
                                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                        <img src="${listP.images.get(0).image}"
                                             alt="new-prd" class="product-card-img w-100 h-auto cursor-pointer" onclick="viewProduct('${listP.productId}')"/>
                                    </div>
                                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                    </div>
                                    <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                                        <h6 class="text-truncate mb-3">${listP.productName}</h6>
                                        <div class="d-flex justify-content-center">
                                            <h6>${listP.price}$</h6><h6 class="text-muted ml-2"></h6>
                                        </div>
                                    </div>
                                    <div class="card-footer d-flex justify-content-between bg-light border">
                                        <a href="" class="btn btn-sm text-dark p-0" ><i class="fas fa-eye text-primary mr-1"></i>View Detail</a>
                                        <a href="/SWP391_Bl5_Gr4/orderproduct?productId=${listP.productId}" class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </c:forEach>
                </div>
            </div>
           <div class="mt-3 d-flex justify-content-center" >
                    <nav aria-label="Page navigation example" >
                        <ul class="pagination" sytle="margin-left:500px;">
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
            <div class="container-fluid pt-5">
                <div class="text-center mb-4">
                    <h2 class="section-title px-5"><span class="px-2">Sản phẩm mới</span></h2>
                </div>
                <div class="row px-xl-5 pb-3">
                    <c:forEach items="${newArrivals}" var="na">
                        <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                            <form id="frm-product-details-${na.productId}" action="productDetail" method="get">
                                <input type="hidden" value="${na.productId}" name="productID" id="productID${na.productId}">
                                <div class="card product-item border-0 mb-4">
                                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                        <img src="${na.images.get(0).image}"
                                             alt="new-prd" class="product-card-img w-100 h-auto cursor-pointer" onclick="viewProduct('${na.productId}')"/>
                                    </div>
                                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                    </div>
                                    <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                                        <h6 class="text-truncate mb-3">${na.productName}</h6>
                                        <div class="d-flex justify-content-center">
                                            <h6>${na.price}$</h6><h6 class="text-muted ml-2"><del>${na.price}$</del></h6>
                                        </div>
                                    </div>
                                    <div class="card-footer d-flex justify-content-between bg-light border">
                                        <a href="" class="btn btn-sm text-dark p-0" ><i class="fas fa-eye text-primary mr-1"></i>View Detail</a>
                                        <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </c:forEach>
                </div>
</div>
            </div>
            <!-- Products End -->




            <!-- Products Start -->
            <div class="container-fluid pt-5">
                <div class="text-center mb-4">
                    <h2 class="section-title px-5"><span class="px-2">Top sản phẩm bán chạy</span></h2>
                </div>
                <div class="row px-xl-5 pb-3">
                    <c:forEach items="${bestSellers}" var="bs">
                        <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                            <form id="frm-product-details-${bs.product.productId}" action="productDetail" method="get">
                                <input type="hidden" value="${bs.product.productId}" name="productID" id="productID${bs.product.productId}">
                                <div class="card product-item border-0 mb-4">
                                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                        <img src="${bs.product.images.get(0).image}"
                                             alt="new-prd" class="product-card-img w-100 h-auto cursor-pointer" onclick="viewProduct('${bs.product.productId}')"/>
                                    </div>
                                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                    </div>
                                    <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                                        <h6 class="text-truncate mb-3">${bs.product.productName}</h6>
                                        <div class="d-flex justify-content-center">
                                            <h6>${bs.product.price}$</h6><h6 class="text-muted ml-2"></h6>
                                        </div>
                                    </div>
                                    <div class="card-footer d-flex justify-content-between bg-light border">
                                        <a href="" class="btn btn-sm text-dark p-0" "><i class="fas fa-eye text-primary mr-1"></i>View Detail</a>
                                        <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <%@ include file = "Footer.jsp" %>
        <!-- Footer End -->

        <!-- Back to Top -->
        <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>


        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>

        <!-- Contact Javascript File -->
        <script src="mail/jqBootstrapValidation.min.js"></script>
        <script src="mail/contact.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>
        <script>
                                                 function viewProduct(id) {
                                                     document.getElementById("frm-product-details-" + id).submit();
                                                 }

        </script>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://kit.fontawesome.com/8d39de38b8.js" crossorigin="anonymous"></script>
    </body>
    <script src="js/header.js"></script>
</html>