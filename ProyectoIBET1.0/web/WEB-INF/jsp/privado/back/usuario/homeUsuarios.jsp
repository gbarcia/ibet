<%-- 
    Document   : homeUsuarios
    Created on : Dec 29, 2009, 12:29:15 PM
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
        <title>iBet | Administraci&oacute;n de Usuarios</title>
        <jsp:include page="/WEB-INF/jsp/include/headAdmin.jsp"></jsp:include>
    <body>
        <div id="pageWrap">
            <jsp:include page="/WEB-INF/jsp/include/headerAdmin.jsp"></jsp:include>
            <div id="content">
                <div id="contentWrapper">
                    <div id="contentColumn">
                        <div id="centerPane">
                            <div class="paneTitle">
                                Gesti&oacute;n de Usuarios
                            </div>
                            <div class="pane">
                                <c:if test="${not empty param.mensaje}">
                                    <c:if test="${param.r!= '1'}">
                                    <div id="alert"><c:out value="${param.mensaje}"/>
                                    </div>
                                    </c:if>
                                </c:if>
                                <display:table id="listadoUsuarios" name="usuariosList" pagesize="5" decorator="org.displaytag.decorator.TotalTableDecorator"
                                               export="true" class="dataTable" requestURI="homeUsuarios.htm?r=1">
                                    <display:column property="username" title="Nombre de Usuario" sortable="true"/>
                                    <display:column property="nombre" title="Nombre" sortable="true"/>
                                    <display:column property="apellido" title="Apellido" sortable="true"/>
                                    <display:column property="fechaNacimiento" title="Fecha Nacimiento"/>
                                    <display:column property="sexo" title="Sexo"/>
                                    <display:column property="correo" title="Correo"/>
                                    <display:column property="telefono" title="Telefono"/>
                                    <display:column property="pais" title="Pais"/>
                                    <display:column title="Activo" >
                                        <c:choose>
                                            <c:when test="${listadoUsuarios.enabled=='true'}">
                                                Si
                                            </c:when>
                                            <c:when test="${listadoUsuarios.enabled=='false'}">
                                                No
                                            </c:when>
                                        </c:choose>
                                    </display:column>
                                    <display:column title="Acciones">
                                        <c:choose>
                                            <c:when test="${listadoUsuarios.enabled=='true'}">
                                                <a class="botonInhabilitar" href="inhabilitarUsuario.htm?username=${listadoUsuarios.username}">
                                                    <img src="<%= request.getContextPath() + "/images/icons/inhabilitar.png"%>" width="16" height="16" alt="Inhabilitar"/>
                                                </a>
                                            </c:when>
                                            <c:when test="${listadoPagos.activo=='false'}">
                                                <a class="botonHabilitar" href="habilitarUsuario.htm?username=${listadoUsuarios.username}">
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