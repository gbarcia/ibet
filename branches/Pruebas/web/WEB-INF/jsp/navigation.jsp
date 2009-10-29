<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<div id="navcontainer">
<ul id="navlist">
    <security:authorize ifAllGranted="ROLE_USER">
    <li class="home"><a href="home.htm">Home</a></li>
    </security:authorize>
    <security:authorize ifAllGranted="ROLE_ADMIN">
    <li><a href="admin.htm">Admin</a></li>
    <li><a href="agregarcliente.htm">Agregar Cliente</a></li>
    <li><a href="pruebaReporte.pdf">Ver Reporte Clientes (pdf)</a></li>
    <li><a href="pruebaReporteXLS.xls">Ver Reporte Clientes (EXCEL)</a></li>
    </security:authorize>
    <li><a href="logout.htm">Logout</a></li>
</ul>
</div>
