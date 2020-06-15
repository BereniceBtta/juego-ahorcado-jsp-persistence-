<%-- 
    Document   : agregar_palabra
    Created on : 1/06/2020, 05:22:11 AM
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

        <form id="elemento_palabra" action="AgregarPalabras">
            <h3>Agregar palabra</h3>
            <%--palabra--%><br/>
            <output name="opalabra"  style="font-family: cursive; font-size: 28px;"> Palabra: </output>
            <input name="palabra" type="text" style="padding:10px; border-radius:15px; font-family: cursive;" maxlength="20" required>
            <%--tipo--%><br/>

            <input name="ptipo" type="hidden" style="padding:10px; border-radius:15px; font-family: cursive;"
                   value="<%=request.getParameter("tipo")%>">
            <%--nivel--%><br/>
            <output name="onivel"  style="font-family: cursive; font-size: 28px;"> Nivel: </output>
            <input name="pnivel" type="number" style="padding:10px; border-radius:15px; font-family: cursive;" min="1">

            <input type="submit" value="Aceptar" style="padding:10px; border-radius:15px; font-family: cursive;" title="ingresar" >
        </form>
        <%
            session = request.getSession();
            session.setAttribute("tipos", request.getParameter("tipo"));
        %>
        <br>
        <br>
        <%--
    <%@include file="menuBusqueda_palabra.jsp" %> --%>
    </center>
</body>

</html>