<%-- 
    Document   : historialApuestas
    Created on : 22/11/2009, 05:18:27 PM
    Author     : nath
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
        <jsp:include page="/WEB-INF/jsp/include/displayTag.jsp"></jsp:include>
    </head>
    <body>
        <display:table id="listadoApuestas" name="listaHistorial" pagesize="1" decorator="org.displaytag.decorator.TotalTableDecorator"
                       export="true" class="dataTable" requestURI="historialApuestas.htm">
            <display:column property="fecha" title="Fecha" sortable="true"/>
            <display:column property="eventoNombre" title="Evento"/>
            <display:column property="monto" title="Monto($)"  sortable="true"/>
            <display:column property="participanteNombre" title="Aposte por" />
            <display:column property="eventoResultado" title="Resultado" />
        </display:table>
    </body>
</html>
