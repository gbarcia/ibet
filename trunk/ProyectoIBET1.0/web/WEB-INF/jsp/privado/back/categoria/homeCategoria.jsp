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
         <c:if test="${!categoriaList.firstPage}">
            <a href="homeCategoria.htm?page=previous"><b>&lt;&lt; Prev</b></a>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          </c:if>
          <c:if test="${!categoriaList.lastPage}">
            <a href="homeCategoria.htm?page=next"><b>Next &gt;&gt;</b></a>
          </c:if>
            <table border="0" cellpadding="4" cellspacing="0">
                <tr>
                    <th>Nombre</th>
                    <th>Posee empate</th>
                    <th>Logica automatica</th>
                    <th>Nombre Logica</th>
                    <th>Actions</th>
                </tr>
              <c:forEach var="categoria" items="${categoriaList.pageList}">
                <tr>
                    <td>${categoria.nombre}</td>
                    <td>
                        <c:choose>
                            <c:when test="${categoria.empate=='true'}">
                                Si
                            </c:when>
                            <c:when test="${categoria.empate=='false'}">
                                No
                            </c:when>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${categoria.logicaAutomatica=='true'}">
                                Si
                            </c:when>
                            <c:when test="${categoria.logicaAutomatica=='false'}">
                                No
                            </c:when>
                        </c:choose>
                    </td>
                    <c:if test="${!categoria.nombreLogica}">
           <td>N/A</td>
          </c:if>
                    <td>${categoria.nombreLogica}</td>                    
                    <td>
                        <a href="updateCategoria.html?id=${categoria.id}">actualizar</a>
                        <a href="deleteCategoria.html?id=${categoria.id}">inhabilitar</a>
                    </td>
                </tr>
              </c:forEach>
              <c:if test="${categoriaList.nrOfElements == 0}">
                <tr><td colspan="5">No hay categorias registradas</td></tr>
              </c:if>
            </table>
    </body>
</html>
