<%-- 
    Document   : homeCategoria
    Created on : 05/12/2009, 03:44:57 PM
    Author     : Gerardo Barcia
--%>

<%@page session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iBet | Administraci&oacute;n de Categorias</title>
        <jsp:include page="/WEB-INF/jsp/include/headAdmin.jsp"></jsp:include>
    <body>
        <div id="pageWrap">
            <jsp:include page="/WEB-INF/jsp/include/headerAdmin.jsp"></jsp:include>
            <div id="content">
                <div id="contentWrapper">
                    <div id="contentColumn">
                        <div id="centerPane">
                            <div class="paneTitle">
                                Gesti&oacute;n de Categorias
                                <a class="botonAgregar" href="agregarCategoria.htm">
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
                                <display:table id="listadoCategorias" name="categoriaList" pagesize="5" decorator="org.displaytag.decorator.TotalTableDecorator"
                                               export="true" class="dataTable" requestURI="homeCategoria.htm?r=1">
                                    <display:column property="nombre" title="Nombre" sortable="true"/>
                                    <display:column title="Empate" >
                                        <c:choose>
                                            <c:when test="${listadoCategorias.empate=='true'}">
                                                Si
                                            </c:when>
                                            <c:when test="${listadoCategorias.empate=='false'}">
                                                No
                                            </c:when>
                                        </c:choose>
                                    </display:column>
                                    <display:column  title="Logica Automatica">
                                        <c:choose>
                                            <c:when test="${listadoCategorias.logicaAutomatica=='true'}">
                                                Si
                                            </c:when>
                                            <c:when test="${listadoCategorias.logicaAutomatica=='false'}">
                                                No
                                            </c:when>
                                        </c:choose>
                                    </display:column>
                                    <display:column  title="Habilitada">
                                        <c:choose>
                                            <c:when test="${listadoCategorias.habilitada=='true'}">
                                                Si
                                            </c:when>
                                            <c:when test="${listadoCategorias.habilitada=='false'}">
                                                No
                                            </c:when>
                                        </c:choose>
                                    </display:column>
                                    <display:column title="Acciones">
                                        <a class="botonActualizar" href="updateCategoria.htm?id=${listadoCategorias.id}">
                                            <img src="<%= request.getContextPath() + "/images/icons/update.png"%>" width="16" height="16" alt="Actualizar"/>
                                        </a>
                                        <c:choose>
                                            <c:when test="${listadoCategorias.habilitada=='true'}">
                                                <a class="botonInhabilitar" href="inhabilitarCategoria.htm?id=${listadoCategorias.id}">
                                                    <img src="<%= request.getContextPath() + "/images/icons/inhabilitar.png"%>" width="16" height="16" alt="Inhabilitar"/>
                                                </a>
                                            </c:when>
                                            <c:when test="${listadoCategorias.habilitada=='false'}">
                                                <a class="botonHabilitar" href="habilitarCategoria.htm?id=${listadoCategorias.id}">
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