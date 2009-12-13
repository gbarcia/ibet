<%-- 
    Document   : admin
    Created on : 25/11/2009, 08:18:53 AM
    Author     : nath
--%>
<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iBet | Administraci&oacute;n</title>
        <jsp:include page="/WEB-INF/jsp/include/head.jsp"></jsp:include>
        <script type="text/javascript" src="<%= request.getContextPath() + "/js/jquery/jquery-1.3.2.min.js"%>"></script>
        <link rel="stylesheet" href="<%= request.getContextPath() + "/css/admin.css"%>" type="text/css" />
        <script type="text/javascript" src="<%= request.getContextPath() + "/js/jquery/jquery.tablero.js"%>"></script>
        <script type="text/javascript" src="<%= request.getContextPath() + "/js/jquery/jquery.slider.js"%>"></script>
    </head>
    <body>
        <div id="pageWrap">
            <jsp:include page="/WEB-INF/jsp/include/headerAdmin.jsp"></jsp:include>
            <div id="content">
                <div id="contentWrapper">
                    <div id="contentColumn">
                        <div id="centerPane">
                            <div class="paneTitle">
                                Administraci&oacute;n
                            </div>
                            <div class="pane">
                                <h1><a href="reportes/homeReportes.htm">Reportes</a></h1>
                                <h1><a href="categoria/homeCategoria.htm">Gestion Categorias</a></h1>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
        </div>
    </body>
</html>