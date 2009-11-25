<%-- 
    Document   : eventos
    Created on : Nov 24, 2009, 9:43:36 AM
    Author     : jonathan
--%>
<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iBet | <c:out value="${eventos[0].idCategoria.nombre}"/></title>
        <jsp:include page="/WEB-INF/jsp/include/head.jsp"></jsp:include>
        <script type="text/javascript">
            $(document).ready(function() {
                var active = null;

                $(".tablero .item").click(function() {
                    if (active != null){
                        $('#'.concat(active)).removeClass().addClass("item");
                    }

                    active = $(this).attr("id");
                    var arregloIds = active.split('-');
                    $("#idEvento").val(arregloIds[0]);
                    $("#idParticipante").val(arregloIds[1]);
                    $("#nombreParticipante").text($(this).find(".participante").text());
                    $("#proporcion").text($(this).find(".proporcion").text());
                    $(this).removeClass().addClass("itemClick");
                });

                $(".tablero .item").hover(
                function() {
                    if ($(this).attr("id") != active){
                        $(this).removeClass().addClass("itemHover");
                    }
                },
                function() {
                    if ($(this).attr("id") != active){
                        $(this).removeClass().addClass("item");
                    }
                })

            });
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
                                                <c:if test="${evento.idCategoria.empate == true}">
                                                    <td id="${evento.id}-0" class="item" width="10%">
                                                        <div class="participante">x</div>
                                                        <div class="proporcion"><c:out value="${proporcionEmpate}"/></div>
                                                    </td>
                                                </c:if>
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
                    <div class="demo">
                        <div id="slip">
                            <form>
                                <div class="slipTitle"><spring:message code="eventos.betslip"/></div>
                                <div class="slipContent">
                                    <input id="idEvento" type="hidden" name="" value="" />
                                    <input id="idParticipante" type="hidden" name="" value="" />
                                    <spring:message code="eventos.pick"/>:
                                    <div id="pick">
                                        <div id="nombreParticipante"></div>
                                        <div id="proporcion"></div>
                                    </div>
                                    <hr>
                                    <table width="100%" cellspacing="5">
                                        <tr>
                                            <td><spring:message code="eventos.stake"/></td>
                                            <td><input type="text" name="" value=""/></td>
                                        </tr>
                                        <tr>
                                            <td><spring:message code="eventos.winnings"/></td>
                                            <td><input type="text" name="" value=""/></td>
                                        </tr>
                                    </table>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
        </div>
    </body>
</html>
