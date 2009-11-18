<%-- 
    Document   : registroConfirmado
    Created on : 18/11/2009, 12:20:53 AM
    Author     : Gerardo Barcia
--%>
<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${resultado == true}">
        <h1><spring:message code="${mensaje}" /> </h1>
        </c:if>
        <c:out value="${resultado}" />
    </body>
</html>
