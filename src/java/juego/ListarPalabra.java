/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import controles.PalabrasJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import modelo.Palabras;

/**
 *
 * @author BereniceAnaya
 */
public class ListarPalabra extends HttpServlet {

    @PersistenceUnit
    private EntityManagerFactory emf;
    @Resource
    private UserTransaction utx;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            HttpSession session = request.getSession();
            int palabra_busqueda = Integer.parseInt((String) request.getParameter("tipo"));

            emf = Persistence.createEntityManagerFactory("juegoPU");
            PalabrasJpaController controlTipo;
            controlTipo = new PalabrasJpaController(utx, emf);
            List<Palabras> lista = controlTipo.buscarPorTipo(palabra_busqueda); //era la consulta

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet listarPalabras</title>");
            out.println("</head>");
            out.println("<body style=\"background: pink;\" >");
            out.println("<center>");
            // out.println("request: " + request.getParameter("tipo"));

            out.println("<a href=\"agregar_palabra.jsp?tipo="+palabra_busqueda +"\" style=\"padding:10px; border-radius:15px; font-family: cursive;\">Seguir agregando palabras</a>");
            out.println("<br>");
            out.println("<a href=\"ListarTipos\" style=\"padding:10px; border-radius:15px; font-family: cursive;\">Cambiar categoría</a>");
            out.println("<br>");
            out.println("<a href=\"index_nuevo.jsp\" style=\"padding:10px; border-radius:15px; font-family: cursive;\">Regresar al inicio</a>");
            out.println("<br>");

            out.println("<table aling='center' width='60%' border=1>");
            //encabezado de la tabla
            out.println("<tr>"
                    + "<td class='datos'>ID</td>"
                    + "<td class='datos'>Palabra</td>"
                    + "<td class='datos'>Tipo</td>"
                    + "<td class='datos'>Nivel</td>"
                    + "</tr");
            //renglones
            for (Palabras dato : lista) {
                out.println("<tr>"
                        + "<td class='datos'>" + dato.getIdpalabra() + "</td>"
                        + "<td class='datos'>" + dato.getPalabra() + "</td>"
                        + "<td class='datos'>" + dato.getTipo().getDescripcion() + "</td>"
                        + "<td class='datos'>" + dato.getNivel() + "</td>"
                        + "</tr");
            }
            out.println("</table>");
            out.println("</center>");

            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
