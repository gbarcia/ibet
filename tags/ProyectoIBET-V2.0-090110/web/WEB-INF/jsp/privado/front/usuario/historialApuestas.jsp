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
        <title>iBet | <spring:message code="usuario.titulo.historialapuestas"/></title>
        <jsp:include page="/WEB-INF/jsp/include/head.jsp"></jsp:include>
        <jsp:include page="/WEB-INF/jsp/include/displayTag.jsp"></jsp:include>
    </head>
    <body>
        <div id="pageWrap">
            <jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
            <div id="content">
                <div id="contentWrapper">
                    <div id="contentColumn">
                        <div id="centerPane">
                            <div class="paneTitle">
                                <spring:message code="usuario.titulo.historialapuestas"/>
                            </div>
                            <div class="pane">
                                <form:form>
                                </form:form>
                                <div>
                                    <display:table id="listadoApuestas" name="listaHistorial" pagesize="5" decorator="org.displaytag.decorator.TotalTableDecorator"
                                                   export="true" class="dataTable" requestURI="historialApuestas.htm">
                                        <display:column property="fecha" title="Fecha" sortable="true"/>
                                        <display:column property="eventoNombre" title="Evento" sortable="true"/>
                                        <display:column property="monto" title="Monto($)"  sortable="true"/>
                                        <display:column property="participanteNombre" title="Aposte por" sortable="true"/>
                                        <display:column property="eventoResultado" title="Resultado" sortable="true"/>
                                        <display:column title="Gane?" sortable="true" >
                                        <c:choose>
                                            <c:when test="${listadoApuestas.ganador=='true'}">
                                                Si
                                            </c:when>
                                            <c:when test="${listadoApuestas.ganador=='false'}">
                                                No
                                            </c:when>
                                        </c:choose>
                                    </display:column>
                                    </display:table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="leftColumn"></div>
                <div id="rightColumn"></div>
            </div>
            <jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
        </div>
    </body>
</html>
