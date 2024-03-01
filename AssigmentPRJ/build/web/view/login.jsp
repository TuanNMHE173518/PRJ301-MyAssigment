<%-- 
    Document   : login
    Created on : Feb 27, 2024, 9:28:53 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <style>


            .alert {
                margin-top: 10px;
                text-align: center;
            }
            .login-wrap {
                background-color: #FFA500;
                border: 2px solid #FF8C00; /* Màu xanh lam */
                border-radius: 10px; /* Bo tròn đường viền */
                padding: 20px; /* Khoảng cách giữa đường viền và nội dung */
            }
        </style>


    </head>
    <body>
        <section class="ftco-section">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-6 text-center mb-5">
                        <h2 class="heading-section">Login</h2>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-md-6 col-lg-5">
                        <div class="login-wrap p-4 p-md-5">
                            <div class="icon d-flex align-items-center justify-content-center">
                                <span class="fa fa-user-o fa-3x"></span>
                            </div>
                            <h3 class="text-center mb-4">FPT Education</h3>
                            <form action="login" class="login-form" method="post">
                                <div class="form-group">
                                    <input type="text" class="form-control rounded-left" placeholder="Username" required name="username">
                                </div>
                                <div class="form-group d-flex">
                                    <input type="password" class="form-control rounded-left" placeholder="Password" required name="password">
                                </div>
                                <div class="form-group">
                                    <c:choose>
                                        <c:when test="${not empty sessionScope.loginError}">
                                            <div class="alert alert-danger mt-2" role="alert">
                                                Incorrect username or password. Please try again.
                                            </div>
                                            <c:remove var="loginError" scope="session" />
                                        </c:when>
                                    </c:choose>
                                </div>
                                <div class="form-group d-md-flex">
                                    <div class="w-50">
                                        <label class="checkbox-wrap checkbox-primary">Remember Me
                                            <input type="checkbox" checked name="remember">
                                            <span class="checkmark"></span>
                                        </label>
                                    </div>

                                </div>
                                <div class="form-group d-flex justify-content-center align-items-center">
                                    <button type="submit" style="background-color: #FFFFFF; color: black;" class="btn btn-primary  rounded submit p-3 px-5">Get Started</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!--        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>-->
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <!--        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>-->


    </body>
</html>
