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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iBet | Administraci&oacute;n de Eventos</title>
        <jsp:include page="/WEB-INF/jsp/include/headAdmin.jsp"></jsp:include>
    <body>
        <div id="pageWrap">
            <jsp:include page="/WEB-INF/jsp/include/headerAdmin.jsp"></jsp:include>
            <div id="content">
                <div id="contentWrapper">
                    <div id="contentColumn">
                        <div id="centerPane">
                            <div class="paneTitle">
                                Finalizar Evento
                            </div>
                            <div class="pane">
                                <form:errors path="*" cssClass="error"/>
                                <fieldset>
                                    <legend>Datos</legend>
                                    <table>
                                        <form:form commandName="finalizarEvento">
                                            <tr>
                                                <th><label for="resultado">Resultado:</label></th>
                                                <td><form:input id="resultado" path="resultado"/></td>
                                                <td><form:errors path="resultado" /></td>
                                            </tr>                       
                                            <tr>
                                                <th><label for="partidaEmpatada">Empate:</label></th>
                                                <td><form:radiobuttons id="partidaEmpatada" path="partidaEmpatada" items="${opcionBooleana}"/></td>
                                            </tr>
                                            <tr>
                                                <th><label for="idParticipanteGanador">Participante ganador:</label></th>
                                                <td><form:select id="idParticipanteGanador" path="idParticipanteGanador" items="${participantes}" /></td>
                                            </tr>                                           
                                            <tr>
                                                <th>&nbsp;</th>
                                                <td>
                                                    <input type="submit" value="Registrar">
                                                    <a href="/ProyectoIBET/privado/back/admin.htm">Volver</a>
                                                </td>
                                                <td>&nbsp;</td>
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