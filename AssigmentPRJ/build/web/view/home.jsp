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
                margin-bottom: 20px;
            }


            header {
                background-color: #FFA500;
                color: #ffffff;
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
        
            <div>
                <header class="text-center">
                    <div class="d-flex justify-content-center align-items-center">
                        <div>
                            <img src="view/images/FPT_Education_logo.svg.png" alt="FPT Education Logo" height="40">
                        </div>
                        <div>
                            <h1 class="ml-3 mb-0">FPT University Academic Portal</h1>
                        </div>
                    </div>
                </header>
            </div>

            <div>
                <nav class="navbar navbar-expand-lg navbar-light bg-light mb-4">
                    <div>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                    </div>

                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item active">
                                <a class="nav-link home" href="home">Home <span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item">
                                <c:forEach items="${sessionScope.roles}" var="role">
                                    <c:if test="${role.id eq 1}">

                                        <a class="nav-link" href="lecture/timetable?id=${sessionScope.account.lecture.id}">View Schedule</a> 
                                    </c:if>
                                    <c:if test="${role.id eq 2}">

                                        <a class="nav-link" href="student/score?id=${sessionScope.account.student.id}">View Score</a> 
                                    </c:if>
                                </c:forEach>
                            </li>
                            <li class="nav-item">
                                <c:forEach items="${sessionScope.roles}" var="role">

                                    <c:if test="${role.id eq 2}">

                                        <a class="nav-link" href="student/attend?id=${sessionScope.account.student.id}">View Attend</a> 
                                    </c:if>
                                </c:forEach>
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
            </div>
            <div class="container mt-3">
                <main>
                    <div>
                        <h1>CẢNH BÁO</h1>
                    </div>
                    <div>
                        <p>Hiện nay có hình thức lừa đảo sửa dữ liệu điểm danh để chiếm đoạt tiền của sinh viên.
                            Các đối tượng có thể giả mạo là giảng viên, cán bộ Nhà trường để gửi thông tin cho một số sinh viên.
                            Vì vậy, sinh viên tuyệt đối không tin và thực hiện hành vi vi phạm nhằm tránh mất tiền, bị xử lý kỷ luật theo nội quy Nhà trường và có thể bị xử lý hình sự do đây là hành vi vi phạm pháp luật theo Luật an ninh mạng 2018.<br/>
                            Đồng thời, ngay khi nhận được các thông tin với nội dung lôi kéo, lừa đảo vui lòng gửi thông tin tới phòng Dịch vụ sinh viên tại các cơ sở.
                            Trân trọng thông báo,<br/>
                            TRƯỜNG ĐẠI HỌC FPT
                        </p>
                    </div>
                </main>
            </div>

            <div class="contact mt-auto">
                <p>
                    Mọi góp ý, thắc mắc xin liên hệ: Phòng dịch vụ sinh viên: Email: dichvusinhvien@fe.edu.vn. 
                </p>
                <h6>Điện thoại:(024)7308.13.13</h6>
            </div>


            <div class="bg-white text-black text-center py-3">
                <footer >
                    <p>&copy; Powered by FPT University</p>
                </footer>
            </div>


        
    </body>
</html>
