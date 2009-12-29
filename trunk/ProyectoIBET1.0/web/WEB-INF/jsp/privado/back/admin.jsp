<%-- 
    Document   : admin
    Created on : 25/11/2009, 08:18:53 AM
    Author     : nath
--%>
<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iBet | Administraci&oacute;n</title>
        <jsp:include page="/WEB-INF/jsp/include/headAdmin.jsp"></jsp:include>
    </head>
    <body>
        <div id="pageWrap">
            <jsp:include page="/WEB-INF/jsp/include/headerAdmin.jsp"></jsp:include>
            <div id="content">
                <div id="contentWrapper">
                    <div id="contentColumn">
                        <div id="centerPane">
                            <div class="paneTitle">
                                Gestion de Eventos
                                <a class="botonAgregar" href="evento/registroEvento.htm">
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
                                <display:table id="lista" name="listaEventos" pagesize="15" decorator="org.displaytag.decorator.TotalTableDecorator"
                                               export="true" class="dataTable" requestURI="admin.htm?r=1">
                                    <display:column property="fechaEvento" title="Fecha" sortable="true"/>
                                    <display:column property="hora" title="Hora"/>
                                    <display:column property="idCategoria.nombre" title="Categoria"/>
                                    <display:column property="nombre" title="Nombre"/>
                                    <display:column property="idPolitica.montoMaximo" title="Monto Maximo" sortable="true"/>
                                    <display:column title="Estado">
                                        <c:choose>
                                            <c:when test="${lista.estatus=='true'}">
                                                Activo
                                            </c:when>
                                            <c:when test="${lista.estatus=='false'}">
                                                Desactivo
                                            </c:when>
                                        </c:choose>
                                    </display:column>
                                    <display:column property="fechaMaxima" title="Fecha Max" sortable="true"/>
                                    <display:column property="horaMaxima" title="Hora Max"/>
                                    <display:column title="Acciones">
                                        <c:if test="${lista.finalizado != 'true'}">
                                        <a class="botonFinalizar" href="finalizarEvento.htm?id=${lista.id}">
                                            <img src="<%= request.getContextPath() + "/images/icons/puerta.png"%>" width="16" height="16" alt="Actualizar"/>
                                        </a>
                                        </c:if>
                                        <c:choose>
                                            <c:when test="${lista.estatus=='true'}">
                                                <a class="botonInhabilitar" href="inhabilitarEvento.htm?id=${lista.id}">
                                                    <img src="<%= request.getContextPath() + "/images/icons/inhabilitar.png"%>" width="16" height="16" alt="Inhabilitar"/>
                                                </a>
                                            </c:when>
                                            <c:when test="${lista.estatus=='false'}">
                                                <a class="botonHabilitar" href="habilitarEvento.htm?id=${lista.id}">
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
            <jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
        </div>
    </body>
</html>