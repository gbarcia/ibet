<%-- 
    Document   : agregarPago
    Created on : Dec 16, 2009, 7:15:17 PM
    Author     : maya
--%>

<%@page session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ibet | Admin - Agregar Medio Pago</title>
    </head>
    <body>
        <h1>Agregar Medio Pago</h1>
        <form:errors path="*" cssClass="error"/>
        <table>
            <form:form commandName="agregarMedioPago">
            <tr>
                <th><label for="nombreMedioPago">Nombre:</label></th>
                <td><form:input id="nombreMedioPago" path="nombre"/></td>
                <td><form:errors path="nombre" /></td>
            </tr>
        </table>
            <input type="submit" value="registrar">
            </form:form>
            <a href="homeMedioPago.htm">Volver</a>
    </body>
</html>
