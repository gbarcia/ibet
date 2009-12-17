<%-- 
    Document   : updatePago
    Created on : Dec 16, 2009, 8:01:47 PM
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
        <title>Ibet | Admin - Editar Medio Pago</title>
    </head>
    <body>
        <h1>Editar Medio Pago</h1>
        <form:errors path="*" cssClass="error"/>
        <table>
            <form:form commandName="editarMedioPago">
            <tr>
                <th><label for="nombreMedioPago">Nombre:</label></th>
                <td><form:input id="nombreMedioPago" path="nombre"/></td>
                <td><form:errors path="nombre" /></td>
            </tr>
        </table>
            <input type="submit" value="actualizar">
            </form:form>
            <a href="homeMedioPago.htm">Volver</a>
    </body>
</html>
