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
        <title>iBet | <spring:message code="registroUsuario.titulo.form"/></title>
        <jsp:include page="/WEB-INF/jsp/include/head.jsp"></jsp:include>
        <script type="text/javascript">
            $(function() {
                $('#datepicker').datepicker({
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: 'yy-mm-dd',
                    yearRange: '1950:2010'
                });
            });
        </script>
    </head>
    <body>
        <div id="pageWrap">
            <jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
            <div id="content">
                <div id="contentWrapper">
                    <div id="contentColumn">
                        <div id="centerPane">
                            <div class="paneTitle">
                                <spring:message code="registroUsuario.titulo.form"/>
                            </div>
                            <div class="pane">
                                <form:form commandName="registroUsuario" action="registroUsuario.htm">
                                    <fieldset>
                                        <legend><spring:message code="usuario.encabezado.datos"/></legend>
                                        <form:errors path="*" cssStyle="messages" />
                                        <br>
                                        <table>
                                            <tbody>
                                                <tr>
                                                    <th><label for="nombreUsuario"><spring:message code="usuario.forms.username"/></label></th>
                                                    <td><form:input id="nombreUsuario" path="nombreUsuario" autocomplete="true"/></td>
                                                    <td><form:errors path="nombreUsuario" cssClass="error"/></td>
                                                </tr>
                                                <tr>
                                                    <th><label for="clave"><spring:message code="usuario.forms.clave"/></label></th>
                                                    <td><form:password id="clave" path="clave"/></td>
                                                    <td><form:errors path="clave" cssClass="error" /></td>
                                                </tr>
                                                <tr>
                                                    <th><label for="repClave"><spring:message code="usuario.forms.claveRep"/></label></th>
                                                    <td><form:password id="repClave" path="repClave"  /></td>
                                                    <td><form:errors path="repClave" cssClass="error" /></td>
                                                </tr>
                                                <tr>
                                                    <th><label for="nombre"><spring:message code="usuario.forms.nombre"/></label></th>
                                                    <td><form:input id="nombre" path="nombre" /></td>
                                                    <td><form:errors path="nombre" cssClass="error"/></td>
                                                </tr>
                                                <tr>
                                                    <th><label for="apellido"><spring:message code="usuario.forms.apellido"/></label></th>
                                                    <td><form:input id="apellido" path="apellido" /></td>
                                                    <td><form:errors path="apellido" cssClass="error"/></td>
                                                </tr>
                                                <tr>
                                                    <th><label for="datepicker"><spring:message code="usuario.forms.fechaNacimiento"/></label></th>
                                                    <td><form:input id="datepicker" path="fechaNacimiento"  /></td>
                                                    <td><form:errors path="fechaNacimiento" cssClass="error" /></td>
                                                </tr>
                                                <tr>
                                                    <th><label for="sexo"><spring:message code="usuario.forms.sexo"/></label></th>
                                                    <td><form:radiobuttons id="sexo" path="sexo" items="${opcionSexo}" /></td>
                                                    <td><form:errors path="sexo" cssClass="error"/></td>
                                                </tr>
                                                <tr>
                                                    <th><label for="correo"><spring:message code="usuario.forms.correo"/></label></th>
                                                    <td><form:input id="correo" path="correo" /></td>
                                                    <td><form:errors path="correo" cssClass="error" /></td>
                                                </tr>
                                                <tr>
                                                    <th><label for="repCorreo"><spring:message code="usuario.forms.correo.rep"/></label></th>
                                                    <td><form:input id="repCorreo" path="repCorreo" /></td>
                                                    <td><form:errors path="repCorreo" cssClass="error"/></td>
                                                </tr>
                                                <tr>
                                                    <th><label for="telefono"><spring:message code="usuario.forms.telefono"/></label></th>
                                                    <td><form:input id="telefono" path="telefono"/></td>
                                                    <td><form:errors path="telefono" cssClass="error"/></td>
                                                </tr>
                                                <tr>
                                                    <th><label for="pais"><spring:message code="usuario.forms.pais"/></label></th>
                                                    <td><form:select id="pais" path="pais" items="${opcionPais}"/></td>
                                                    <td><form:errors path="pais" cssClass="error"/></td>
                                                </tr>
                                                <tr>
                                                    <th><label for="estado"><spring:message code="usuario.forms.estado"/></label></th>
                                                    <td><form:select id="estado" path="estado" items="${opcionEstado}"/></td>
                                                    <td><form:errors path="estado" cssClass="error"/></td>
                                                </tr>
                                                <tr>
                                                    <th><label for="ciudad"><spring:message code="usuario.forms.ciudad"/></label></th>
                                                    <td><form:input id="ciudad" path="ciudad"  /></td>
                                                    <td><form:errors path="ciudad" cssClass="error" /></td>
                                                </tr>
                                                <tr>
                                                    <th><label for="calle"><spring:message code="usuario.forms.calle"/></label></th>
                                                    <td><form:textarea id="calle" path="calle" rows="4" /></td>
                                                    <td><form:errors path="calle" cssClass="error"/></td>
                                                </tr>
                                                <tr>
                                                    <th><label for="codigoPostal"><spring:message code="usuario.forms.codigoPostal"/></label></th>
                                                    <td><form:input id="codigoPostal" path="codigoPostal" /></td>
                                                    <td><form:errors path="codigoPostal" cssClass="error" /></td>
                                                </tr>
                                                <tr>
                                                    <th>&nbsp;</th>
                                                    <td>
                                                        <form:checkbox id="aceptarContrato" path="aceptaContrato" value="1"/>
                                                        <label for="aceptarContrato"><spring:message code="usuario.forms.politica"/></label>
                                                    </td>
                                                    <td><form:errors path="aceptaContrato" cssClass="error" /></td>
                                                </tr>
                                                <tr>
                                                    <th>&nbsp;</th>
                                                    <td><input type="submit" value="Registrar"/></td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </fieldset>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="leftColumn"></div>
                <div id="rightColumn"></div>
            </div>
            <jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
        </div>





















        <h2></h2>

    </body>
</html>
