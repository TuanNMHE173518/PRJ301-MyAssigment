<%-- 
    Document   : timetable
    Created on : Feb 28, 2024, 10:38:57 PM
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
                width: 150%;
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
                vertical-align: top;
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
                padding: 0px;
            }
            td {
                height: 25px;
            }

            select {
                padding: 2px;
            }

            a {
                text-decoration: none;
                color: #84A3E0;
                transition: color 0.2s;
            }

            a:hover {
                color: #4F6187;
                text-decoration: underline;
            }

            h5 {
                margin: 0;
            }

            .time_start{
                width: 75%;
                padding: 1px 0px;
                border-radius: 3px;
                text-align: center;
                background-color: #5CB85C;
                color: white;
            }
            .year{
                margin-left: 3px;
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

            footer{
                border-top: 1px solid black;

            }
            .contact{
                
                margin-left: 100px;
            }
            a.disabled {
                pointer-events: none;
                color: gray;
                text-decoration: none;
            }
        </style>
    </head>
    <body>
        <div>

            <div>
                <header class="text-center">
                    <div class="d-flex justify-content-center align-items-center">
                        <img src="../view/images/FPT_Education_logo.svg.png" alt="FPT Education Logo" height="40">
                        <h1 class="ml-3 mb-0">FPT University Academic Portal</h1>
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
                                <a class="nav-link home" href="../home">
                                    Home
                                </a>
                            </li>
                            <li class="nav-item d-flex justify-content-center align-items-center">
                                <c:forEach items="${sessionScope.roles}" var="role">
                                    <c:if test="${role.id eq 1}">

                                        <h6 class="nav_if">
                                            View Schedule
                                        </h6>
                                    </c:if>
                                    <c:if test="${role.id eq 2}">

                                        <h6 class="nav_if">
                                            View Score
                                        </h6>
                                    </c:if>
                                </c:forEach>
                            </li>


                        </ul>
                        <ul class="navbar-nav">
                            <li class="nav-item ml-auto align-self-center">
                                <label class="label_name">
                                    ${sessionScope.account.displayname}
                                </label> 
                            </li>
                            <li class="nav-item ml-auto">
                                <a class="nav-link" href="../logout">
                                    Logout
                                </a>  
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>

            <div>
                <h4 class="d-flex justify-content-center align-items-center">
                    Lecturer: ${sessionScope.account.username}
                </h4>
            </div>
            <div class="table_container">
                <table >

                    <tr class="row1">
                        <td>
                            <form action="timetable" method="get">
                                <input type="hidden" value="${param.id}" name="id"/>
                                <div class="year">
                                    Year:
                                    <select id="year" name="year" onchange="this.form.submit()">
                                        <c:forEach items="${requestScope.years}" var="y">
                                            <option value="${y}"
                                                    <c:if test="${y eq param.year}">
                                                        selected="selected"
                                                    </c:if>>${y}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </form>
                        </td>

                        <td class="td_week">
                            <label>
                                MON
                            </label>
                        </td>
                        <td class="td_week">
                            <label>
                                TUE
                            </label>
                        </td>
                        <td class="td_week">
                            <label>
                                WED
                            </label>
                        </td>
                        <td class="td_week">
                            <label>
                                THU
                            </label>
                        </td>
                        <td class="td_week">
                            <label>
                                FRI
                            </label>
                        </td>
                        <td class="td_week">
                            <label>
                                SAT
                            </label>
                        </td>
                        <td class="td_week">
                            <label>
                                SUN
                            </label>
                        </td>

                    </tr>

                    <tr class="row1">
                        <td>
                            <form action="timetable" method="get">
                                <input type="hidden" value="${param.id}" name="id"/>
                                <input type="hidden" value="${requestScope.year}" name="year"/>
                                <div class="year">
                                    Week:
                                    <select id="week" name="week" onchange="this.form.submit()" >
                                        <c:forEach items="${requestScope.weeks}" var="w">
                                            <option value="${w}"
                                                    <c:if test="${w eq param.week || (empty param.week and w eq requestScope.currentWeek)
                                                          }">
                                                        selected="selected"
                                                    </c:if>>
                                                ${w}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </form>
                        </td>
                        <c:forEach items="${requestScope.dates}" var="d">
                            <td class="td_week"> 
                                <label>
                                    ${d}
                                </label>
                            </td>

                        </c:forEach>
                    </tr>

                    <c:forEach items="${requestScope.timeslots}" var="slot">
                        <tr>
                            <td class="td_slot">
                                ${slot.name} 
                            </td>
                            <c:forEach items="${requestScope.dates}" var="d">
                                <td class="td_slot" >
                                    <c:set var="hasData" value="false"></c:set>
                                    <c:forEach items="${requestScope.lessions}" var="less">

                                        <c:if test="${d eq less.date and slot.id eq less.timeslot.id}">

                                            <c:set var="hasData" value="true"></c:set>
                                            <a class="${less.date > requestScope.currentDate ? 'disabled' : ''}"
                                               href="takeattend?lesid=${less.id}&group=${less.group.name}&isattend=${less.isAttend}"
                                               >
                                                
                                                ${less.group.name}<br/>
                                                -${less.group.subject.name}<br/>
                                                at ${less.room.number}<br/>
                                            </a>
                                            <c:if test="${less.isAttend}">
                                                <h6 style="color: #5CB85C">
                                                    (attended)
                                                </h6>
                                            </c:if>
                                            <c:if test="${!less.isAttend}">
                                                <h6 style="color: red">
                                                    (Not yet)
                                                </h6> 

                                                <h6 class="time_start">
                                                    (${less.timeslot.start}- ${less.timeslot.end})
                                                </h6>
                                            </c:if>

                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${not hasData}">-</c:if>            

                                    </td>

                            </c:forEach>
                        </tr>
                    </c:forEach>
                </table>
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
        </div>
    </body>
</html>
