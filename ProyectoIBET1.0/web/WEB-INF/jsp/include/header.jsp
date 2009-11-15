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
            <a href="home.htm">
                <img src="<%= request.getContextPath()+ "/images/logo/logoShadow.png"%>" width="164" height="50" alt="logoShadow"/>
            </a>
        </div>
        <security:authorize ifAllGranted="ROLE_GUEST">
            <div id="menuTop">
                <ul id="menuTopList">
                    <li><a href="login.htm"><spring:message code="menu.login"/></a></li>
                    <li><a href="publico/front/usuario/registroUsuario.htm"><spring:message code="menu.register"/></a></li>
                    <li><a href="about.htm"><spring:message code="menu.about"/></a></li>
                </ul>
            </div>
        </security:authorize>
        <security:authorize ifAllGranted="ROLE_USER">
            <div id="sesion">
                <div id="username">
                    <security:authentication property="principal.username"/>
                </div>
                <div id="userOptions"><a href="">Opcion</a> | <a href="">Opcion</a> | <a href="asdasd">Opcion</a></div>
            </div>
        </security:authorize>
    </div>
    <div id="headerBottom">
    </div>
</div>