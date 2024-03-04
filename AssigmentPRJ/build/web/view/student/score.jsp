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

        <style>
            
            .subject_info{
                color: #3A7096;
            }
            .subject_info:hover{
                color: #71B6E5;
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <table border="1px">
            <tr>
                <td>Course</td>
            </tr>

            <c:forEach items="${requestScope.enrolls}" var="e">
                <tr>
                    <td>
                        <span class="subject_info">${e.group.subject.fullname} (${e.group.subject.name})</span>
                        <span>(${e.group.name}, from ${e.startDate} to ${e.endDate})</span>
                    </td>
                </tr>

            </c:forEach>
        </table>
    </body>
</html>
