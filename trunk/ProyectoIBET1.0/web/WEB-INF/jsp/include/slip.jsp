<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<div id="slip">
    <div id="slipTitle"><spring:message code="eventos.betslip"/></div>
    <div id="slipContent">
        <div id="slipNoBet"><spring:message code="eventos.nobet"/></div>
        <div id="slipForm">
            <input id="idEvento" type="hidden" name="" value="" />
            <input id="idParticipante" type="hidden" name="" value="" />
            <spring:message code="eventos.pick"/>:
            <div id="prediccion">
                <div id="nombreParticipante"></div>
                <div id="proporcion"></div>
            </div>
            <div id="nombreEvento"></div>
            <hr>
            <table width="100%" cellspacing="5">
                <tr>
                    <td><spring:message code="eventos.stake"/></td>
                <td><input id="importe" type="text" name="" value=""/></td>
                </tr>
                <tr>
                    <td><spring:message code="eventos.winnings"/></td>
                <td><div id="ganancias"></div></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><input id="botonApostar" type="button" value="<spring:message code="eventos.button"/>" /></td>
                </tr>
            </table>
        </div>
    </div>
</div>