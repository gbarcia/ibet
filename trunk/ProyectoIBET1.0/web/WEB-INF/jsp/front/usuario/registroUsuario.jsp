<%-- 
    Document   : registroUsuario
    Created on : 07/11/2009, 12:17:27 PM
    Author     : Gerardo Barcia
--%>
<%@include file="/WEB-INF/jsp/comun/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="registroUsuario.titulo.form"/></title>
    </head>
    <body>
        <h2><spring:message code="registroUsuario.titulo.form"/></h2>
        <form:form commandName="registroUsuario" action="registroUsuario.htm">
            <form:errors path="*"/>
            <c:if test="${ param.resultado == 'SUCCESS'}">
                <h2>Formulario Procesado</h2>
            </c:if>
             <c:if test="${ param.errorNegocio != ''}">
                 <h2><c:out value="${param.errorNegocio}"/></h2>
            </c:if>
            <table border="0" cellpadding="1">
                <tbody>
                    <tr>
                        <td><spring:message code="perfilUsuario.forms.username"/></td>
                        <td><form:input path="nombreUsuario" /></td>
                        <td><form:errors path="nombreUsuario" /></td>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Registrar"/></td>
                    </tr>
                </tbody>
            </table>
        </form:form>
    </body>
</html>
