<%-- 
    Document   : agregar_tipo_palabra
    Created on : 1/06/2020, 03:05:50 AM
    Author     : BereniceAnaya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Configuración de  juego</title>
    </head>

    <body style="background: pink;">
    <center>
        <h3>Agregar tipo de palabra</h3>
        <form id="atipo" action="AgregarTipos">
            <br/>
            <output name="ttipo" value="Tipo: " style="font-family: cursive; font-size: 28px;"> Descripción </output>
            <br/>
            <input name="tipo" type="text" style="padding:10px; border-radius:15px; font-family: cursive;" maxlength="30" minlength="4" required="true">
            <br/>
            <input type="submit" value="Aceptar" style="padding:10px; border-radius:15px; font-family: cursive;" title="ingresar" >
        </form>
        <%-- <%@include file="agregar_palabra.jsp" %> --%>
        <br>
        <a href="menuBusqueda_palabra.jsp">Buscar palabra</a> 
    </center>
</body>

</html>
