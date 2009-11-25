<%-- 
    Document   : reporteGanancias
    Created on : Nov 23, 2009, 5:50:19 PM
    Author     : maya
--%>
<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="/WEB-INF/jsp/include/head.jsp"></jsp:include>
        <script type="text/javascript">
            $(function() {
                $('#datepicker').datepicker({
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: 'yy-mm-dd',
                    yearRange: '2009:' + Date
                });
                $('#datepicker2').datepicker({
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: 'yy-mm-dd',
                    yearRange: '2009:' + Date
                });
               
            });
        </script>
    </head>
    <body>
        <form:form commandName="reporteGanancias" action="reporteGanancias.htm">
            <form:input id="datepicker" path="fechaInicio" />
            <form:input id="datepicker2" path="fechaFin" />
            <form:select path="tipoReporte" items="${opcionTipoReporte}"/>
            <input type="submit">
        </form:form>
    </body>
</html>
