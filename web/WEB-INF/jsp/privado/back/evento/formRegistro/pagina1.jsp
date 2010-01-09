<%--
    Document   : agregarCategoria
    Created on : 11/12/2009, 04:04:36 PM
    Author     : Gerardo Barcia
--%>

<%@page session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link rel="stylesheet" href="<%= request.getContextPath() + "/js/hora/ui.core.css"%>" type="text/css" />
        <link rel="stylesheet" href="<%= request.getContextPath() + "/js/hora/ui.timepickr.css"%>" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iBet | Administraci&oacute;n de Eventos</title>
        <jsp:include page="/WEB-INF/jsp/include/headAdmin.jsp"></jsp:include>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath() + "/js/hora/jquery.utils.js"%>"></script>
        <script type="text/javascript" src="<%= request.getContextPath() + "/js/hora/jquery.strings.js"%>"></script>
        <script type="text/javascript" src="<%= request.getContextPath() + "/js/hora/jquery.anchorHandler.js"%>"></script>
        <script type="text/javascript" src="<%= request.getContextPath() + "/js/hora/jquery.ui.all.js"%>"></script>
        <script type="text/javascript" src="<%= request.getContextPath() + "/js/hora/ui.timepickr.js"%>"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#horaMax").click(function(){
                    $('#horaMax').timepickr();
                });
                $('#datepicker').datepicker({
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: 'yy-mm-dd',
                    yearRange: '2010:2020'
                });
            });
        </script>
    <body>
        <div id="pageWrap">
            <jsp:include page="/WEB-INF/jsp/include/headerAdmin.jsp"></jsp:include>
            <div id="content">
                <div id="contentWrapper">
                    <div id="contentColumn">
                        <div id="centerPane">
                            <div class="paneTitle">
                                Nuevo Evento
                            </div>
                            <div class="pane">
                                <form:errors path="*" cssClass="error"/>
                                <fieldset>
                                    <legend>Datos sobre Politicas</legend>
                                    <table>
                                        <form:form commandName="registroEvento">
                                            <tr>
                                                <th><label for="categoria">Categoria:</label></th>
                                                <td><form:select id="categoria" path="categoria.nombre" items="${listaCategorias}" /></td>
                                                <td><form:errors path="categoria.nombre" cssClass="error"/></td>
                                            </tr>                                            
                                            <tr>
                                                <th><label for="finAntes">Finaliza Antes:</label></th>
                                                <td><form:radiobuttons id="finAntes" path="politica.finalizarAntes" items="${finAntes}"/></td>
                                                <td><form:errors path="politica.finalizarAntes" cssClass="error" /></td>
                                            </tr>
                                            <tr>
                                                <th><label for="datepicker">Fecha Maxima Apuesta:</label></th>
                                                <td><form:input id="datepicker" path="fechaMax"  /></td>
                                                <td><form:errors path="fechaMax" cssClass="error" /></td>
                                            </tr>
                                            <tr>
                                                <th><label for="horaMax">HoraMaxima Apuesta:</label></th>
                                                <td><form:input id="horaMax" path="horaMax"/></td>
                                                <td><form:errors path="horaMax"cssClass="error" /></td>
                                            </tr>
                                            <tr>
                                                <th></th>
                                                <th>
                                                    <input type="submit" value="Cancelar" name="_cancel" />
                                                    <input type="submit"value="Siguiente"name="_target1"/>
                                                </th>
                                            </tr>
                                        </table>
                                    </fieldset>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="/WEB-INF/jsp/include/footerAdmin.jsp"></jsp:include>
        </div>
    </body>
</html>