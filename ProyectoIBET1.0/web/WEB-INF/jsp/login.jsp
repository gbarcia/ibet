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
                                    <c:if test="${not empty param.authfailed}">
                                        <span id="infomessage" class="errormessage" >
                                            Login failed due to: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
                                        </span>
                                    </c:if>
                                    <c:if test="${not empty param.authfailed}">
                                        <span id="infomessage" class="errormessage" >
                                            Login failed due to: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
                                        </span>
                                    </c:if>
                                    <c:if test="${not empty param.newpassword}">
                                        <span id="infomessage" class="errormessage" >
                                            Login failed due to: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
                                        </span>
                                    </c:if>
                                    <c:if test="${not empty param.acclocked}">
                                        <span id="infomessage" class="errormessage" >
                                            Login failed due to: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
                                        </span>
                                    </c:if>
                                    <c:if test="${not empty param.accdisabled}">
                                        <span id="infomessage" class="errormessage" >
                                            Login failed due to: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
                                        </span>
                                    </c:if>
                                    <c:if test="${not empty param.loggedout}">
                                        <span id="infomessage" class="successmessage">
                                            You have been successfully logged out.
                                        </span>
                                    </c:if>
                                    <table>
                                        <tr><td>Username</td><td><input id="usernameField" type="text" name="j_username" value="<c:out value="${SPRING_SECURITY_LAST_USERNAME}"/>"/></td></tr>
                                        <tr><td>Password</td><td><input id="passwordField" type="password" name="j_password" /></td></tr>

                                        <tr><td colspan="2" align="right"><input type="submit" value="Login" /></td></tr>
                                    </table>
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