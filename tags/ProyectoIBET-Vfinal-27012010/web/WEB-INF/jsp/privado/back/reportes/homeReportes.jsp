<%-- 
    Document   : homeReportes
    Created on : 12/12/2009, 01:15:58 AM
    Author     : nath
--%>

<%@page session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iBet | Administraci&oacute;n de Categorias</title>
        <jsp:include page="/WEB-INF/jsp/include/headAdmin.jsp"></jsp:include>
    </head>
    <body>
        <div id="pageWrap">
            <jsp:include page="/WEB-INF/jsp/include/headerAdmin.jsp"></jsp:include>
            <div id="content">
                <div id="contentWrapper">
                    <div id="contentColumn">
                        <div id="centerPane">
                            <div class="paneTitle">
                                Reportes
                            </div>
                            <div class="pane">
                                <display:table id="listadoReportes" name="listaReportes" pagesize="4" decorator="org.displaytag.decorator.TotalTableDecorator"
                                               export="true" class="dataTable" requestURI="homeReportes.htm?r=1">
                                    <display:column title="Nombre" property="nombreReporte" sortable="true"/>
                                    <display:column title="Tipo de Reporte" property="tipoReporte" sortable="true"/>
                                    <display:column title="Acciones">
                                        <a href="${listadoReportes.url}">Ver</a>
                                    </display:column>             
                                </display:table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="/WEB-INF/jsp/include/footerAdmin.jsp"></jsp:include>
        </div>
    </body>
</html>