<%-- 
    Document   : cart
    Created on : 6 thg 7, 2023, 02:59:35
    Author     : milvh
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

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
            <h1 class="font-weight-semi-bold text-uppercase mb-3">Manager Product</h1>
            <div class="d-inline-flex">
                <p class="m-0"><a href="">Home</a></p>
                <p class="m-0 px-2">-</p>
                <p class="m-0">Manager Product</p>
            </div>
        </div>
    </div>
    <!-- Page Header End -->
    
    <!-- Add new product start-->
    <div class="fill-button" style="display: flex; justify-content: center;" >
        <div  class="col-lg-3 col-6 text-center ">
            <a href="#addNewProduct" class="btn border raw-border" data-toggle="modal">
                <i class="fas fa-plus text-primary"></i>
                <span class="badge"> ADD MORE PRODUCTS</span>
            </a>
        </div>
        
        <div id="addNewProduct" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="add" method="get">
                        <div class="modal-header">						
                            <h4 class="modal-title">Add Product</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Id</label>
                                <input name="pid" type="number" class="form-control" min="0">
                            </div>
                            <div class="form-group">
                                <label>Name</label>
                                <input name="pname" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <input name="pprice" type="number" min="0" step="0.01" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Description</label>
                                <textarea name="pdescription" class="form-control" required></textarea>
                            </div>
                            <div class="form-group">
                                <label>Image</label>
                                <div class="custom-file">
                                    <input name="pimage" type="text" id="actual-btn" accept="image/*"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Quantity</label>
                                <input name="pquantity" type="number" class="form-control" min="0" required>
                            </div>
                            <div class="form-group">
                                <label>Category</label>
                                <select name="category" class="form-select" aria-label="Default select example">
                                <c:forEach items="${listC}" var="c">
                                        <option ${c.id}>${c.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Add">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
    </div>
    <!-- Add new product end -->
    
    <!-- Cart Start -->
    <div class="container-fluid pt-5">
        
                <table class="table table-bordered text-center mb-0">
                    <thead class="bg-secondary text-dark">
                        <tr>
                            <th>STT</th>
                            <th>Id</th>
                            <th>Products</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Update</th>
                            <th>Remove</th>
                        </tr>
                    </thead>
                    <tbody class="align-middle"">
                        <c:forEach items="${list}" var="l">
                            <tr>
                                <td class="align-middle"></td>
                                <td class="align-middle">${l.pid}</td>
                                <td class="align-middle"><img src="${l.image}" alt="" style="width: 50px;"> ${l.name}</td>
                                <td class="align-middle">$${l.price}</td>
                                <td class="align-middle">${l.quantity}</td>
                                <td class="align-middle">
                                    <a href="load?pid=${l.pid}">
                                        <button class="btn btn-sm btn-success"><i class="fa fa-edit"></i></button>
                                    </a>
                                    
                                </td>
                                <td class="align-middle"><a href="delete?catID=${l.pid}"><button class="btn btn-sm btn-danger"><i class="fa fa-trash"></i></button></a></td>
                            </tr>
                        </c:forEach>    
                        
                    </tbody>
                </table>
        
    </div>
    <!-- Cart End -->

    
    
    <jsp:include page="footer.jsp"></jsp:include>


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
</body>

</html>
