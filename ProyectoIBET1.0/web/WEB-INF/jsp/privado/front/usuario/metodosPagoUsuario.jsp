<%--
    Document   : metodosPagoUsuario
    Created on : 24/11/2009, 10:26:11 AM
    Author     : nath
--%>

<%@page session="true" %>
<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iBet | <spring:message code="pagos.titulo"/></title>
        <jsp:include page="/WEB-INF/jsp/include/head.jsp"></jsp:include>
        <jsp:include page="/WEB-INF/jsp/include/displayTag.jsp"></jsp:include>
        <link rel="stylesheet" href="<%= request.getContextPath() + "/css/slider.css"%>" type="text/css" />

    </head>
    <body>
        <div id="pageWrap">
            <jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
            <div id="content">
                <div id="contentWrapper">
                    <div id="contentColumn">
                        <div id="centerPane">
                            <div class="paneTitle">
                                <spring:message code="pagos.titulo"/>
                                <%--<a href="habilitarUsuarioMedioPago.htm">
                                    <img src="<%= request.getContextPath() + "/images/icons/agregar.png"%>" width="16" height="16" alt="Inhabilitar"/>
                                </a>--%>
                            </div>
                            <div class="pane">
                                <display:table id="historial" name="historial" pagesize="5" decorator="org.displaytag.decorator.TotalTableDecorator"
                                               export="true" class="dataTable" requestURI="metodosPagoUsuario.htm">
                                    <display:column property="medioPago.nombre" title="Nombre" sortable="true"/>
                                    <display:column property="montoMaximo" title="Monto m&aacute;ximo" sortable="true"/>
                                    <display:column property="fechaInicio" title="Fecha de inicio" sortable="true"/>
                                    <display:column property="fechaFin" title="Fecha de fin" sortable="true"/>
                                    <display:column title="">
                                        <c:if test="${historial.activo == 'true'}">
                                            <%--<a id="i-${historial.usuarioMedioPagoPK.username}-${historial.usuarioMedioPagoPK.idMedioPago}" class="botonEditar" href="editarUsuarioMedioPago.htm?username=${historial.usuarioMedioPagoPK.username}&idMedioPago=${historial.usuarioMedioPagoPK.idMedioPago}">
                                                <img src="<%= request.getContextPath() + "/images/icons/editar.png"%>" width="16" height="16" alt="Inhabilitar"/>
                                            </a>--%>
                                            <a id="i-${historial.usuarioMedioPagoPK.username}-${historial.usuarioMedioPagoPK.idMedioPago}" class="botonInhabilitar" href="inhabilitarUsuarioMedioPago.htm?username=${historial.usuarioMedioPagoPK.username}&idMedioPago=${historial.usuarioMedioPagoPK.idMedioPago}">
                                                <img src="<%= request.getContextPath() + "/images/icons/inhabilitar.png"%>" width="16" height="16" alt="Inhabilitar"/>
                                            </a>
                                        </c:if>
                                    </display:column>
                                </display:table>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="leftColumn">
                </div>
                <div id="rightColumn">
                </div>
            </div>
            <jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
        </div>
    </body>
</html>
