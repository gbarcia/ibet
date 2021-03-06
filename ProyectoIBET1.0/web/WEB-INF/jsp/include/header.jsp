<%--
    Document   : header
    Created on : Nov 15, 2009, 2:24:49 PM
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
        <security:authorize ifAllGranted="ROLE_GUEST">
            <div id="menuTop">
                <ul id="menuTopList">
                    <li><a href="/ProyectoIBET/login.htm"><spring:message code="menu.login"/></a></li>
                    <li><a href="/ProyectoIBET/publico/front/usuario/registroUsuario.htm"><spring:message code="menu.register"/></a></li>
                    <li><a href="/ProyectoIBET/about.htm"><spring:message code="menu.about"/></a></li>
                </ul>
            </div>
        </security:authorize>
        <security:authorize ifAllGranted="ROLE_USER">
            <div id="sesion">
                <div id="username">
                    <security:authentication property="principal.username"/>
                </div>
                <div id="userOptions">
                    <a href="/ProyectoIBET/privado/front/usuario/perfilUsuario.htm"><spring:message code="header.perfil"/></a> |
                    <a href="/ProyectoIBET/privado/front/usuario/historialApuestas.htm"><spring:message code="header.historial"/></a> |
                    <a href="/ProyectoIBET/privado/front/usuario/metodosPagoUsuario.htm"><spring:message code="header.metodos"/></a> |
                    <a href="/ProyectoIBET/logout.htm"><spring:message code="header.logout"/></a>
                </div>
            </div>
        </security:authorize>
    </div>
    <div id="headerBottom">
        <div id="twitter">
            <div id="twitterText">
                <a href="http://twitter.com/iBetResultados">
                    <spring:message code="twitter.follow"/>
                </a>
            </div>
            <a href="http://twitter.com/iBetResultados">
                <img src="<%= request.getContextPath() + "/images/icons/twitter.png"%>" alt="twitter" width="16" height="16"/>
            </a>
        </div>
    </div>
</div>