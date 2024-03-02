<%-- 
    Document   : timetable
    Created on : Feb 28, 2024, 10:38:57 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

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
                width: 80%;
                padding: 1px 0px;
                border-radius: 3px;
                text-align: center;
                background-color: #5CB85C;
                color: white;
            }
          

           

            .year{
                margin-left: 3px;
            }
        </style>
    </head>
    <body>



        <div class="container">
            Lecture: ${sessionScope.account.username}
            <div class="table_container">
                <table >
                    <tr class="row1">
                        <td >
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

                        <td class="td_week"><label>MON</label></td>
                        <td class="td_week"><label>TUE</label></td>
                        <td class="td_week"><label>WED</label></td>
                        <td class="td_week"><label>THU</label></td>
                        <td class="td_week"><label>FRI</label></td>
                        <td class="td_week"><label>SAT</label></td>
                        <td class="td_week"><label>SUN</label></td>

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
                                                    </c:if>>${w}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </form>
                        </td>
                        <c:forEach items="${requestScope.dates}" var="d">
                            <td  class="td_week"> <label>${d}</label></td>

                        </c:forEach>
                    </tr>

                    <c:forEach items="${requestScope.timeslots}" var="slot">
                        <tr>
                            <td class="td_slot">
                                ${slot.name} 
                            </td>
                            <c:forEach items="${requestScope.dates}" var="d">
                                <td class="td_slot">
                                    <c:set var="hasData" value="false"></c:set>
                                    <c:forEach items="${requestScope.lessions}" var="less">
                                        
                                        <c:if test="${d eq less.date and slot.id eq less.timeslot.id}">
                                            <c:set var="hasData" value="true"></c:set>
                                            <a href="takeattend?lesid=${less.id}&group=${less.group.name}&isattend=${less.isAttend}"> ${less.group.name}<br/>
                                                -${less.group.subject.name}<br/>
                                                at ${less.room.number}<br/>
                                            </a>
                                            <c:if test="${less.isAttend}">
                                                <h5 style="color: #5CB85C"> (attended)</h5>
                                            </c:if>
                                            <c:if test="${!less.isAttend}">
                                                <h5 style="color: red"> (Not yet)</h5> 
                                                
                                                <h5 class="time_start">(${less.timeslot.start}- ${less.timeslot.end})</h5>
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

        </div>                

    </body>
</html>
