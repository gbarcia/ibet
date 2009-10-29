<%-- 
    Document   : perfilUsuario
    Created on : Oct 29, 2009, 4:35:55 PM
    Author     : maya
--%>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>
            <spring:message code="perfilUsuario.titulo.form"/>
        </h2>
        <form:form method="POST" action="perfilUsuario.htm" commandName="perfilUsuario">
            <spring:message code="perfilUsuario.forms.username"/>
            <form:input path="username"/>
        </form:form>
    </body>
</html>
