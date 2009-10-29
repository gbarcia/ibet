<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<html>

<head>
 <script src="http://code.jquery.com/jquery-latest.js"></script>
 <script type="text/JavaScript">
     $(document).ready(function(){
         alert("Prueba JQUERY")
     })
 </script>
<title>Home: Spring Security Web Application</title>

</head>

<body>
<%@ include file="/WEB-INF/jsp/navigation.jsp" %>
<%@ include file="/WEB-INF/jsp/userinfobar.jsp"%>
home page: only logged in users should see this page.
now is, <c:out value="${now}"/>
</body>

</html>