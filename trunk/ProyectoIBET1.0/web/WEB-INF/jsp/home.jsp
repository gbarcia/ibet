<%-- 
    Document   : index
    Created on : 24/10/2009, 08:55:39 PM
    Author     : Gerardo Barcia
--%>
<%@include file="include/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iBet</title>
        <jsp:include page="include/head.jsp"></jsp:include>
        <script type="text/javascript">
            $(function() {
                $(".tablero td").click(function() {
                    var id = $(this).attr('id');
                    $("#tableroSeleccionado").replaceWith("<div id='tableroSeleccionado'>"+id+"</div>");
                });
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
                                iBet
                            </div>
                            <div class="pane">
                                <table class="tablero" cellspacing="5">
                                    <tbody>
                                        <c:forEach items="${eventos}" var="evento">
                                            <tr>
                                                <td class="itemTableroHora">
                                                    <c:out value="${evento.hora}"/>
                                                </td>
                                                <c:forEach var="tablero" items="${evento.tableroGananciaCollection}">
                                                    <td id="${tablero.tableroGananciaPK}" class="itemTablero">
                                                        <div class="itemTableroParticipante"><c:out value="${tablero.participante.nombre}"/></div>
                                                        <div class="itemTableroProporcion"><c:out value="${tablero.propocionGano}"/></div>
                                                    </td>
                                                    <c:set var="proporcionEmpate" value="${tablero.proporcionEmpate}"/>
                                                </c:forEach>
                                                <c:if test="${evento.idCategoria.empate == true}">
                                                    <td class="itemTableroX">
                                                        <div class="itemTableroParticipante">x</div>
                                                        <div class="itemTableroProporcion"><c:out value="${proporcionEmpate}"/></div
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
                            <div class="slipTitle">Asd</div>
                            <div class="slipContent">
                                <span id="tableroSeleccionado">none</span>.
                            </div>
                            <div id="slipBottom">
                                asd
                            </div>
                        </div>
                        <p id="feedback">

                        </p>
                    </div>
                </div>
            </div>
            <jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
        </div>
    </body>
</html>
