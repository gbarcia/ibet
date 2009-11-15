<%-- 
    Document   : perfilUsuario
    Created on : Oct 29, 2009, 4:35:55 PM
    Author     : maya
--%>
<%@include file="/WEB-INF/jsp/comun/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="perfilUsuario.titulo.form"/></title>
    </head>
    <body>
        <h2>
            <spring:message code="perfilUsuario.titulo.form"/>
        </h2>
        <form:form method="POST" commandName="perfil">
            <table>
                <tbody>
                    <tr>
                        <td>
                            <spring:message code="perfilUsuario.forms.username"/>
                        </td>
                        <td>
                            <form:input path="username"/>
                            <form:errors path="username" /><br/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <spring:message code="perfilUsuario.forms.nombre"/>
                        </td>
                        <td>
                            <form:input path="nombre"/>
                            <form:errors path="nombre" /><br/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <spring:message code="perfilUsuario.forms.apellido"/>
                        </td>
                        <td>
                            <form:input path="apellido"/>
                            <form:errors path="apellido" /><br/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <spring:message code="perfilUsuario.forms.fechaNacimiento"/>
                        </td>
                        <td>
                            <form:input path="fechaNacimiento"/>
                            <form:errors path="fechaNacimiento" /><br/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <spring:message code="perfilUsuario.forms.sexo"/>
                        </td>
                        <td>
                            <form:radiobuttons path="sexo" items="${sexor}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <spring:message code="perfilUsuario.forms.correo"/>
                        </td>
                        <td>
                            <form:input path="correo"/>
                            <form:errors path="correo" /><br/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        </td>
                            <spring:message code="perfilUsuario.forms.telefono"/>
                        <td>
                            <form:input path="telefono"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <spring:message code="perfilUsuario.forms.pais"/>
                        </td>
                        <td>
                            <form:input path="pais"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <spring:message code="perfilUsuario.forms.ciudad"/>
                        </td>
                        <td>
                            <form:input path="ciudad"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <spring:message code="perfilUsuario.forms.codigoPostal"/>
                        </td>
                        <td>
                            <form:input path="codigoPostal"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <spring:message code="perfilUsuario.forms.estado"/>
                        </td>
                        <td>
                            <form:input path="estado"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <spring:message code="perfilUsuario.forms.calle"/>
                        </td>
                        <td>
                            <form:input path="calle"/>
                        </td>
                    </tr>
                    <tr/>
                    <tr>
                        <td>
                        </td>
                        <td align="right">
                            <input type="submit"/>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form:form>
    </body>
</html>
