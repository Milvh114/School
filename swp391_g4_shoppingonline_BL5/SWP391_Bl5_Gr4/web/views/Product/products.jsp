<%-- 
    Document   : product
    Created on : Dec 10, 2023, 12:15:02 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EShopper</title>
        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 
        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <!-- Libraries Stylesheet -->
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>
        <%@ include file="../../views/Header.jsp" %>
        <div class="filter-order-wrappper d-flex align-items-center gap-3 container-fluid ms-5 justify-content-center">
            <c:if test="${category == null}">
                <div class="filter-text">Sắp xếp theo</div>

                <form id="frm-sort" action="search" method="post">
                    <select class="form-select" name="sortOption" onchange="submitForm()" aria-label="Default select example">
                        <option value="-1"
                                <c:if test="${sortOption == -1}">selected</c:if>
                                    >Mặc định</option>
                                <option value="1"
                                <c:if test="${sortOption == 1}">selected</c:if>
                                    >A → Z</option>
                                <option value="2"
                                <c:if test="${sortOption == 2}">selected</c:if>
                                    >Z → A</option>
                                <option value="3"
                                <c:if test="${sortOption == 3}">selected</c:if>
                                    >Giá tăng dần</option>
                                <option value="4"
                                <c:if test="${sortOption == 4}">selected</c:if>
                                    >Giá giảm dần</option>
                        </select>
                        <input type="hidden" name="moreOption" id="sortOptionValue"  value="${sortOption}">
                    <button type="button" class="btn btn-outline-primary" onclick="setAndSubmitForm(5)">Mới nhất</button>          
                    <button type="button" class="btn btn-outline-primary" onclick="setAndSubmitForm(6)">Bán chạy</button>
                </form>
            </div>
        </c:if>
        <div class="container-fluid mb-5">
            <div class="row border-top px-xl-5">
                <c:if test="${sessionScope.textSearch != null && sessionScope.textSearch != ''}">
                    <p class="filter-text">Kết quả tìm tiếm cho tu khoa '${sessionScope.textSearch}'</p>
                </c:if>
                <!-- Products Start -->
                <div class="container-fluid pt-5">

                </div>

                <c:if test="${productsSearch.size() == 0}">
                    <div class="text-center mb-4">
                        <h2 class="section-title px-5"><span class="px-2">Trang tìm kiếm</span></h2>
                    </div>
                    <br>
                    <p>Đã tìm thấy ${productsSearch.size()} kết quả phù hợp</p>

                </c:if>
                <c:if test="${(sessionScope.textSearch != null || sessionScope.textSearch != '') && productsSearch.size() != 0}">
                    <div class="w-100 d-flex justify-content-center">
                        <h2 class="section-title px-5"><span class="px-2">Trang tìm kiếm</span></h2>
                    </div>
                    </br>
                    <p>Đã tìm thấy ${productsSearch.size()} kết quả phù hợp</p><br>
                </c:if>

                <div class="row px-xl-5 pb-3">
                    <c:forEach items="${productsSearch}" var="ps">
                        <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                            <form id="frm-product-details-${ps.productId}" action="productDetail" method="post">
                                <input type="hidden" value="${ps.productId}" name="productID" id="productID${ps.productId}">
                                <div class="card product-item border-0 mb-4">
                                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                        <img src="${ps.images.get(0).image}"
                                             alt="new-prd" class="product-card-img w-100 h-auto cursor-pointer" onclick="viewProduct('${ps.productId}')"/>
                                    </div>
                                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                    </div>
                                    <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                                        <h6 class="text-truncate mb-3">${ps.productName}</h6>
                                        <div class="d-flex justify-content-center">
                                            <h6>${ps.price}$</h6><h6 class="text-muted ml-2"></h6>
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
            <!-- Products End -->
        </div>
    </div>
</body>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<script src="lib/easing/easing.min.js"></script>
<script src="lib/owlcarousel/owl.carousel.min.js"></script>

<!-- Contact Javascript File -->
<script src="mail/jqBootstrapValidation.min.js"></script>
<script src="mail/contact.js"></script>

<!-- Template Javascript -->
<script src="js/main.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/8d39de38b8.js" crossorigin="anonymous"></script>
<script>
                                                 function viewProduct(id) {
                                                     document.getElementById("frm-product-details-" + id).submit();
                                                 }
                                                 function submitTxtSearch() {
                                                      var searchInput = document.getElementById("yourSearchInputId"); // Replace "yourSearchInputId" with the actual ID of your input field
                                                 var searchText = searchInput.value.trim();

                                                 if (searchText === "") {
                                                     alert("Please enter a search term.");
                                                 }
                                                 else{
                                                 document.getElementById('frm-seachtxt').submit();
                                                 }
                                                                                }
                                                 function submitForm() {

                                                     document.getElementById("frm-sort").submit();
                                                 }
                                                 function setAndSubmitForm(value) {
                                                     if (document.getElementById('sortOptionValue').value === '-1') {
                                                         document.getElementById('sortOptionValue').value = '';
                                                     }
                                                     document.getElementById('sortOptionValue').value = value;
                                                     document.getElementById('frm-sort').submit();
                                                 }

</script>
</html>
