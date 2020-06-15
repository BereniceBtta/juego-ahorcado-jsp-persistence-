<%-- 
    Document   : acceso
    Created on : 25/05/2020, 11:46:55 PM
    Author     : BereniceAnaya
--%>

<%@page import="java.util.List"%>
<%@page import="controles.TiposJpaController"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.transaction.UserTransaction"%>
<%@page import="javax.annotation.Resource"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="javax.persistence.PersistenceUnit"%>
<%@page import="modelo.Tipos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%!
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Resource
    private UserTransaction utx;
%>
<% emf = Persistence.createEntityManagerFactory("juegoPU");
    TiposJpaController tipos = new TiposJpaController(utx, emf);
    List<Tipos> lista = tipos.findTiposEntities();
%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Acceso</title>
    </head>
    <body style="background: pink;">
        <!--  <h1 style="font-family: cursive; font-size: 38px;">"ACCEDER AL JUEGO"</h1> -->
    <center>
        <h1 style="font-family: cursive; font-size: 38px;">Acceder</h1>
        <h2 style="font-family: cursive; font-size: 28px;">Bienvenido <%=request.getParameter("nombre")%> </h2>
        <h2 style="font-family: cursive; font-size: 28px;">Nivel de juego: <%= request.getParameter("niveles[]")%> </h2>
       

        <%
            session = request.getSession();
            session.setAttribute("maxint", request.getParameter("nintento"));
            session.setAttribute("niveles", request.getParameter("niveles[]"));
            session.setAttribute("tipo", request.getParameter("tipos"));
%>


        <h3 style="font-family: cursive; font-size: 18px;"> ¿ TE GUSTARÍA JUGAR ? 
            <a href="index.jsp">NO</a>
            <a href="Ahorcado">SI</a>
        </h3>
    </center>
</body>
</html>
