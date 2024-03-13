<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>E Shopper</title>
        <link rel="stylesheet" href="style.css" type="text/css" />
        <link rel="stylesheet" href="../css/style.css" type="text/css" />
        <link rel="stylesheet" href="../css/layout.css" type="text/css" />
        <link rel="stylesheet" href="../css/login.css" type="text/css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
              crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/8d39de38b8.js" crossorigin="anonymous"></script>
    </head>

    <body>
        <%@ include file="HeaderUser.jsp" %>
        <!-- Login wrapper -->
        <div class="d-flex justify-content-center mt-5 mb-5">
            <div class="login-wrapper shadow bg-body rounded">

                <div class="nav group-login-wrapper mb-2 row" id="accountTab" role="tablist">
                    <div class="col-6">
                        <button class="btn-tab-login active w-100" id="pills-login-tab" data-bs-toggle="pill"
                                data-bs-target="#pills-login" type="button" role="tab" aria-controls="pills-login"
                                aria-selected="true">Đăng nhập ${sessionScope.carts.cartId}</button>
                    </div>
                    <div class="col-6">
                        <form action="register" method="get">
                            <button class="btn-tab-login w-100"
                                    type="submit">Đăng kí</button>

                        </form>
                    </div>
                </div>
                <div class="tab-content" id="accountTabContent">
                    <!--Login form-->
                    <div class="tab-pane fade show active body-login-wrapper" id="pills-login" role="tabpanel"
                         aria-labelledby="pills-login-tab">
                        <form action="login" method="post">
                            <c:if test="${msg != null}">
                                <p style="color: red">${msg}</p>
                            </c:if>
                            <h6>EMAIL <span class="text-danger">*</span></h6>
                            <input type="text" class="form-control mb-3" name="email" required
                                   placeholder="Nhập địa chỉ Email" aria-label="Username">
                            <h6>MẬT KHẨU <span class="text-danger">*</span></h6>
                            <input type="password" class="form-control mb-2" name="pwd" required
                                   placeholder="Nhập Mật khẩu" aria-label="Username">
                            <div class="mb-3">
                                <a href="forgotPassword" class="forget-pass-btn">
                                    Quên mật khẩu?
                                </a>
                            </div>
                            <button type="submit" class="btn btn-dark w-100 mb-2">Đăng nhập</button>
                            <div class="text-center commit-meeko mb-3">
                                EShopper cam kết bảo mật và sẽ không bao giờ đăng<br />hay chia sẻ thông tin mà chưa có
                                được sự
                                đồng ý của bạn.
                            </div>
                           
                        </form>
                       
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="./Footer.jsp" %>
        <!--Script go to top, copy to every single page-->
        
    </body>

</html>