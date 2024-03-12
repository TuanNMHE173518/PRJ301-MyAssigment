<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 
    Document   : score
    Created on : Mar 4, 2024, 10:50:44 PM
    Author     : ADMIN
--%>

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
                margin: 0;
            }

            td {
                padding: 0px 3px;
                
            }
            .table_container {
                flex-grow: 1;
                font-family: 'Arial', sans-serif;
                margin: 20px;
                display: flex;
                justify-content: center;
                align-items: center;
            }
            #table_score .row1 td{
                border-left: 2px solid white;
                border-right: 2px solid white;
            }
            .row_content td{
                border-bottom: 2px solid #ddd;
            }
            .row1{
                background-color: #6B90DA;
            }

            .average{
                border-top: 5px solid #E9F7F6;
                font-weight: bold;

            }

            .status{
                font-weight: bold;
            }
            .subject_info{
                color: #3A7096;
            }
            .subject_info:hover{
                color: #71B6E5;
                text-decoration: underline;
            }
            .home{
                border-right: 1px solid black;
            }
            nav.navbar {
                margin-bottom: 20px; /* Adjust the margin as needed */
            }


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
            .contact {
                margin-top: auto;
                margin-left: 100px;
            }

            footer {
                border-top: 1px solid black;
                margin-top: auto;
                padding: 20px;
            }
            #table_score{
                display: none;
            }

            .grade_catgory{
                border: none;
            }

            .status-studying {
                color: green;
            }


            .status-passed {
                color: green;
            }


            .status-not-passed {
                color: red;
            }
        </style>

        <script>

            document.addEventListener('DOMContentLoaded', function () {
                var tableScore = document.getElementById('table_score');


                if (sessionStorage.getItem('formSubmitted')) {

                    tableScore.style.display = 'flex';
                    var divsubject = document.getElementById(sessionStorage.getItem('suid'));
                    divsubject.style.color = 'black';
                    divsubject.style.fontWeight = 'bold';

                    sessionStorage.removeItem('formSubmitted');
                }

                var spans = document.getElementsByClassName('subject_info');
                for (var i = 0; i < spans.length; i++) {
                    spans[i].addEventListener('click', function () {
                        var suid = this.getAttribute('data-suid');
                        submitForm(suid);
                    });
                }
            });

            function submitForm(suid) {
                var formid = 'subject_' + suid;
                var form = document.getElementById(formid);


                sessionStorage.setItem('formSubmitted', 'true');
                sessionStorage.setItem('suid', suid);
                form.submit();
            }

        </script>
    </head>
    <body>
        <header class="text-center">
            <div class="d-flex justify-content-center align-items-center">
                <div>
                    <img src="../view/images/FPT_Education_logo.svg.png" alt="FPT Education Logo" height="40">
                </div>
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
                        <a class="nav-link home" href="../home">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item justify-content-center align-items-center d-flex">
                        <c:forEach items="${sessionScope.roles}" var="role">
                            <c:if test="${role.id eq 1}">

                                <h6 class="nav_if">View Attend</h6> 
                            </c:if>
                            <c:if test="${role.id eq 2}">

                                <h6 class="nav_if">View Score</h6>
                            </c:if>
                        </c:forEach>
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


        <div>
            <h1 class="ml-5">
                Grade report for ${requestScope.enrolls[0].student.name} (${requestScope.enrolls[0].student.id})
            </h1>

            <h3 class="text-center">Select a course to see report</h3>
        </div>

        <div class="table_container">
            <table border="1px" >
                <tr class="row1">
                    <td>Course</td>
                </tr>

                <c:forEach items="${requestScope.enrolls}" var="e">
                    <tr>
                        <td>
                            <form id="subject_${e.group.subject.id}" action="score" method="get">
                                <div id="${e.group.subject.id}">
                                    <span  class="subject_info" data-suid="${e.group.subject.id}">${e.group.subject.fullname} (${e.group.subject.name})</span>
                                    <input type="hidden" name="suid" value="${e.group.subject.id}"/>
                                    <input type="hidden" name="id" value="${param.id}"/>
                                    <span>(${e.group.name}, from ${e.startDate} to ${e.endDate})</span>
                                </div>
                            </form>
                        </td>
                    </tr>

                </c:forEach>
            </table>
        </div>


        <div id="table_score" class="table_container">
            <table >
                <tr class="row1">
                    <td>GRADE CATEGORY</td>
                    <td>GRADE ITEM</td>
                    <td>WEIGHT</td>
                    <td>VALUE</td>
                    <td>COMMENT</td>
                </tr>

                <c:set var="dontHasScore" value="false" />
                <c:set var="notpassScore" value="false"/>
                <c:forEach items="${requestScope.grades}" var="gr">
                    <tr class="row_content">
                        <td style="border: none">
                            ${gr.exam.assessment.name}
                        </td>
                        <td>
                            ${gr.exam.assessment.name}
                        </td>
                        <td>
                            <fmt:formatNumber value="${gr.exam.assessment.weight * 100}" pattern="0.0" /> %
                        </td>
                        <td>
                            <c:if test="${gr.score ge 0}">
                                ${gr.score}
                            </c:if>
                            <c:if test="${gr.score lt 0 and (gr.exam.assessment.name ne 'Final Exam Resit')}">
                                <c:set var="dontHasScore" value="true" />
                            </c:if>
                            <c:if test="${gr.score eq 0}">
                                <c:set var="notpassScore" value="true" />
                            </c:if>

                            <c:if test="${gr.score lt 4 and gr.exam.assessment.name eq 'Final Exam'}">
                                <c:set var="notpassScore" value="true" />
                            </c:if>
                        </td>
                        <td>
                            ${gr.comment}
                        </td>
                    </tr>
                    <tr class="row_content">
                        <td class="empty-cell"></td>
                        <td>Total</td>
                        <td><fmt:formatNumber value="${gr.exam.assessment.weight * 100}" pattern="0.0" /> %</td>
                        <td><c:if test="${gr.score ge 0}">
                                ${gr.score}
                            </c:if></td>
                        <td></td>
                    </tr>

                </c:forEach>

                <tr class="average" >
                    <td>COURSE TOTAL</td>
                    <td>
                        AVERAGE 
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${dontHasScore eq 'true'}">
                                0.0
                            </c:when>
                            <c:otherwise>
                                ${requestScope.average}
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr class="status">
                    <td></td>
                    <td>STATUS</td>
                    <td class="status-cell">
                        <c:choose>
                            <c:when test="${dontHasScore eq 'true'}">
                                <span class="status-studying">STUDYING</span>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${requestScope.average ge 5 and notpassScore eq 'false'}">
                                    <span class="status-passed">PASSED</span>
                                </c:if>
                                <c:if test="${(requestScope.average lt 5) or notpassScore eq 'true' }">
                                    <span class="status-not-passed">NOT PASSED</span>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </table>
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
