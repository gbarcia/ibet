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
        <title>Gestion de Categorias</title>
    </head>
    <body>
        <h1>Gestion de Categorias</h1>
        <c:if test="${not empty param.mensaje}">
            <c:out value="${param.mensaje}"/>
        </c:if>
        <div>
            <display:table id="listadoCategorias" name="categoriaList" pagesize="5" decorator="org.displaytag.decorator.TotalTableDecorator"
                           export="true" class="" requestURI="homeCategoria.htm">
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
                    <a href="updateCategoria.htm?id=${listadoCategorias.id}">actualizar</a>
                    <c:choose>
                        <c:when test="${listadoCategorias.habilitada=='true'}">
                            <a href="inhabilitarCategoria.htm?id=${listadoCategorias.id}">inhabilitar</a>
                        </c:when>
                        <c:when test="${listadoCategorias.habilitada=='false'}">
                            <a href="habilitarCategoria.htm?id=${listadoCategorias.id}">habilitar</a>
                        </c:when>
                    </c:choose>
                </display:column>
            </display:table>
        </div>
        <a href="agregarCategoria.htm">nueva categoria</a>
    </body>
</html>
