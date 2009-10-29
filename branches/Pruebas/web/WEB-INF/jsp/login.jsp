<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<html>

<head>
<title>Login: Spring Security Web Application</title>

<style TYPE="text/css">
.errormessage {
   color:red;
}

.successmessage {
}
</style>
</head>

<body onload='document.loginForm.j_username.focus();'>

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

<c:if test="${empty param.l}">
        <table>
          <tr><td>Username</td><td><input id="usernameField" type="text" name="j_username" value="<c:out value="${SPRING_SECURITY_LAST_USERNAME}"/>"/></td></tr>
          <tr><td>Password</td><td><input id="passwordField" type="password" name="j_password" /></td></tr>

          <tr><td colspan="2" align="right"><input type="submit" value="Login" /></td></tr>
        </table></c:if><c:if test="${not empty param.l}"><%@ include file="/WEB-INF/jsp/userinfobar.jsp"%></c:if>
          <c:out value="${now}" />
          <%@ include file="/WEB-INF/jsp/navigation.jsp" %>
</form>

</body>

</html>