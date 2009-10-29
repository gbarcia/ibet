<%@ page session="true"%>
<%@page contentType="text/html"%>
<%@taglib prefix="form"
    uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring"
    uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
  <head>
      <title><fmt:message key="titulo.form.agregarCliente"/></title>
      <style>
         .error {
             color: #ff0000;
             font-weight: bold;
         }
      </style>
  </head>
  <body>
      <h2><fmt:message key="titulo.form.agregarCliente"/></h2>
      <form:form method="POST" enctype="multipart/form-data"action="agregarcliente.htm" commandName="cliente">
          <b><spring:message code="forms.cliente.rif.uno"/></b>
          <form:input path="rif" />
          <form:errors path="rif" cssClass="error" /><br/>
          <b><spring:message code="forms.cliente.rif.dos"/></b>
          <form:input path="nombre" />
          <form:errors path="nombre" cssClass="error" /><br/>
          <form:select path="estados">
              <form:options items="${estados}"/>
          </form:select><br/>
          <form:checkboxes path="estado" items="${esta}" />
          <input type="file" name="file">
          <input type="submit" align="center">
          <c:if test="${not empty param.r}">
          <spring:message code="forms.agregado"/>
          </c:if>
      </form:form>
  </body>
</html>
