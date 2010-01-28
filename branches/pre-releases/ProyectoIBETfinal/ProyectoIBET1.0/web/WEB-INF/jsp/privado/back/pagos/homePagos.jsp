<%-- 
    Document   : homePagos
    Created on : Dec 16, 2009, 6:47:13 PM
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
        <title>iBet | Administraci&oacute;n de Medios de Pagos</title>
        <jsp:include page="/WEB-INF/jsp/include/headAdmin.jsp"></jsp:include>
    <body>
        <div id="pageWrap">
            <jsp:include page="/WEB-INF/jsp/include/headerAdmin.jsp"></jsp:include>
            <div id="content">
                <div id="contentWrapper">
                    <div id="contentColumn">
                        <div id="centerPane">
                            <div class="paneTitle">
                                Gesti&oacute;n de Medios de Pagos
                                <a class="botonAgregar" href="agregarPago.htm">
                                    <img src="<%= request.getContextPath() + "/images/icons/agregar.png"%>" width="16" height="16" alt="Inhabilitar"/>
                                </a>
                            </div>
                            <div class="pane">
                                <c:if test="${not empty param.mensaje}">
                                    <c:if test="${param.r!= '1'}">
                                    <div id="alert"><c:out value="${param.mensaje}"/>
                                    </div>
                                    </c:if>
                                </c:if>
                                <display:table id="listadoPagos" name="pagosList" pagesize="5" decorator="org.displaytag.decorator.TotalTableDecorator"
                                               export="true" class="dataTable" requestURI="homeMedioPago.htm?r=1">
                                    <display:column property="nombre" title="Nombre" sortable="true"/>
                                    <display:column title="Activo" >
                                        <c:choose>
                                            <c:when test="${listadoPagos.activo=='true'}">
                                                Si
                                            </c:when>
                                            <c:when test="${listadoPagos.activo=='false'}">
                                                No
                                            </c:when>
                                        </c:choose>
                                    </display:column>
                                    <display:column title="Acciones">
                                        <a class="botonActualizar" href="updatePago.htm?id=${listadoPagos.id}">
                                            <img src="<%= request.getContextPath() + "/images/icons/update.png"%>" width="16" height="16" alt="Actualizar"/>
                                        </a>
                                        <c:choose>
                                            <c:when test="${listadoPagos.activo=='true'}">
                                                <a class="botonInhabilitar" href="inhabilitarPago.htm?id=${listadoPagos.id}">
                                                    <img src="<%= request.getContextPath() + "/images/icons/inhabilitar.png"%>" width="16" height="16" alt="Inhabilitar"/>
                                                </a>
                                            </c:when>
                                            <c:when test="${listadoPagos.activo=='false'}">
                                                <a class="botonHabilitar" href="habilitarPago.htm?id=${listadoPagos.id}">
                                                    <img src="<%= request.getContextPath() + "/images/icons/habilitar.png"%>" width="16" height="16" alt="Habilitar"/>
                                                </a>
                                            </c:when>
                                        </c:choose>
                                    </display:column>
                                </display:table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="/WEB-INF/jsp/include/footerAdmin.jsp"></jsp:include>
        </div>
    </body>
</html>