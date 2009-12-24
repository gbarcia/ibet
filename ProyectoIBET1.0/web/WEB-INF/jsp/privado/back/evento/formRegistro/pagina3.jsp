<%--
    Document   : agregarCategoria
    Created on : 11/12/2009, 04:04:36 PM
    Author     : Gerardo Barcia
--%>

<%@page session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iBet | Administraci&oacute;n de Eventos</title>
        <jsp:include page="/WEB-INF/jsp/include/headAdmin.jsp"></jsp:include>
        <script type="text/javascript">
            var nav4 = window.Event ? true : false;
            function acceptNum(evt){
                var key = nav4 ? evt.which : evt.keyCode;
                return (key <= 13 || (key >= 48 && key <= 57));
            }
            function validar(e) { 
                tecla = (document.all) ? e.keyCode : e.which; 
                if (tecla==8) return true; 
                patron = /[0-9.]/;
                te = String.fromCharCode(tecla);
                return patron.test(te);
            }

        </script>
    <body>
        <div id="pageWrap">
            <jsp:include page="/WEB-INF/jsp/include/headerAdmin.jsp"></jsp:include>
            <div id="content">
                <div id="contentWrapper">
                    <div id="contentColumn">
                        <div id="centerPane">
                            <div class="paneTitle">
                                Nuevo Evento
                            </div>
                            <div class="pane">
                                <form:errors path="*" cssClass="error"/>
                                <fieldset>
                                    <legend>Datos sobre Proporciones</legend>
                                    <table>
                                        <form:form commandName="registroEvento">                                                                                       
                                            <tr>
                                                <th><label for="montoMax">Monto maximo Apuesta:</label></th>
                                                <td><form:input id="montoMax" path="politica.montoMaximo" onkeypress="return validar(event)"/></td>
                                                <td><form:errors path="politica.montoMaximo" /></td>
                                            </tr>
                                            <tr>
                                                <th><label for="pe1">Proporcion equipo uno:</label></th>
                                                <td><input type="text" id="pe1" name="tableroGananciaUno.propocionGano" value="${propEquipoUno}" onkeypress="return validar(event)"
                                                           <c:if test="${logica == 'true'}"> readonly="true"</c:if>/></td>
                                                <td><form:errors path="tableroGananciaUno.propocionGano" cssClass="error" /></td>
                                            </tr>
                                            <tr>
                                                <th><label for="pe2">Proporcion equipo dos:</label></th>
                                                <td><input type="text" id="pe2" name="tableroGananciaDos.propocionGano" value="${propEquipoDos}" onkeypress="return validar(event)"
                                                           <c:if test="${logica == 'true'}"> readonly="true"</c:if>/></td>
                                                <td><form:errors path="tableroGananciaDos.propocionGano" cssClass="error" /></td>
                                            </tr>
                                            <tr>
                                                <c:if test="${empate == 'true'}">
                                                    <th><label for="pe">Proporcion de empate:</label></th>
                                                    <td><input type="text" id="pe" name="tableroGananciaUno.proporcionEmpate" value="${propEmpate}" onkeypress="return validar(event)"
                                                               <c:if test="${logica == 'true'}"> readonly="true"</c:if>/></td>
                                                    <td><form:errors path="tableroGananciaUno.proporcionEmpate" cssClass="error" /></td>
                                                </c:if>
                                            </tr>
                                            <tr>
                                                <th></th>
                                                <th>
                                                    <input type="submit" value="Cancelar" name="_cancel" />
                                                    <input type="submit" value="Atras" name="_target1" />
                                                    <input type="submit"value="Registrar"name="_finish"/>
                                                </th>
                                            </tr>
                                        </table>
                                    </fieldset>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="/WEB-INF/jsp/include/footerAdmin.jsp"></jsp:include>
        </div>
    </body>
</html>