<%-- 
    Document   : eventos
    Created on : Nov 24, 2009, 9:43:36 AM
    Author     : jonathan
--%>
<%@page session="true" %>
<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iBet | <c:out value="${eventos[0].idCategoria.nombre}"/></title>
        <jsp:include page="/WEB-INF/jsp/include/head.jsp"></jsp:include>
        <script type="text/javascript" src="<%= request.getContextPath() + "/js/jquery/jquery.ibet.front.js"%>"></script>
        <script type="text/javascript">
            $(function(){
                $("input.numeric").numeric(".", numberInvalid);
            });
            function numberInvalid(){
                alert("'" + $(this).val() + "' is not a valid number");
            }
        </script>
    </head>
    <body>
        <div id="pageWrap">
            <jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
            <div id="content">
                <div id="contentWrapper">
                    <div id="contentColumn">
                        <div id="centerPane">
                            <div class="paneTitle">
                                iBet: <c:out value="${eventos[0].idCategoria.nombre}"/>
                            </div>
                            <div class="pane">
                                <table id="tablaEventos" class="eventTable" cellspacing="5">
                                    <tbody>
                                        <c:forEach items="${eventos}" var="evento">
                                            <c:if test="${evento.fecha != ultimaFecha}">
                                                <tr>
                                                    <td class="fecha" colspan="4"><c:out value="${evento.fecha}"/></td>
                                                </tr>
                                            </c:if>
                                            <tr id="${evento.nombre}" class="visible">
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
                                                <c:if test="${evento.idCategoria.empate == true}">
                                                    <td id="${evento.id}-0" class="item" width="10%">
                                                        <div class="participante">x</div>
                                                        <div class="proporcion"><c:out value="${proporcionEmpate}"/></div>
                                                    </td>
                                                </c:if>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${evento.proporcion != null}">
                                                            <img id="mas-${evento.id}" class="mas" src="<%= request.getContextPath() + "/images/icons/mas.png"%>" width="16" height="16" alt="+"/>
                                                            <img id="menos-${evento.id}" class="menos" src="<%= request.getContextPath() + "/images/icons/menos.png"%>" width="16" height="16" alt="-"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            &nbsp;
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                            <c:if test="${evento.proporcion != null}">
                                                <tr id="expanded-${evento.id}" class="hidden">
                                                    <td class="logo" width="10%">
                                                        <img src="<%= request.getContextPath() + "/images/icons/ubet.png"%>" width="52" height="16" alt="uBet"/>
                                                    </td>
                                                    <td class="ubet" width="40%">
                                                        <div class="participante"><c:out value="${tablero.participante.nombre}"/></div>
                                                        <div class="proporcion"><c:out value="${evento.proporcion.proporcionEquipoUno}"/></div>
                                                    </td>
                                                    <td class="ubet" width="40%">
                                                        <div class="participante"><c:out value="${tablero.participante.nombre}"/></div>
                                                        <div class="proporcion"><c:out value="${evento.proporcion.proporcionEquipoDos}"/></div>
                                                    </td>
                                                    <td>&nbsp;</td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                            </c:if>
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
                    <jsp:include page="../../../include/slip.jsp"></jsp:include>
                </div>
            </div>
            <jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
        </div>
    </body>
</html>
