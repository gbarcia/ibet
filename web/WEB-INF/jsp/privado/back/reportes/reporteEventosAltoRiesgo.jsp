<%-- 
    Document   : reporteEventosAltoRiesgo
    Created on : Nov 24, 2009, 6:25:36 PM
    Author     : maya
--%>

<%@page session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iBet | Reporte de Eventos de Alto Riesgo</title>
        <jsp:include page="/WEB-INF/jsp/include/headAdmin.jsp"></jsp:include>
        <script type="text/javascript">
            var nav4 = window.Event ? true : false;
            function acceptNum(evt){
                var key = nav4 ? evt.which : evt.keyCode;
                return (key <= 13 || (key >= 48 && key <= 57));
            }
        </script>
    </head>
    <body>
        <div id="pageWrap">
            <jsp:include page="/WEB-INF/jsp/include/headerAdmin.jsp"></jsp:include>
            <div id="content">
                <div id="contentWrapper">
                    <div id="contentColumn">
                        <div id="centerPane">
                            <div class="paneTitle">
                                Reporte de Eventos de Alto Riesgo
                            </div>
                            <div class="pane">
                                <fieldset>
                                    <legend>Filtros</legend>
                                    <table>
                                        <form:form commandName="reporteEventosAltoRiesgo" >
                                            <tr>
                                                <th>Monto o banda($): </th>
                                                <th> <form:input path="monto" onkeypress="return acceptNum(event)" /></th>
                                                <th><form:errors path="monto"/></th>
                                            </tr>
                                            <tr>
                                                <th>Tipo de Salida:</th>
                                                <th><form:select path="tipoReporte" items="${opcionTipoReporte}"/></th>                                                
                                            </tr>
                                            <tr>
                                                <th><input type="submit" value="Consultar"></th>
                                                <td><a href="homeReportes.htm">Volver</a></td>
                                            </tr>
                                        </form:form>
                                    </table>
                                </fieldset>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="/WEB-INF/jsp/include/footerAdmin.jsp"></jsp:include>
        </div>
    </body>
</html>