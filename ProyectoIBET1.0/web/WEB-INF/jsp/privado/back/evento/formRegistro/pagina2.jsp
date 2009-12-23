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
                $("#hora").click(function(){
                    $('#hora').timepickr();
                });
                $('#datepicker').datepicker({
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: 'yy-mm-dd',
                    yearRange: '1950:2020'
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
                                    <legend>Datos sobre el Evento</legend>
                                    <table>
                                        <form:form commandName="registroEvento" enctype="multipart/form-data">
                                            <tr>
                                                <th><label for="datepicker">Fecha:</label></th>
                                                <td><form:input id="datepicker" path="fechaEvento"  /></td>
                                                <td><form:errors path="fechaEvento" cssClass="error" /></td>
                                            </tr>
                                            <tr>
                                                <th><label for="hora">Hora:</label></th>
                                                <td><form:input id="hora" path="horaEvento"/></td>
                                                <td><form:errors path="horaEvento" /></td>
                                            </tr>
                                            <tr>
                                                <th><label for="nombre">Nombre:</label></th>
                                                <td><form:input id="nombre" path="nombreEvento"/></td>
                                                <td><form:errors path="nombreEvento" /></td>
                                            </tr>
                                            <tr>
                                                <th><label for="p1">Participante uno:</label></th>
                                                <td><form:select path="participanteUno.id" items="${listaParticipantes}"/></td>
                                                <td><form:errors path="participanteUno.id" cssClass="error" /></td>
                                            </tr> 
                                            <tr>
                                                <th><label for="p1">Participante dos:</label></th>
                                                <td><form:select path="participanteDos.id" items="${listaParticipantes}"/></td>
                                                <td><form:errors path="participanteDos.id" cssClass="error" /></td>
                                            </tr>

                                            <tr>
                                                <th><label for="imagen">Imagen:</label></th>
                                                <td><input id="imagenEvento" type="file" name="imagenEvento"></td>
                                            </tr>
                                            <tr>
                                                <th></th>
                                                <th>
                                                    <input type="submit" value="Cancelar" name="_cancel" />
                                                    <input type="submit" value="Atras" name="_target0" />
                                                    <input type="submit"value="Siguiente"name="_target2"/>
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