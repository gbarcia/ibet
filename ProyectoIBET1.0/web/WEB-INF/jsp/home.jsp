<%-- 
    Document   : index
    Created on : 24/10/2009, 08:55:39 PM
    Author     : Gerardo Barcia
--%>
<%@include file="include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                                <input id="idEvento" type="text" name="" value="" />
                                <input id="idParticipante" type="text" name="" value="" />
                            </div>
                            <div id="slipBottom">
                                asd
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
        </div>
    </body>
</html>
