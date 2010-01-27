<%-- 
    Document   : error
    Created on : 02/01/2010, 10:10:33 PM
    Author     : nath
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iBet | 404</title>
        <jsp:include page="/WEB-INF/jsp/include/head.jsp"></jsp:include>
        <link rel="stylesheet" href="<%= request.getContextPath() + "/css/slider.css"%>" type="text/css" />
    </head>
    <body>
        <div id="pageWrap">
            <jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
            <div id="content">
                <div id="contentWrapper">
                    <div id="contentColumn">
                        <div id="centerPane">
                            <div class="paneTitle">
                                404
                            </div>
                            <div id="sliderContainer">
                                <img src="<%= request.getContextPath() + "/images/error/errorMaldo.jpg"%>" width="850" height="400" alt="Error"/>
                                <div class="textoEvento">Maldo no consigui&oacute; la p&aacute;gina que estas buscando.
                                    <a href="javascript:history.go(-1)">Regresa aqu&iacute;.</a>
                                    <div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
        </div>
    </body>
</html>