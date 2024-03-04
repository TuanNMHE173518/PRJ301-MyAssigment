<%-- 
    Document   : takeatt
    Created on : Mar 1, 2024, 10:21:54 PM
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
            .table_container {
                font-family: 'Arial', sans-serif;
                margin: 20px;
                display: flex;
                justify-content: center;
                align-items: center;

            }

            table {
                border-collapse: collapse;
                width: 85%;
            }

            .td_week {

                border-left: 2px solid white;
                border-right: 2px solid white;
                border-top: 2px solid white;
            }

            .td_week :first-child{
                margin-left: 3px;
            }

            .td_slot{
                border-bottom: 2px solid #ddd;
                border-top: 2px solid #ddd;
            }
            .row1{
                background-color: #6B90DA;
            }

            th, td {
                text-align: left;
            }

            tr.row1 td {
                padding: 0px; /* Giảm padding cho ô trong các dòng có class "row1" */
            }
            td {
                height: 25px;
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
            .label_name{
                margin-bottom: -8px;
                border: 1px solid gray;
                background-color: #ddd;
                border-radius:5px;
                padding-bottom: 4px;
                padding-left: 4px;
                padding-right: 4px;
            }
            .nav_if{
                margin-bottom: 0px;
                margin-left: 5px;
            }
            .home{
                border-right: 1px solid black;
            }
            .row_content{
                border-bottom: 2px solid #ddd;
            }
            footer{
                border-top: 1px solid black;

            }
            .contact{
                margin-top: 100px;
                margin-left: 100px;
            }
        </style>
    </head>
    <body>
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
                        <a class="nav-link home" href="../home">Home</a>
                    </li>
                    <li class="nav-item justify-content-center align-items-center d-flex">
                        <c:if test="${sessionScope.account.role eq 1}">

                            <h6 class="nav_if">View Attend</h6> 
                        </c:if>
                        <c:if test="${sessionScope.account.role eq 2}">

                            <h6 class="nav_if">View Score</h6>
                        </c:if>

                    </li>


                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item ml-auto align-self-center">
                        <label class="label_name">${sessionScope.account.displayname}</label> 
                    </li>
                    <li class="nav-item ml-auto">
                        <a class="nav-link" href="../logout">Logout</a>  
                    </li>
                </ul>
            </div>
        </nav>


        <div class="content_container">
            <form action="takeattend" method="post">
                <input type="hidden" name="lesid" value="${param.lesid}"/>
                <input type="hidden" name="lesattend" value="${param.isattend}"/>
                <div class="table_container">

                    <table >
                        <tr class="row1">

                            <td class="td_week"><label>NO</label></td>
                            <td class="td_week"><label>GROUP</label></td>
                            <td class="td_week"><label>CODE</label></td>
                            <td class="td_week"><label>NAME</label></td>
                            <td class="td_week"><label>STATUS</label></td>
                            <td class="td_week"><label>COMMENT</label></td>
                            <td class="td_week"><label>TAKER</label></td>
                            <td class="td_week"><label>RECORD TIME</label></td>

                        </tr>


                        <c:forEach items="${requestScope.atts}" var="a" varStatus="loop">

                            <tr class="row_content">
                                <td> ${loop.index + 1}</td>
                                <td>${requestScope.group}</td>
                                <td>${a.student.id}</td>
                                <td>${a.student.name}</td>
                                <td> 
                                    <input type="radio" ${a.ispresent?"checked=\"checked\"":""} value="attended" name="ispresent${a.student.id}"/>attended 
                                    <input type="radio" ${!a.ispresent?"checked=\"checked\"":""} value="absent" name="ispresent${a.student.id}"/>absent

                                </td>
                                <td><input type="text" value="${a.description}" name="description${a.student.id}"/></td>
                                <td>${sessionScope.account.displayname}</td>
                                <td>${a.datetime}</td>
                            </tr>

                        </c:forEach>    
                    </table>
                </div>
                <input type="submit" value="Save" class="btn btn-light btn-outline-secondary float-right mr-5">
            </form>
        </div>  
        <div class="contact">
            <p>
                Mọi góp ý, thắc mắc xin liên hệ: Phòng dịch vụ sinh viên: Email: dichvusinhvien@fe.edu.vn. 
            <h6>Điện thoại:(024)7308.13.13</h6>
        </p>
    </div>
    <footer class="bg-white text-black text-center py-3">
        <p>&copy; Powered by FPT University</p>
    </footer>
</body>
</html>
