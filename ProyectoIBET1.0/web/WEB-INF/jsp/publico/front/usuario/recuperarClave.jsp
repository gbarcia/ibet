<%-- 
    Document   : recuperarClave
    Created on : 19/11/2009, 12:17:40 PM
    Author     : Gerardo Barcia
--%>

<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iBet | <spring:message code="recuperarclave.titulo"/></title>
        <jsp:include page="/WEB-INF/jsp/include/head.jsp"></jsp:include>
    </head>
    <body>
        <div id="pageWrap">
            <jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
            <div id="content">
                <div id="contentWrapper">
                    <div id="contentColumn">
                        <div id="centerPane">
                            <div class="paneTitle">
                                <spring:message code="recuperarclave.titulo"/>
                            </div>
                            <div class="pane">
                                <form:form action="recuperarClave.htm" commandName="confirmacionClave">
                                    <fieldset>
                                        <legend>Introduce tu Datos</legend>
                                        <form:errors path="*"/>
                                        <br>
                                        <table>
                                            <tbody>
                                                <tr>
                                                    <th><label for="username"><spring:message code="usuario.forms.username"/></label></th>
                                                    <td><form:input id="username" path="username" autocomplete="true"/></td>
                                                    <td><form:errors path="username" /></td>
                                                </tr>
                                                <tr>
                                                    <th><label for="correo"><spring:message code="usuario.forms.correo"/></label></th>
                                                    <td><form:input id="correo" path="correo" /></td>
                                                    <td><form:errors path="correo" /></td>
                                                </tr>
                                                <tr>
                                                    <th>&nbsp;</th>
                                                    <td><input type="submit" value="Solicitar"/></td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </fieldset>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
                   <div id="leftColumn"></div>
                   <div id="rightColumn"></div>
            </div>
           <jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
        </div>
    </body>
</html>
