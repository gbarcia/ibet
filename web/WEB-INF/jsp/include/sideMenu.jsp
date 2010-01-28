<%-- 
    Document   : sideMenu
    Created on : Nov 21, 2009, 11:20:17 AM
    Author     : jonathan
--%>
<%@include file="include.jsp" %>

<div id="menuSide">
    <ul id="menuSideList">
        <c:forEach items="${arbolCategoria}" var="categoria">
            <li><h1><c:out value="${categoria.categoriaPadre.nombre}" /></h1></li>
            <c:forEach items="${categoria.listaSubcategorias}" var="subcategoria">
                <li>
                    <a href="/ProyectoIBET/publico/front/eventos/eventos.htm?idCategoria=<c:out value="${subcategoria.id}" />">
                        <c:out value="${subcategoria.nombre}" />
                    </a>
                </li>
            </c:forEach>
        </c:forEach>
    </ul>
</div>