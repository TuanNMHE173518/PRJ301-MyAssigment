<%-- 
    Document   : home
    Created on : Mar 1, 2024, 2:16:30 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <style>
            body {
                display: flex;
                flex-direction: column;
                min-height: 100vh;
            }
            label{
                margin-bottom: -8px;
                border: 1px solid gray;
                background-color: #ddd;
                border-radius:5px;
                padding-bottom: 4px;
                padding-left: 4px;
                padding-right: 4px;
            }
            main {
                flex: 1;
            }

            .home{
                border-right: 1px solid black;
            }
            nav.navbar {
                margin-bottom: 20px; /* Adjust the margin as needed */
            }

            /* Custom Styles for the Header */
            header {
                background-color: #FFA500; /* Dark background color */
                color: #ffffff; /* White text color */
                text-align: center;
                padding: 20px;
            }
            footer{
                border-top: 1px solid black;
            }
            .contact{
                margin-left: 100px;
            }
        </style>

    </head>
    <body>
        <!-- Header -->
        <header class="text-center">
            <div class="d-flex justify-content-center align-items-center">
                <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/ad/FPT_Education_logo.svg/2560px-FPT_Education_logo.svg.png" alt="FPT Education Logo" height="40">
                <h1 class="ml-3 mb-0">FPT University Academic Portal</h1>
            </div>
        </header>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light mb-4">

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link home" href="home">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <c:if test="${sessionScope.account.role eq 1}">

                            <a class="nav-link" href="lecture/timetable?id=${sessionScope.account.lecture.id}">View Schedule</a> 
                        </c:if>
                        <c:if test="${sessionScope.account.role eq 2}">

                            <a class="nav-link" href="student/score?id=">View Score</a> 
                        </c:if>

                    </li>


                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item ml-auto align-self-center">
                        <label>${sessionScope.account.displayname}</label> 
                    </li>
                    <li class="nav-item ml-auto">
                        <a class="nav-link" href="logout">Logout</a>  
                    </li>
                </ul>
            </div>
        </nav>

        <!-- Content -->
        <main class="container mt-3">
            <h1>CẢNH BÁO</h1>
            <!-- Your page content goes here -->
            <p>Hiện nay có hình thức lừa đảo sửa dữ liệu điểm danh để chiếm đoạt tiền của sinh viên.
                Các đối tượng có thể giả mạo là giảng viên, cán bộ Nhà trường để gửi thông tin cho một số sinh viên.
                Vì vậy, sinh viên tuyệt đối không tin và thực hiện hành vi vi phạm nhằm tránh mất tiền, bị xử lý kỷ luật theo nội quy Nhà trường và có thể bị xử lý hình sự do đây là hành vi vi phạm pháp luật theo Luật an ninh mạng 2018.<br/>
                Đồng thời, ngay khi nhận được các thông tin với nội dung lôi kéo, lừa đảo vui lòng gửi thông tin tới phòng Dịch vụ sinh viên tại các cơ sở.
                Trân trọng thông báo,<br/>
                TRƯỜNG ĐẠI HỌC FPT
            </p>


        </main>


        <div class="contact">
            <p>
                Mọi góp ý, thắc mắc xin liên hệ: Phòng dịch vụ sinh viên: Email: dichvusinhvien@fe.edu.vn. 
            <h6>Điện thoại:(024)7308.13.13</h6>
        </p>
    </div>
    <footer class="bg-white text-black text-center py-3 mt-auto">
        <p>&copy; Powered by FPT University</p>
    </footer>



    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
