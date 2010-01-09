<%-- 
    Document   : tableroApuesta
    Created on : 26/11/2009, 04:48:24 PM
    Author     : Gerardo Barcia
--%>

<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iBet | <spring:message code="apuesta.titulo.form"/></title>
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
                                <spring:message code="apuesta.titulo.form"/>
                            </div>
                            <div class="pane">
                                <form:form commandName="apuesta">
                                    <fieldset>
                                        <legend><spring:message code="apuesta.datos"/></legend>      
                                        <form:errors path="*" cssClass="error"/>
                                        <p></p>
                                        <table>
                                            <tbody>
                                                <tr>
                                                    <th><label for="fechaEvento"><spring:message code="apuesta.datos.fecha"/></label></th>
                                                    <td><form:input id="fechaEvento" path="fechaEvento" autocomplete="true" readonly="true"/></td>
                                                </tr>
                                                <tr>
                                                    <th><label for="nombreEvento"><spring:message code="apuesta.datos.nombreEvento"/></label></th>
                                                    <td><form:input id="nombreEvento" path="nombreEvento" readonly="true"/></td>
                                                </tr>
                                                <tr>
                                                    <th><label for="apostePor"><spring:message code="apuesta.datos.apostePor"/></label></th>
                                                    <td><form:input id="apostePor" path="apostePor" autocomplete="true" readonly="true"/></td>
                                                </tr>
                                                <tr>
                                                    <th><label for="montoApuesta"><spring:message code="apuesta.datos.montoApuesta"/></label></th>
                                                    <td><form:input id="montoApuesta" path="montoApuesta" autocomplete="true"/></td>
                                                </tr>
                                                <tr>
                                                    <th><label for="nombreMetodoPago"><spring:message code="apuesta.datos.nombreMetodoPago"/></label></th>
                                                    <td><form:select path="nombreMetodoPago" items="${opcionMetodosPago}" /></td>
                                                </tr>
                                                <tr>
                                                    <th>&nbsp;</th>
                                                    <td><input type="submit" value="<spring:message code="apuesta.datos.submit"/>"/></td>
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

