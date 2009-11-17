<%--
    Document   : login.jsp
    Descripcion: pagina principal de la aplicacion y login
    Created on : 22/10/2009, 11:47:48 PM
    Author     : Gerardo Barcia
--%>
<%@include file="include/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>iBet | <spring:message code="login.titulo"/></title>
        <jsp:include page="include/head.jsp"></jsp:include>
    </head>
    <body onload='document.loginForm.j_username.focus();'>
        <div class="pageWrap">
            <jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
            <div id="content">
                <div id="contentWrapper">
                    <div id="contentColumn">
                        <div id="centerPane">
                            <div class="paneTitle">
                                <spring:message code="login.titulo"/>
                            </div>
                            <div class="pane">
                                <form id="loginForm" name="loginForm" action="j_spring_security_check" method="post">
                                    <fieldset>
                                        <legend><spring:message code="login.form.titulo"/></legend>
                                        <c:if test="${not empty param.authfailed}">
                                            <div class="error">
                                                <c:choose>
                                                    <c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'Bad credentials'}">
                                                        <spring:message code="login.error.badcredentials"/>
                                                    </c:when>
                                                    <c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'User not found'}">
                                                        <spring:message code="login.error.notfound"/>
                                                    </c:when>
                                                    <c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'Maximum sessions of 1 for this principal exceeded'}">
                                                        <spring:message code="login.error.maximum"/>
                                                    </c:when>
                                                    <c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message != 'Bad credentials' && SPRING_SECURITY_LAST_EXCEPTION.message != 'User not found'
                                                                    && SPRING_SECURITY_LAST_EXCEPTION.message != 'Maximum sessions of 1 for this principal exceeded' }">
                                                        <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message} "/>
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                        </c:if>
                                        <c:if test="${not empty param.newpassword}">
                                            <div class="error">
                                                <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message} "/>
                                            </div>
                                        </c:if>
                                        <c:if test="${not empty param.acclocked}">
                                            <div class="error">
                                                <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message} "/>
                                            </div>
                                        </c:if>
                                        <c:if test="${not empty param.accdisabled}">
                                            <div class="error">
                                                <spring:message code="login.error.disabled"/>
                                            </div>
                                        </c:if>
                                        <table align="center">
                                            <tr>
                                                <th><label for="usernameField"><spring:message code="login.form.username"/></label></th>
                                                <td>
                                                    <input id="usernameField" type="text" name="j_username" value="<c:out value="${SPRING_SECURITY_LAST_USERNAME}"/>"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th><label for="passwordField"><spring:message code="login.form.password"/></label></th>
                                                <td>
                                                    <input id="passwordField" type="password" name="j_password" />
                                                    <spring:message code="login.form.forgot"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    &nbsp;
                                                </th>
                                                <td colspan="2">
                                                    <input type="submit" value="<spring:message code="login.form.button"/>" />
                                                    <a href="publico/front/usuario/registroUsuario.htm"><spring:message code="login.form.newaccount"/></a>
                                                </td>
                                            </tr>
                                        </table>
                                    </fieldset>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="leftColumn"></div>
                <div id="rightColumn"></div>
            </div>
            <jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
        </div>
    </body>
</html>