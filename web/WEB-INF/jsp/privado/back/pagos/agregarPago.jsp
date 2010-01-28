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
        <jsp:include page="/WEB-INF/jsp/include/headAdmin.jsp"></jsp:include>
    <body>
        <div id="pageWrap">
            <jsp:include page="/WEB-INF/jsp/include/headerAdmin.jsp"></jsp:include>
            <div id="content">
                <div id="contentWrapper">
                    <div id="contentColumn">
                        <div id="centerPane">
                            <div class="paneTitle">
                                Nuevo Medio de Pago
                            </div>
                            <div class="pane">
                                <form:errors path="*" cssClass="error"/>
                                <fieldset>
                                    <legend>Datos</legend>
                                    <table>
                                          <form:form commandName="agregarMedioPago">
                                            <tr>
                                                <th><label for="nombre">Nombre:</label></th>
                                                <td><form:input id="nombre" path="nombre"/></td>
                                                <td><form:errors path="nombre" /></td>
                                            </tr>
                                            <tr>
                                                <th>&nbsp;</th>
                                                <td>
                                                    <input type="submit" value="Registrar">
                                                    <a href="homeMedioPago.htm">Volver</a>
                                                </td>
                                                <td>&nbsp;</td>
                                            </tr>
                                        </table>
                                    </fieldset>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="/WEB-INF/jsp/include/footerAdmin.jsp"></jsp:include>
        </div>
    </body>
</html>