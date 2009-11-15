<%--
    Document   : login.jsp
    Descripcion: pagina principal de la aplicacion y login
    Created on : 22/10/2009, 11:47:48 PM
    Author     : Gerardo Barcia
--%>

<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>Login: Spring Security Web Application</title>
        <jsp:include page="include/head.html"></jsp:include>
    </head>
    <body onload='document.loginForm.j_username.focus();'>
        <div class="pageWrap">
            <jsp:include page="include/header.html"></jsp:include>
            <div id="content">
                <div id="contentWrapper">
                    <div id="contentColumn">
                        <div id="centerPane">
                            <div class="paneTitle">
                                Inicio de Sesion
                            </div>
                            <div class="pane">
                                <form id="loginForm" name="loginForm" action="j_spring_security_check" method="post">
                                    <fieldset>
                                        <legend></legend>
                                    <c:if test="${not empty param.authfailed}">
                                        <div class="error">
                                            Login failed due to: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
                                        </div>
                                    </c:if>
                                    <c:if test="${not empty param.newpassword}">
                                        <div class="error">
                                            Login failed due to: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
                                        </div>
                                    </c:if>
                                    <c:if test="${not empty param.acclocked}">
                                        <div class="error">
                                            Login failed due to: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
                                        </div>
                                    </c:if>
                                    <c:if test="${not empty param.accdisabled}">
                                        <div class="error">
                                            Login failed due to: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
                                        </div>
                                    </c:if>
                                    <c:if test="${not empty param.loggedout}">
                                        <div class="error">
                                            You have been successfully logged out.
                                        </div>
                                    </c:if>
                                        <table align="center">
                                            <tr>
                                                <th><label for="usernameField">Username</label></th>
                                                <td>
                                                    <input id="usernameField" type="text" name="j_username" value="<c:out value="${SPRING_SECURITY_LAST_USERNAME}"/>"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th><label for="passwordField">Password</label></th>
                                                <td>
                                                    <input id="passwordField" type="password" name="j_password" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    &nbsp;
                                                </th>
                                                <td>
                                                    <input type="submit" value="Login" />
                                                </td>
                                            </tr>
                                        </table>
                                    </fieldset>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="leftColumn">

                </div>
                <div id="rightColumn">

                </div>
            </div>
            <jsp:include page="include/footer.html"></jsp:include>
        </div>







    </body>

</html>