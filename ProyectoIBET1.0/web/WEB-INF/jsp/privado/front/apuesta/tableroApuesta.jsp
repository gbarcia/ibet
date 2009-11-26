<%-- 
    Document   : tableroApuesta
    Created on : 26/11/2009, 04:48:24 PM
    Author     : nath
--%>

<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TableroApuesta</title>
    </head>
    <body>
        <form:form commandName="apuesta" action="tableroApuesta.htm">
            <form:input path="nombreEvento" />
            <form:input path="montoApuesta" />
            <form:input path="fechaEvento" />
            <form:select path="nombreMetodoPago" items="${opcionMetodosPago}" />
        </form:form>
    </body>
</html>
