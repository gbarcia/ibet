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
                                <c:forEach items="${eventos}" var="evento">
                                    <%--<div class="itemTablero"><c:out value="${evento.nombre}"/></div>--%>
                                    <div class="itemTablero"><c:out value="${evento.fecha}"/>
                                    <c:out value="${evento.hora}"/></div>
                                    <c:forEach var="tablero" items="${evento.tableroGananciaCollection}">
                                        <div class="itemTablero"><c:out value="${tablero.participante.nombre}"/>
                                        <c:out value="${tablero.propocionGano}"/></div>
                                        <c:set var="proporcionEmpate" value="${tablero.proporcionEmpate}"/>
                                    </c:forEach>
                                    <c:if test="${evento.idCategoria.empate == true}">
                                        <div class="itemTablero">Empate: <c:out value="${proporcionEmpate}"/></div>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="leftColumn">
                    <c:import url="/include/sideMenu.htm" />
                </div>
                <div id="rightColumn">

                </div>
            </div>
            <jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
        </div>
    </body>
</html>
