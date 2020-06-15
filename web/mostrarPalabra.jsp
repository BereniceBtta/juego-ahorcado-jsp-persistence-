<%-- 
    Document   : mostrarPalabra
    Created on : 2/06/2020, 01:02:59 AM
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
<% String id = request.getParameter("idPalabra");%>
<% String tipo = request.getParameter("tipo");%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mostrar</title>
    </head>
    <body style="background: pink;">
    <center>
        <h1>EDITAR O ELIMINAR PALABRA</h1>
        <form method="POST" action="Operacion">
            <table align="center"  style="border-style: blue">                
                <tbody>
                    <tr>
                        <td> Id:</td> 
                        <td><input type="text" name="idPalabra" value='<%= request.getParameter("idPalabra")%>' readonly="readonly" style="padding:10px; border-radius:15px;"></td>
                    </tr>
                    <tr>
                        <td>Palabra:</td>
                        <td><input  type="text" name="palabra" id="palabra" value='<%= request.getParameter("nombre")%>' style="padding:10px; border-radius:15px;"></td>
                    </tr>
                    <tr>
                        <td>Tipo:</td>
                        <td><select name="tipo" style="padding:10px; border-radius:15px;" onselect="" >
                                <% for (Tipos t : lista) {
                                        // if(t.getIdtipos() == Integer.parseInt(request.getParameter("tipo"))){%>
                                <option  value='<%=t.getIdtipos()%>' <%--selected--%>> 
                                    <%= t.getDescripcion()%></option>
                                    <% }%><%--}--%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Dificultad:</td>
                        <td> 
                            <select style="padding:10px; border-radius:15px;" name="nivel" >
                                <option value="1">Fácil</option>
                                <option value="2">Intermedio</option>
                                <option value="3">Difícil</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2"> <input type="submit" class="btn btn-primary" name="accion" value="Editar" id="Editar" style="padding:10px; border-radius:15px;"></th>
                    </tr>
                    <tr>
                        <th colspan="2"> <input type="submit" name="accion" class="btn btn-success" value="Eliminar" id="Eliminar" style="padding:10px; border-radius:15px;"></th>
                    </tr>
                </tbody>
            </table>
        </form>    
        <br/><br/>
        <a href="menuBusqueda_palabra.jsp" style="padding:10px; border-radius:15px;">BUSCAR OTRA PALABRA</a> 
        <br/>
        <a href="index_nuevo.jsp" style="padding:10px; border-radius:15px;">REGRESAR</a> 
    </center>
</body>
</html>