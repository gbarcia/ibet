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
        <%@include file="/WEB-INF/jsp/comun/includeJSvalidateForm.jsp" %>
        <script type="text/javascript">
            window.onload = go;
            function go(){
                Typecast.Init();
            }
        </script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="registroUsuario.titulo.form"/></title>
    </head>
    <body>
        <h2><spring:message code="registroUsuario.titulo.form"/></h2>
        <form:form commandName="registroUsuario" action="registroUsuario.htm">
            <form:errors path="*"/>
            <c:if test="${param.resultado == 'SUCCESS'}">
                <h2>Formulario Procesado</h2>
            </c:if>
            <c:if test="${!empty param.errorNegocio}">
                <spring:message code="${param.errorNegocio}"/>
            </c:if>
            <br>
            <table border="0" cellpadding="1">
                <tbody>
                    <tr>
                        <td><spring:message code="usuario.forms.username"/></td>
                        <td><form:input path="nombreUsuario" autocomplete="true"/></td>
                        <td><form:errors path="nombreUsuario" /></td>
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
                        <td><form:input path="fechaNacimiento" /></td>
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
                        <td><input id="telefono" name="telefono" type="text" class="TCMask[(####) ###-####,(____) ___-_____]" value="" /></td>
                        <td><form:errors path="telefono"/></td>
                    </tr>
                    <tr>
                        <td><spring:message code="usuario.forms.codigoPostal"/></td>
                        <td><form:input path="codigoPostal" /></td>
                        <td><form:errors path="codigoPostal" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Registrar"/></td>
                    </tr>
                </tbody>
            </table>
        </form:form>
    </body>
</html>
