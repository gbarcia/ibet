<%-- 
    Document   : reporteCantidadUsuariosPorCategoria
    Created on : Nov 24, 2009, 5:23:31 PM
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
        <title>iBet | Reporte de Ususuarios por Categoria</title>
        <jsp:include page="/WEB-INF/jsp/include/headAdmin.jsp"></jsp:include>
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
        <div id="pageWrap">
            <jsp:include page="/WEB-INF/jsp/include/headerAdmin.jsp"></jsp:include>
            <div id="content">
                <div id="contentWrapper">
                    <div id="contentColumn">
                        <div id="centerPane">
                            <div class="paneTitle">
                                Reporte de Usuarios por Categoria
                            </div>
                            <div class="pane">
                                <fieldset>
                                    <legend>Filtros</legend>
                                    <table>
                                        <form:form commandName="reporteCantidadUsuariosPorCategoria">
                                            <tr>
                                                    <th>Fecha de Inicio:</th>
                                                    <th><form:input id="datepicker" path="fechaInicio" /></th>
                                                    <th><form:errors path="fechaInicio"/></th>
                                                </tr>
                                                <tr>
                                                    <th>Fecha de Fin:</th>
                                                    <th><form:input id="datepicker2" path="fechaFin" /></th>
                                                    <th><form:errors path="fechaFin"/></th>
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
