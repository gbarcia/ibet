<%--
    Document   : agregarCategoria
    Created on : 11/12/2009, 04:04:36 PM
    Author     : Gerardo Barcia
--%>

<%@page session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ibet | Admin - Actualizar Categoria</title>
    </head>
    <body>
        <h1>Actualizar Categoria</h1>
        <form:errors path="*" cssClass="error"/>
        <table>
            <form:form commandName="updateCategoria">
            <tr>
                <th><label for="nombreCategoria">Nombre:</label></th>
                <td><form:input id="nombreCategoria" path="nombreCategoria"/></td>
                <td><form:errors path="nombreCategoria" /></td>
            </tr>
            <tr>
                <th><label for="jerarquia">Jerarquia:</label></th>
                <td><form:select id="jerarquia" path="jerarquia" items="${opcionJerarquia}" /></td>
            </tr>
            <tr>
                <th><label for="empate">Empate:</label></th>
                <td><form:radiobuttons id="empate" path="empate" items="${opcionBooleana}"/></td>
            </tr>
            <tr>
                <th><label for="logicaAutomatica">Logica Automatica:</label></th>
                <td><form:radiobuttons id="logicaAutomatica" path="logicaAutomatica" items="${opcionBooleana}"/></td>
            </tr>
            <tr>
                <th><label for="nombreLogica">Nombre Logica:</label></th>
                <td><form:input id="nombreLogica" path="nombreLogica"/></td>
                <td><form:errors path="nombreLogica" /></td>
            </tr>
        </table>
            <input type="submit" value="actualizar">
            </form:form>
            <a href="homeCategoria.htm">Volver</a>
    </body>
</html>

