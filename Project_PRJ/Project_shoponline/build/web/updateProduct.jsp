<%-- 
    Document   : updateProduct
    Created on : 12 thg 7, 2023, 02:34:08
    Author     : milvh
--%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>EShopper - Bootstrap Shop Template</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

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
        <jsp:include page="menu.jsp"></jsp:include>
            <!-- Page Header Start -->
            <div class="container-fluid bg-secondary mb-5">
                <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
                    <h1 class="font-weight-semi-bold text-uppercase mb-3">Update Product</h1>
                    <div class="d-inline-flex">
                        <p class="m-0"><a href="">Home</a></p>
                        <p class="m-0 px-2">-</p>
                        <p class="m-0">Manager Product</p>
                        <p class="m-0 px-2">-</p>
                        <p class="m-0">Update Product</p>
                    </div>
                </div>
            </div>
            <!-- Page Header End -->
            <!-- update form start-->

            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="update" method="get">
                        <div class="modal-header" style="justify-content: center">						
                            <h4 class="modal-title">Update Product</h4>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Id</label>
                                <input name="pid" type="number" class="form-control" min="0" required value="${p.pid}" readonly>
                        </div>
                        <div class="form-group">
                            <label>Name</label>
                            <input name="pname" type="text" class="form-control" value="${p.name}" required>
                        </div>
                        <div class="form-group">
                            <label>Price</label>
                            <input name="pprice" type="number" min="0" step="0.01" value="${p.price}" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Description</label>
                            <textarea name="pdescription" class="form-control" required>${p.description}</textarea>
                        </div>
                        <div class="form-group">
                            <label>Image</label>
                            <div class="custom-file">
                                <input name="pimage" type="text" id="actual-btn" accept="image/*" value="${p.image}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Quantity</label>
                            <input name="pquantity" type="number" class="form-control" min="0" value="${p.quantity}" required>
                        </div>
                        <div class="form-group">
                            <label>Category</label>
                            <select name="category" class="form-select" aria-label="Default select example">
                                <c:forEach items="${listC}" var="c">
                                    <option value="${c.id}" >${c.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input type="submit" class="btn btn-success" value="Update">
                    </div>
                </form>
            </div>
        </div>

        <!-- update form end-->
        <jsp:include page="footer.jsp"></jsp:include>
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
    </body>
</html>
