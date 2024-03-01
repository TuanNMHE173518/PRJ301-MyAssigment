<%-- 
    Document   : list
    Created on : Feb 18, 2024, 7:33:12 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border = "1px" >
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Gender</th>
                <th>Date</th>
                <th>Email</th>
                
            </tr>
            <c:forEach items="${requestScope.students}" var="s">
                <tr>
                    <td>${s.sid}</td>
                    <td>${s.sname}</td>
                    <td>${s.gender ? "male" : "female"}</td>
                    <td>${s.date}</td>
                    <td>${s.email}</td>
                    
                </tr>
            </c:forEach>
            
        </table>
        
        
    </body>
</html>
