<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<html>

<head>
<title>Admin: Spring Security Web Application</title>

</head>

<body>
<%@ include file="/WEB-INF/jsp/navigation.jsp" %>
<%@ include file="/WEB-INF/jsp/userinfobar.jsp"%>
Admin page: only administrative users should see this page.

</body>

</html>
