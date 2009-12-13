<%-- 
    Document   : index
    Created on : 24/10/2009, 08:55:39 PM
    Author     : Gerardo Barcia
--%>
<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iBet | <spring:message code="home.titulo"/></title>
        <jsp:include page="/WEB-INF/jsp/include/head.jsp"></jsp:include>
        <script type="text/javascript" src="<%= request.getContextPath() + "/js/jquery/jquery.tablero.js"%>"></script>
        <script type="text/javascript" src="<%= request.getContextPath() + "/js/jquery/jquery.slider.js"%>"></script>
    </head>
    <body>
        <div id="pageWrap">
            <jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
            <div id="content">
                <div id="contentWrapper">
                    <div id="contentColumn">
                        <div id="centerPane">
                            <div class="paneTitle">
                                <spring:message code="home.proximas"/>
                            </div>
                            <div class="pane">
                                <table class="tablero" cellspacing="5">
                                    <tbody>
                                        <c:forEach items="${eventos}" var="evento">
                                            <c:if test="${evento.fecha != ultimaFecha}">
                                                <tr>
                                                    <td class="fecha" colspan="4"><c:out value="${evento.fecha}"/></td>
                                                </tr>
                                            </c:if>
                                            <tr id="${evento.nombre}">
                                                <td class="hora" width="10%">
                                                    <c:out value="${evento.hora}"/>
                                                </td>
                                                <c:forEach var="tablero" items="${evento.tableroGananciaCollection}">
                                                    <td id="${tablero.tableroGananciaPK.idEvento}-${tablero.tableroGananciaPK.idParticipante}" class="item" width="40%">
                                                        <div class="participante"><c:out value="${tablero.participante.nombre}"/></div>
                                                        <div class="proporcion"><c:out value="${tablero.propocionGano}"/></div>
                                                    </td>
                                                    <c:set var="proporcionEmpate" value="${tablero.proporcionEmpate}"/>
                                                    <c:set var="ultimaFecha" value="${evento.fecha}"/>
                                                </c:forEach>
                                                <c:choose>
                                                    <c:when test="${evento.idCategoria.empate == true}">
                                                        <td id="${evento.id}-0" class="item" width="10%">
                                                            <div class="participante">x</div>
                                                            <div class="proporcion"><c:out value="${proporcionEmpate}"/></div>
                                                        </td>
                                                    </c:when>
                                                    <c:otherwise><td>&nbsp;</td></c:otherwise>
                                                </c:choose>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="leftColumn">
                    <c:import url="/include/sideMenu.htm" />
                </div>
                <div id="rightColumn">
                    <div id="slip">
                        <form action="">
                            <div class="slipTitle"><spring:message code="eventos.betslip"/></div>
                            <div id="slipContent" class="slipContent">
                                <div id="slipNoBet"><spring:message code="eventos.nobet"/></div>
                                <div id="slipForm">
                                    <input id="idEvento" type="hidden" name="" value="" />
                                    <input id="idParticipante" type="hidden" name="" value="" />
                                    <spring:message code="eventos.pick"/>:
                                    <div id="pick">
                                        <div id="nombreParticipante"></div>
                                        <div id="proporcion"></div>
                                    </div>
                                    <div id="nombreEvento"></div>
                                    <hr>
                                    <table width="100%" cellspacing="5">
                                        <tr>
                                            <td><spring:message code="eventos.stake"/></td>
                                            <td><input class="importe" type="text" name="" value=""/></td>
                                        </tr>
                                        <tr>
                                            <td><spring:message code="eventos.winnings"/></td>
                                            <td><div class="ganancias"></div></td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td><input class="botonApostar" type="button" value="<spring:message code="eventos.button"/>" /></td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
            <c:if test="${param.mensaje == 'exito.apuesta'}">
                <div id="alert"><spring:message code="exito.apuesta"/></div>
            </c:if>
        </div>
    </body>
</html>