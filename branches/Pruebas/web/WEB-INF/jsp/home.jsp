<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>

<head>
<title>Home: Spring Security Web Application</title>
<link rel="stylesheet" type="text/css" href="css/displaytagex.css">
</head>

<body>
<%@ include file="/WEB-INF/jsp/navigation.jsp" %>
<%@ include file="/WEB-INF/jsp/userinfobar.jsp"%>
home page: only logged in users should see this page.
now is, <c:out value="${now}"/>

<display:table id="clientes" name="listaClientes" pagesize="3" requestURI="/home.htm" decorator="org.displaytag.decorator.TotalTableDecorator"
               export="true" class="dataTable">
    <display:column property="id" title="ID" sortable="true"/>
    <display:column property="rif" title="RIF" sortable="true" class="customer" headerClass="customer"/>
    <display:column property="nombre" title="NOMBRE" />
</display:table>
 
</body>

</html>