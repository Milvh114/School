<%-- 
    Document   : login
    Created on : 9 thg 7, 2023, 06:41:50
    Author     : milvh
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
    <head>
        <meta charset="UTF-8">
        <title>CodePen - Animated Form</title>
    <head>
        <meta charset="UTF-8">
        <title>AnimaForm</title>
        <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
    </head><link rel="stylesheet" href="./css/login.css">

</head>
<body>
    <!-- partial:index.partial.html -->
    <section class="forms-section">
        <h1 class="section-title">WELCOME TO MY FRIEND!</h1>
        <div class="forms">

            <!--Login-->
            <div class="form-wrapper is-active">
                <button type="button" class="switcher switcher-login">
                    Login
                    <span class="underline"></span>
                </button>
                
                <form action="login" method="post" class="form form-login">
                    <fieldset>
                        <div class="input-block">
                            <label for="login-email">User name</label>
                            <input name="txtUser" id="login-email" type="txt" value="${userC}" required>
                        </div>
                        <div class="input-block">
                            <label for="login-password">Password</label>
                            <input name="txtPass" id="login-password" type="password" value="${passC}" required>
                        </div>
                        <div class="form-group form-check">
                            <input name="remember" value="1" type="checkbox" class="form-check-input" id="exampleCheck1">
                            <label class="form-check-label" for="excampleCheck1">Remenber me</label>
                        </div>
                    </fieldset>
                    <button type="submit" class="btn-login">Login</button>
                </form>
            </div>
            

            <!--Sign up-->
            <div class="form-wrapper">
                <button type="button" class="switcher switcher-signup" enabled="yes">
                    Sign Up
                    <span class="underline"></span>
                </button>
                <form action="signup" method="post" class="form form-signup">
                    <fieldset>
                        <legend>Please, enter your email, password and password confirmation for sign up.</legend>
                        <div class="input-block">
                            <label for="signup-email">User name</label>
                            <input name="txtUser" id="signup-email" type="txt" required>
                        </div>
                        <div class="input-block">
                            <label for="signup-password">Password</label>
                            <input name="txtPass" id="signup-password" type="password" required>
                        </div>
                        <div class="input-block">
                            <label for="signup-password-confirm">Confirm password</label>
                            <input name="txtRePass" id="signup-password-confirm" type="password" required>
                        </div>
                    </fieldset>
                    <button type="submit" class="btn-signup">Continue</button>
                </form>
            </div>

        </div>
        <div class="alert alert-danger" role="alert" style="background-color: #e83e8c; color: white">
            ${mess} 
        </div>
    </section>
    <!-- partial -->
    <script  src="./js/script.js"></script>

</body>
</html>
