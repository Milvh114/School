<%-- 
    Document   : RatingProducts
    Created on : Dec 14, 2023, 10:23:19 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <style>
            .user-rating {
                font-size: 24px;
                color: #ffcc00;
            }

            .user-rating .selected {
                color: #ffc107;
            }
            .rounded-image {
                border-radius: 50%;
                width: 100px;
                height: 100px;
                object-fit: cover;
            }
        </style>
    </head>
    <body>
        <%@ include file="../../views/Header.jsp" %>
        <div class="container" style="background-color:#F5f5f5;">

            <div class="row">
                <div class="col-sm-3">
                    <div class="rating-block">
                        <h4 style="margin-top:20px;">Đánh giá Shop</h4>
                        <h2 class="bold padding-bottom-7" style="color: #FF9999;"> <fmt:formatNumber value="${sessionScope.reviewAvg}" pattern="#.#" /> <small>trên 5</small></h2>
                    </div>
                </div>
                <div class="col-sm-6" style="margin-top:6%;">
                    <div class="row">

                        <div class="container mt-4">
                            <form id="ratingForm" method="get" action="rating">
                                <input type="hidden" id="star" name="star" value="" />
                                <button type="button" class="btn btn-info"  onclick="submitForm(${-1})">Tất cả</button>&nbsp;
                                <c:forEach var="i" begin="1" end="5">
                                    <button type="button" class="btn btn-info"  onclick="submitForm(${i})">
                                        ${i} sao
                                    </button>
                                    &nbsp;
                                </c:forEach>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-7">
                    <hr />
                    <div class="review-block">
                        <c:if test="${reviewList.size() == 0}">Hiện tại chưa có đánh giá ${sessionScope.starRating} sao nào!</c:if>
                        <c:forEach var="review" items="${reviewList}">
                            <div class="row">
                                <div class="col-sm-3">
                                    <img src="https://inkythuatso.com/uploads/thumbnails/800/2023/03/9-anh-dai-dien-trang-inkythuatso-03-15-27-03.jpg" class="img-rounded rounded-image">
                                    <div class="review-block-name"><a href="#">${review.getUser().getFullName()}</a></div>

                                    <div class="user-rating">
                                        <c:forEach begin="1" end="${review.rate}">
                                            <span class="selected">&#9733;</span>
                                        </c:forEach>
                                    </div>
                                  
                                </div>
                                <div class="col-sm-9">


                                    <div class="review-block-title" style="margin-bottom: 6px;">${review.comment}</div>
                                    <form id="frm-product-details-${review.product.productId}" action="productDetail" method="post">
                                        <input type="hidden" value="${review.product.productId}" name="productID" id="productID${review.product.productId}">
                                        <div style="display: flex;background-color: #FF9999;" onclick="viewProduct('${review.product.productId}')">
                                            <img src="${review.product.images.get(0).image}" class="img-rounded"style="height:100px;width: 100px; object-fit: cover;">
                                            <div style="margin:10px 0 0 15px;max-width: 200px; overflow: hidden; text-overflow: ellipsis;">
                                                <p style="  white-space: nowrap;
                                                   overflow: hidden;
                                                   text-overflow: ellipsis;  margin: 0; ">${review.product.productName}</p>
                                                <p style="color: azure; font-weight: lighter; ">Phân loại hàng:<span>${review.product.category.categoryName}</span></p>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <hr />
                        </c:forEach>



                    </div>
                </div>
            </div>
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <li class="page-item <c:if test="${currentPage eq 1}">disabled</c:if>">
                        <a class="page-link" href="?page=${currentPage - 1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <c:forEach var="pageNum" begin="1" end="${totalPages}">
                        <li class="page-item <c:if test="${currentPage eq pageNum}">active</c:if>">
                            <a class="page-link" href="?star=${sessionScope.starRating}&page=${pageNum}">${pageNum}</a>                
                        </li>
                    </c:forEach>
                    <li class="page-item <c:if test="${currentPage eq totalPages}">disabled</c:if>">
                        <a class="page-link" href="?star=${sessionScope.starRating}&page=${currentPage + 1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
        <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script>

                                            function viewProduct(id) {
                                                document.getElementById("frm-product-details-" + id).submit();
                                            }
                                            function submitForm(value) {
                                                document.getElementById('star').value = value;
                                                document.getElementById('ratingForm').submit();
                                            }
        </script>
    </body>
</html>
