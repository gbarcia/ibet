<%-- 
    Document   : headerAdmin
    Created on : Dec 12, 2009, 1:16:00 PM
    Author     : jonathan
--%>

<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="header">
    <div id="headerTop">
        <div id="logo">
            <a href="/ProyectoIBET/home.htm">
                <img src="<%= request.getContextPath() + "/images/logo/logoShadow.png"%>" width="164" height="50" alt="logoShadow"/>
            </a>
        </div>
            <div id="menuTop">
                <ul id="menuTopList">
                    <li><a href="/ProyectoIBET/privado/back/admin.htm">Eventos</a></li>
                    <li><a href="/ProyectoIBET/privado/back/categoria/homeCategoria.htm">Categorias</a></li>
                    <li><a href="#">Usuarios</a></li>
                    <li><a href="/ProyectoIBET/privado/back/reportes/homeReportes">Reportes</a></li>
                    <li><a href="#">Medios de Pago</a></li>
                </ul>
            </div>
        <security:authorize ifAllGranted="ROLE_ADMIN">
            <div id="sesion">
                <div id="username">
                    <security:authentication property="principal.username"/>
                </div>
                <div id="userOptions">
                    <a href="/ProyectoIBET/logout.htm"><spring:message code="header.logout"/></a>
                </div>
            </div>
        </security:authorize>
    </div>
</div>