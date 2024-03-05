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

        <style>

            .subject_info{
                color: #3A7096;
            }
            .subject_info:hover{
                color: #71B6E5;
                text-decoration: underline;
            }
        </style>

        <script>
            function submitForm(suid) {
                var formid = 'subject_' + suid;
                var form = document.getElementById(formid);
                form.submit();
            }

        </script>
    </head>
    <body>
        <table border="1px">
            <tr>
                <td>Course</td>
            </tr>

            <c:forEach items="${requestScope.enrolls}" var="e">
                <tr>
                    <td>
                        <form id="subject_${e.group.subject.id}" action="score" method="get">
                            <span class="subject_info" onclick="submitForm('${e.group.subject.id}')">${e.group.subject.fullname} (${e.group.subject.name})</span>
                            <input type="hidden" name="suid" value="${e.group.subject.id}"/>
                            <input type="hidden" name="id" value="${param.id}"/>
                            <span>(${e.group.name}, from ${e.startDate} to ${e.endDate})</span>
                        </form>
                    </td>
                </tr>

            </c:forEach>
        </table>

        <table border="1px">
            <tr>
                <td>GRADE CATEGORY</td>
                <td>GRADE ITEM</td>
                <td>WEIGHT</td>
                <td>VALUE</td>
                <td>COMMENT</td>
            </tr>

            <c:set var="dontHasScore" value="false" />
            <c:set var="notpassScore" value="false"/>
            <c:forEach items="${requestScope.grades}" var="gr">
                <tr>
                    <td>
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
                        <c:if test="${gr.score lt 0}">
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
                <tr>
                    <td></td>
                    <td>Total</td>
                </tr>

            </c:forEach>

            <tr>
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
            <tr>
                <td></td>
                <td>STATUS</td>
                <td>

                    <c:choose>
                        <c:when test="${dontHasScore eq 'true'}">
                            STUDYING
                        </c:when>
                        <c:otherwise>
                            <c:if test="${requestScope.average ge 5 and notpassScore eq 'false'}">
                                PASSED
                            </c:if>
                            <c:if test="${(requestScope.average lt 5) or notpassScore eq 'true' }">
                                NOT PASSED
                            </c:if>
                        </c:otherwise>
                    </c:choose>

                </td>
            </tr>
        </table>
    </body>
</html>
