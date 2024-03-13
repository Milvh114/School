<%-- 
    Document   : UserProfile
    Created on : Jun 13, 2023, 11:54:34 AM
    Author     : dell
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <!--<link href="{pageContext.request.contextPath}/css/account.css" rel="stylesheet" type="text/css"/>-->
        <!--<link href="{pageContext.request.contextPath}/css/layout.css" rel="stylesheet" type="text/css"/>-->
        <script src="script.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <script src="../js/script.js"></script>
        <script src="https://kit.fontawesome.com/8d39de38b8.js" crossorigin="anonymous"></script>
        <style>
            th{
                width: 100px;
            }
        </style>
    </head>
    <body>
        <%@ include file="../HeaderUser.jsp" %>
        <!-- display message here -->
        <div id="snackbar"></div>
        <c:if test="${msg != null}">
            <script>
                var x = document.getElementById("snackbar");
                x.textContent = '${msg}';
                x.className = "show ${color}";
                setTimeout(function () {
                    x.className = x.className.replace("show", "");
                }, 3000);
            </script>
        </c:if>
        <div class="row container-home"
             style="padding-left: 1%;padding-right: 64px; padding-top: 60px; padding-bottom: 60px; width: 100%;">
            <div class="col-lg-2 px-2">
                <!-- Left section -->
                <div class="nav group__left__account mb-3" style="display: block;" role="tablist">
                    <h4 style="font-weight: 200;">
                        TRANG TÀI KHOẢN
                    </h4>
                    <h6 style="font-weight: bold;">Xin chào, ${sessionScope.account.fullName}!</h6>
                    <div class="mt-4 mb-2 active left__account" style="font-size: 17px;" data-bs-toggle="pill"
                         data-bs-target="#pills-accounts" type="button" role="tab">
                        Thông tin tài khoản
                    </div>
                    <div class="mb-2 left__account" style="font-size: 17px;" data-bs-toggle="pill"
                         data-bs-target="#pills-password" type="button" role="tab">
                        Đổi mật khẩu
                    </div>
                </div>
            </div>
            <div class="col-lg-10 px-2">
                <!-- Right Section  -->
                <div class="tab-content mb-3">
                    <!-- This section is use for account tab  -->
                    <section class="tab-pane fade show active" id="pills-accounts">
                        <h3 style="font-weight: 300;">
                            TRANG TÀI KHOẢN
                        </h3>
                        <p class="mt-4"><span style="font-weight: bold;">Họ tên: </span>${sessionScope.account.fullName}</p>
                        <p><span style="font-weight: bold;">Email:</span> ${sessionScope.account.email}</p>
                        <p><span style="font-weight: bold;">Phone:</span> ${sessionScope.account.phone}</p>
                        <p><span style="font-weight: bold;">Address:</span> ${sessionScope.account.address}</p>
                    </section>


                    <!-- This section is use for change password  -->
                    <section class="tab-pane fade show" id="pills-password">
                        <h3 style="font-weight: 300;">
                            ĐỔI MẬT KHẨU
                        </h3>
                        <c:if test="${sessionScope.account != null}">
                            <p class="mt-4">Để đảm bảo tính bảo mật vui lòng đặt mật khẩu với ít nhất 8 kí tự và 1 ký tự đặc biệt
                            </p>
                            <form id="frm-changePass" action="changePassword" method="post" class="mt-2">
                                <h6>MẬT KHẨU <span class="text-danger">*</span></h6>
                                <input id="input-oldPwd" name="oldPwd" type="password" class="form-control mb-2" placeholder="Mật khẩu cũ"
                                       aria-label="Username" required>
                                <h6 class="mt-3">MẬT KHẨU MỚI <span class="text-danger">*</span></h6>
                                <input id="input-newPwd" type="password" class="form-control mb-2" placeholder="Mật khẩu mới" name="newPwd"
                                       aria-label="Username" required oninput="validatePwd(this)"/>
                                <span style="color: red;display: none" id="error-newPwd"></span>
                                <h6 class="mt-3">XÁC NHẬN LẠI MẬT KHẨU <span class="text-danger">*</span></h6>
                                <input id="input-cfPwd" type="password" class="form-control mb-2" placeholder="Xác nhận lại mật khẩu"
                                       aria-label="Username" required oninput="validateCfPwd(this)"/>
                                <span style="color: red;display: none" id="error-cfPwd"></span>
                                <button type="button" onclick="changePass()" class="btn btn-dark mt-4">Đặt lại mật khẩu</button>
                            </form>
                        </c:if>

                    </section>
                    <section class="tab-pane fade show" id="order_list">
                        <h3 style="font-weight: 300;">
                            ĐỔI MẬT KHẨU Nè
                        </h3>


                    </section>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
        <script src="https://kit.fontawesome.com/8d39de38b8.js" crossorigin="anonymous"></script>
    </body>
    <%@ include file="../Footer.jsp" %>

    <script>
                                    // Get the modal
                                    var modal = document.getElementById("myModal");

                                    // Get the button that opens the modal
                                    var btn = document.getElementById("myBtn");

                                    // Get the <span> element that closes the modal
                                    var span = document.getElementsByClassName("close")[0];

                                    // When the user clicks the button, open the modal 
                                    btn.onclick = function () {
                                        modal.style.display = "block";
                                    };

                                    // When the user clicks on <span> (x), close the modal
                                    span.onclick = function () {
                                        modal.style.display = "none";
                                    };

                                    // When the user clicks anywhere outside of the modal, close it
                                    window.onclick = function (event) {
                                        if (event.target === modal) {
                                            modal.style.display = "none";
                                        }
                                    };

                                    var checkNewPwd = false;
                                    var checkCfPass = false;

                                    function changePass() {
                                        var oldPwd = document.getElementById('input-oldPwd').value;
                                        var newPwd = document.getElementById('input-newPwd').value;
                                        var cfPwd = document.getElementById('input-cfPwd').value;
                                        if (oldPwd === '' && newPwd === '' && cfPwd === '') {
                                            alert('Vui lòng kiểm tra lại thông tin');
                                        } else {
                                            if (checkNewPwd && checkCfPass) {
                                                document.getElementById('frm-changePass').submit();
                                            } else {
                                                alert('Vui lòng kiểm tra lại thông tin');
                                            }

                                        }
                                    }

                                    function validatePwd(input) {
                                        var value = input.value;
                                        var regex = /^(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{8,}$/;

                                        if (!regex.test(value)) {
                                            document.getElementById('error-newPwd').textContent = "Mật khẩu phải chứa ít nhất 1 ký tự đặc biệt và có độ dài tối thiểu là 8 ký tự";
                                            document.getElementById('error-newPwd').style.display = "block";
                                            checkNewPwd = false;
                                        } else {
                                            document.getElementById('error-newPwd').textContent = "";
                                            document.getElementById('error-newPwd').style.display = "none";
                                            checkNewPwd = true;
                                        }
                                    }

                                    function validateCfPwd(input) {
                                        var newPwd = document.getElementById('input-newPwd').value;
                                        var value = input.value;
                                        if (value !== newPwd) {
                                            document.getElementById('error-cfPwd').textContent = "Mật khẩu xác nhận không giống mật khẩu mới";
                                            document.getElementById('error-cfPwd').style.display = "block";
                                            checkCfPass = false;
                                        } else {
                                            document.getElementById('error-cfPwd').textContent = "";
                                            document.getElementById('error-cfPwd').style.display = "none";
                                            checkCfPass = true;
                                        }
                                    }

    </script>

</html>