<%-- 
    Document   : index
    Created on : 25/05/2020, 11:44:09 PM
    Author     : BereniceAnaya
--%>

<%@page import="java.util.LinkedList"%>
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
        <title>Inicia el juego</title>
    </head>
    <body style="background: pink;">
    <center>
        <h1 style="font-family: cursive; font-size: 28px;">Inicio del juego</h1> 


        <form method="post" action="acceso.jsp" >
            <h6 style="font-family: cursive; font-size: 18px;">Participante: </h6>
            <input type="text" name="nombre" style="padding:10px;border-radius:15px" required/>
            <h6 style="font-family: cursive; font-size: 18px;">Num. Intentos </h6>
            <input type="number" name="nintento" style="padding:10px;border-radius:15px" min="1" required/>
            <h2 style="font-family: cursive; font-size: 28px;">¿En qué nivel deseas jugar? </h2>

            <select name="niveles[]" style="font-family: cursive; font-size: 15px; padding:10px;border-radius:15px"> 
                <%    LinkedList<String> list = new LinkedList<String>();
                    list.add(0, "Facil");
                    list.add(1, "Intermedio");
                    list.add(2, "Dificil");
                    for (int i = 0; i < list.size(); i++) {%>
                <option  value="<%=list.get(i)%>"><%=list.get(i)%></option>
                <% }%>  
            </select>

            <h2 style="font-family: cursive; font-size: 28px;">Categoría de palabra:</h2>
            <select name="tipos" style="padding:10px; border-radius:15px;">
                <% for (Tipos t : lista) {
                    // if(t.getIdtipos() == Integer.parseInt(request.getParameter("tipo"))){%>
                <option  value="<%=t.getIdtipos()%>" <%--selected--%>> 
                    <%= t.getDescripcion()%> </option>
                    <% }%>
            </select>

            <input type="submit" value="Continuar" style="padding:10px;border-radius:15px" />
        </form>
    </center>

</body>
</html>
