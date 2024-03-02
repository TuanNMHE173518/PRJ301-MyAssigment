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
        </style>
    </head>
    <body>
        <div class="container">
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
                            
                            <tr>
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
                <input type="submit" value="Save"/>
            </form>
        </div>                
    </body>
</html>
