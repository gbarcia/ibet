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
        <link rel="stylesheet" href="<%= request.getContextPath() + "/css/wholeLayout.css"%>" type="text/css" />
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
    <body onload='document.registroUsuario.nombreUsuario.focus();'>
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
                                        <legend><spring:message code="registroUsuario.titulo.personal"/></legend>
                                        <table>
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        <label for="nombre"><spring:message code="usuario.forms.nombre"/></label>
                                                    </td>
                                                    <td>
                                                        <form:input id="nombre" path="nombre" />
                                                    </td>
                                                    <td>
                                                        <form:errors path="nombre" cssClass="formError"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <label for="apellido"><spring:message code="usuario.forms.apellido"/></label>
                                                    </td>
                                                    <td>
                                                        <form:input id="apellido" path="apellido" />
                                                    </td>
                                                    <td>
                                                        <form:errors path="apellido" cssClass="formError"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <label for="datepicker"><spring:message code="usuario.forms.fechaNacimiento"/></label>
                                                    </td>
                                                    <td>
                                                        <form:input id="datepicker" path="fechaNacimiento"  />
                                                    </td>
                                                    <td>
                                                        <form:errors path="fechaNacimiento" cssClass="formError" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <label for="sexo"><spring:message code="usuario.forms.sexo"/></label>
                                                    </td>
                                                    <td>
                                                        <form:radiobuttons id="sexo" path="sexo" items="${opcionSexo}" />
                                                    </td>
                                                    <td>
                                                        <form:errors path="sexo" cssClass="formError"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <label for="correo"><spring:message code="usuario.forms.correo"/></label>
                                                    </td>
                                                    <td>
                                                        <form:input id="correo" path="correo" />
                                                    </td>
                                                    <td>
                                                        <form:errors path="correo" cssClass="formError" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <label for="repCorreo"><spring:message code="usuario.forms.correo.rep"/></label>
                                                    </td>
                                                    <td>
                                                        <form:input id="repCorreo" path="repCorreo" />
                                                    </td>
                                                    <td>
                                                        <form:errors path="repCorreo" cssClass="formError"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <label for="telefono"><spring:message code="usuario.forms.telefono"/></label>
                                                    </td>
                                                    <td>
                                                        <form:input id="telefono" path="telefono"/>
                                                    </td>
                                                    <td>
                                                        <form:errors path="telefono" cssClass="formError"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <label for="pais"><spring:message code="usuario.forms.pais"/></label>
                                                    </td>
                                                    <td>
                                                        <form:select id="pais" path="pais" items="${opcionPais}"/>
                                                    </td>
                                                    <td>
                                                        <form:errors path="pais" cssClass="formError"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <label for="estado"><spring:message code="usuario.forms.estado"/></label>
                                                    </td>
                                                    <td>
                                                        <form:select id="estado" path="estado" items="${opcionEstado}"/>
                                                    </td>
                                                    <td>
                                                        <form:errors path="estado" cssClass="formError"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <label for="ciudad"><spring:message code="usuario.forms.ciudad"/></label>
                                                    </td>
                                                    <td>
                                                        <form:input id="ciudad" path="ciudad"  />
                                                    </td>
                                                    <td>
                                                        <form:errors path="ciudad" cssClass="formError" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <label for="calle"><spring:message code="usuario.forms.calle"/></label>
                                                    </td>
                                                    <td>
                                                        <form:input id="calle" path="calle"/>
                                                    </td>
                                                    <td>
                                                        <form:errors path="calle" cssClass="formError"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <label for="codigoPostal"><spring:message code="usuario.forms.codigoPostal"/></label>
                                                    </td>
                                                    <td>
                                                        <form:input id="codigoPostal" path="codigoPostal" />
                                                    </td>
                                                    <td>
                                                        <form:errors path="codigoPostal" cssClass="formError" />
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </fieldset>
                                    <fieldset>
                                        <legend><spring:message code="registroUsuario.titulo.cuenta"/></legend>
                                        <table>
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        <label for="nombreUsuario"><spring:message code="usuario.forms.username"/></label>
                                                    </td>
                                                    <td>
                                                        <form:input id="nombreUsuario" path="nombreUsuario" autocomplete="true"/>
                                                    </td>
                                                    <td>
                                                        <form:errors path="nombreUsuario" cssClass="formError"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <label for="clave"><spring:message code="usuario.forms.clave"/></label>
                                                    </td>
                                                    <td>
                                                        <form:password id="clave" path="clave"/>
                                                    </td>
                                                    <td>
                                                        <form:errors path="clave" cssClass="formError" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <label for="repClave"><spring:message code="usuario.forms.claveRep"/></label>
                                                    </td>
                                                    <td>
                                                        <form:password id="repClave" path="repClave"  />
                                                    </td>
                                                    <td>
                                                        <form:errors path="repClave" cssClass="formError" />
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>

                                    </fieldset>
                                    <fieldset>
                                        <legend><spring:message code="registroUsuario.titulo.confirmacion"/></legend>
                                        <table>
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        <form:checkbox id="aceptarContrato" path="aceptaContrato" value="1"/>
                                                    </td>
                                                    <td>
                                                        <label for="aceptarContrato">
                                                            <spring:message code="usuario.forms.politica"/>
                                                        </label>
                                                    </td>
                                                    <td>
                                                        <form:errors path="aceptaContrato" cssClass="formError" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2" align="center"><input type="submit" value="Registrar"/></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </fieldset>
                                    <div class="clear"></div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
        </div>
    </body>
</html>
