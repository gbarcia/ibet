<%-- 
    Document   : reportePerdidas
    Created on : Nov 24, 2009, 5:00:34 PM
    Author     : maya
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
        <form:form commandName="reportePerdidas" action="reportePerdidas.htm">
            <form:input path="fechaInicio" />
            <form:input path="fechaFin" />
            <form:select path="tipoReporte" items="${opcionTipoReporte}"/>
            <input type="submit">
        </form:form>
    </body>
</html>
