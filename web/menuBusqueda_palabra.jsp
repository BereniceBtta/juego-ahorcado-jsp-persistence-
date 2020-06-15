
<%-- 
    Document   : menuBusqueda_palabra
    Created on : 31/05/2020, 11:35:51 AM
    Author     : BereniceAnaya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscar Palabra</title>
    </head>
    <body style="background: pink;">
    <center>
        <h1>BUSCAR PALABRA</h1>
        <form name="autenticacion" method="POST" action="BuscarPalabra">
            <table align="center"  style="border-style: blue">

                <tbody>
                    <tr>
                        <td> Palabra:</td>
                        <td><input  type="text" style="padding:10px; border-radius:15px" name="id" id="id" required="" ></td>
                    </tr>

                    <tr>
                        <th colspan="2"> <input type="submit" style="padding:10px; border-radius:15px;" class="btn btn-primary" value="Buscar" id="Buscar"></th>
                    </tr>
                </tbody>
            </table>
        </form>
        <br/>
        <a href="index.jsp">REGRESAR</a> 
    </center>
</body>
</html>
