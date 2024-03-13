<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Detail</title>
        <link href="../css/style_1.css" rel="stylesheet" type="text/css"/>
        <link href="../css/backtotop.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/style_1.css">
        <script src="../js/script.js" type="text/javascript"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <style>
            @media (max-width: 767px) {
                .carousel-inner .carousel-item>div {
                    display: none;
                }

                .carousel-inner .carousel-item>div:first-child {
                    display: block;
                }
            }

            .carousel-inner .carousel-item.active,
            .carousel-inner .carousel-item-next,
            .carousel-inner .carousel-item-prev {
                display: flex;
            }

            /* medium and up screens */
            @media (min-width: 768px) {

                .carousel-inner .carousel-item-end.active,
                .carousel-inner .carousel-item-next {
                    transform: translateX(25%);
                }

                .carousel-inner .carousel-item-start.active,
                .carousel-inner .carousel-item-prev {
                    transform: translateX(-25%);
                }
            }

            .carousel-inner .carousel-item-end,
            .carousel-inner .carousel-item-start {
                transform: translateX(0);
            }
        </style>
    </head>

    <body>
        <%@ include file = "../HeaderUser.jsp" %>

        <div>
            <div class="container-home"
                 style="padding-left: 64px; padding-right: 64px; padding-top: 60px; padding-bottom: 60px; width: 100%;">
                <div class="row">
                    <div class="col-md-6 col-12">
                        <!-- Carousel wrapper -->
                        <div id="carouselExampleIndicators" class="carousel slide carousel-fade" data-bs-ride="carousel">
                            <!-- Slides -->
                            <div class="carousel-inner mb-5">
                                <div class="carousel-item active" style="height: 400px; width: 100%;">
                                    <img src="${images.get(0).image}"
                                         class="w-100 h-100 object-fit-cover" alt="..." />
                                </div>

                            </div>
                            <!-- Slides -->

                            <!-- Controls -->
                            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
                                    data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                            </button>
                            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
                                    data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>
                            <!-- Controls -->

                            <!-- Thumbnails -->

                            <!-- Thumbnails -->
                        </div>
                        <!-- Carousel wrapper -->
                    </div>
                    <div class="col-md-6 col-12">
                        <form action="orderproduct" method="post"> 
                            <input value="${product.productId}" type="hidden" name="productId">
                            <h3 style="font-weight: 400; margin-bottom: 14px;">${product.productName}</h3>
                            <p>${product.category.getCategoryName()}</p>
                            <div class="d-flex">
                                <p style="color: #c83252; font-weight: 700; font-size: 24px; margin-bottom: 14px;" id="proPrice">
                                    <fmt:formatNumber value="${product.price}" pattern="#,##0.000" var="formattedPrice" />
                                    ${formattedPrice}đ
                                </p>
                                <p>
                                <div class="col-6">
                                    <label for="category-film" class="col-form-label">Loại sản phẩm: </label>
                                    <select class="form-select" aria-label="Default select example" id="theloai"
                                            name="sizeid" required>


                                        <c:forEach var="c" items="${size}">
                                            <option value="${c.sizeId}"
                                                    >${c.value}</option>
                                        </c:forEach>


                                    </select>
                                </div>
                                <div class="col-6">
                                    <label for="category-film" class="col-form-label">Loại sản phẩm: </label>
                                    <select class="form-select" aria-label="Default select example" id="theloai"
                                            name="colorid" required>


                                        <c:forEach var="d" items="${color}">
                                            <option value="${d.colorId}"
                                                    >${d.value}</option>
                                        </c:forEach>


                                    </select>
                                </div>
                                </p>
                            </div> 
                            <button type="submit" class="btn btn-primary">Lưu</button>
                        </form>
                    </div>
                </div>

                <!-- Product Detail Go here -->
                <div style="margin-top: 10%">
                    <div class="mt-5 mb-5">
                        <p style="margin-top: 32px; font-weight: 600; margin-bottom: 4px;">Thông tin sản phẩm</p>
                        <p style="max-width: 700px; width: 100%; color: #777777; font-size: 14px; line-height: 24px;">${product.description}</p>
                    </div>
                    <div style="height: 1px; width: 100%; background-color: #33333330;"></div>

                    <!-- Slider in same category here  -->

                </div>
            </div>

            <section>
                <div class="rt-container">
                    <div class="col-rt-12">
                        <div class="Scriptcontent">


                            <div class="feedback">
                                <h4>Xin hãy dành ra vài phút đánh giá sản phẩm</h4>

                                <form method="post" action="reviewproduct">
                                    <input value="${product.productId}" type="hidden" name="productId">
                                    <span class="star-rating">
                                        <input type="radio" id="rating1" name="rating1" value="1"><i></i>
                                        <input type="radio" id="rating1" name="rating1" value="2"><i></i>
                                        <input type="radio" id="rating1" name="rating1" value="3"><i></i>
                                        <input type="radio"  id="rating1"name="rating1" value="4"><i></i>
                                        <input type="radio" id="rating1" name="rating1" value="5"><i></i>
                                    </span>

                                    <div class="clear"></div> 
                                    <hr class="survey-hr">

                                    <label for="m_3189847521540640526commentText">Bạn có nhận xét gì về sản phẩm của chúng tôi?</label><br/><br/>
                                    <textarea cols="75" id="TextComment" name="commentText" rows="5"></textarea><br>
                                    <br>
                                    <div class="clear"></div> 
                                    <button style="background:#43a7d5;color:#fff;padding:12px;border:0" type="submit" value="" onclick="validateForm(),viewProduct('${product.productId}')">Xác nhận </button>&nbsp;
                                   
                                </form>
                            </div>
                            <hr> 
                            <form id="frm-product-details-${product.productId}" action="productDetail" method="post">
                                <input type="hidden" value="${product.productId}" name="productID" id="productID${product.productId}"> 

                                <table style width="20%">
                                    <th>
                                        <div class="review-order dropdown" id="review-sort">
                                            <div type="button" id="sortMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                                <span>Sắp xếp: </span><span class="review-order-condition sort-condition" data-sort="0"> </span>
                                            </div>
                                            <ul class="dropdown-menu sort-drop" aria-labelledby="sortMenu">
                                                <li class="sort-option special" data-sort="2" > <a href="/SWP391_Bl5_Gr4/filterreview?rate=7">Đánh giá: cao - thấp</a></li>
                                                <li class="sort-option special" data-sort="3"><a href="/SWP391_Bl5_Gr4/filterreview?rate=8">Đánh giá: thấp - cao</a></li>
                                            </ul>
                                        </div>
                                    </th>
                                    <th>
                                        <div class="review_header-order">

                                            <div class="review-order dropdown" id="review-filter">
                                                <div type="button" id="filterMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                                    <span>Lọc: </span><span class="review-order-condition filter-condition" data-filter="0"> Tất cả</span>
                                                </div>
                                                <ul class="dropdown-menu filter-drop" aria-labelledby="filterMenu">
                                                    <li class="filter-option" data-filter="0"><a href="/SWP391_Bl5_Gr4/filterreview?rate=6">Tất cả sao</a></li>
                                                    <li class="filter-option" data-filter="1"><a href="/SWP391_Bl5_Gr4/filterreview?rate=1">1 sao</a></li>
                                                    <li class="filter-option" data-filter="2"><a href="/SWP391_Bl5_Gr4/filterreview?rate=2"> 2 sao</a></li>
                                                    <li class="filter-option" data-filter="3"><a href="/SWP391_Bl5_Gr4/filterreview?rate=3"> 3 sao</a></li>
                                                    <li class="filter-option" data-filter="4"><a href="/SWP391_Bl5_Gr4/filterreview?rate=4"> 4 sao</a></li>
                                                    <li class="filter-option" data-filter="5"><a href="/SWP391_Bl5_Gr4/filterreview?rate=5"> 5 sao</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </th>
                                    <div class="col-12 col-lg-3">
                                        <div class="review_header">
                                            <div class="review_header-title">
                                                Đánh giá sản phẩm
                                            </div>
                                        </div>
                                        <div class="review_comments"></div>	
                                        <div class="review_paginate">
                                            <ul></ul>
                                        </div>
                                    </div> 
                                </table>
                            </form>
                            <div class="feedback">
                                <c:forEach var="r" items="${listrv}" >
                                    <div>${r.user.fullName}</div>
                                    <span class="star-rating">
                                        <c:forEach begin="1" end="${r.rate}">
                                            <input type="radio" name="rating1" checked><i></i>
                                        </c:forEach>

                                    </span>
                                    <div>${r.comment}</div>
                                </c:forEach>

                            </div>
                        </div>
                    </div>
                </div>
            </section>

        </div>
        <%@ include file="../Footer.jsp" %>

        <script>
            function validateForm() {
                var comment = document.getElementById("TextComment").value.trim();
                if (comment === "") {
                    alert("Vui lòng nhập nhận xét của bạn.");
                  
                }
                return true;
            }

            function viewProduct(id) {
                document.getElementById("frm-product-details-" + id).submit();
            }
        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
        <script src="https://kit.fontawesome.com/8d39de38b8.js" crossorigin="anonymous"></script>
    </body>

</html>