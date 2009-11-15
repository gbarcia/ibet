<%-- 
    Document   : registroUsuario
    Created on : 07/11/2009, 12:17:27 PM
    Author     : Gerardo Barcia
--%>
<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="registroUsuario.titulo.form"/></title>
        <jsp:include page="/WEB-INF/jsp/include/head.jsp"></jsp:include>
    </head>
    <body>
        <h2><spring:message code="registroUsuario.titulo.form"/></h2>
        <form:form commandName="registroUsuario" action="registroUsuario.htm">
            <fieldset>
                <legend>Introduce tu Datos</legend>
                <form:errors path="*"/>
                <c:if test="${param.resultado == 'SUCCESS'}">
                    <h2>Formulario Procesado</h2>
                </c:if>
                <br>
                <table border="0" cellpadding="1">
                    <tbody>
                        <tr>
                            <th><label for="nombreUsuario"><spring:message code="usuario.forms.username"/></label></th>
                            <td><form:input id="nombreUsuario" path="nombreUsuario" autocomplete="true"/></td>
                            <td><form:errors path="nombreUsuario" /></td>
                        </tr>
                        <tr>
                            <td><spring:message code="usuario.forms.clave"/></td>
                            <td><form:password path="clave"/></td>
                            <td><form:errors path="clave" /></td>
                        </tr>
                        <tr>
                            <td><spring:message code="usuario.forms.claveRep"/></td>
                            <td><form:password path="repClave"  /></td>
                            <td><form:errors path="repClave" /></td>
                        </tr>
                        <tr>
                            <td><spring:message code="usuario.forms.nombre"/></td>
                            <td><form:input path="nombre" /></td>
                            <td><form:errors path="nombre" /></td>
                        </tr>
                        <tr>
                            <td><spring:message code="usuario.forms.apellido"/></td>
                            <td><form:input path="apellido" /></td>
                            <td><form:errors path="apellido" /></td>
                        </tr>
                        <tr>
                            <td><spring:message code="usuario.forms.fechaNacimiento"/></td>
                            <td><form:input path="fechaNacimiento"  /></td>
                            <td><form:errors path="fechaNacimiento" /></td>
                        </tr>
                        <tr>
                            <td><spring:message code="usuario.forms.sexo"/></td>
                            <td><form:radiobuttons path="sexo" items="${opcionSexo}" /></td>
                            <td><form:errors path="sexo" /></td>
                        </tr>
                        <tr>
                            <td><spring:message code="usuario.forms.correo"/></td>
                            <td><form:input path="correo" /></td>
                            <td><form:errors path="correo" /></td>
                        </tr>
                        <tr>
                            <td><spring:message code="usuario.forms.correo.rep"/></td>
                            <td><form:input path="repCorreo" /></td>
                            <td><form:errors path="repCorreo" /></td>
                        </tr>
                        <tr>
                            <td><spring:message code="usuario.forms.telefono"/></td>
                            <td><form:input path="telefono"/></td>
                            <td><form:errors path="telefono"/></td>
                        </tr>
                        <tr>
                            <td><spring:message code="usuario.forms.pais"/></td>
                            <td><form:select path="pais" items="${opcionPais}"/></td>
                            <td><form:errors path="pais"/></td>
                        </tr>
                        <tr>
                            <td><spring:message code="usuario.forms.estado"/></td>
                            <td><form:select path="estado" items="${opcionEstado}"/></td>
                            <td><form:errors path="estado"/></td>
                        </tr>
                        <tr>
                            <td><spring:message code="usuario.forms.ciudad"/></td>
                            <td><form:input path="ciudad"  /></td>
                            <td><form:errors path="ciudad" /></td>
                        </tr>
                        <tr>
                            <td><spring:message code="usuario.forms.calle"/></td>
                            <td><form:textarea path="calle" /></td>
                            <td><form:errors path="calle" /></td>
                        </tr>
                        <tr>
                            <td><spring:message code="usuario.forms.codigoPostal"/></td>
                            <td><form:input path="codigoPostal" /></td>
                            <td><form:errors path="codigoPostal" /></td>
                        </tr>
                        <tr>
                            <td><spring:message code="usuario.forms.politica"/></td>
                            <td><form:checkbox path="aceptaContrato" value="1"/></td>
                            <td><form:errors path="aceptaContrato" /></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Registrar"/></td>
                        </tr>
                    </tbody>
                </table>
            </fieldset>
        </form:form>
    </body>
</html>
