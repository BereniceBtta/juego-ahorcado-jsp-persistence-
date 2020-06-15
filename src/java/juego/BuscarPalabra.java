/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import controles.PalabrasJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import modelo.Palabras;

/**
 *
 * @author BereniceAnaya
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

@WebServlet(name = "BuscarPalabra", urlPatterns = {"/BuscarPalabra"})
public class BuscarPalabra extends HttpServlet {

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
        emf = Persistence.createEntityManagerFactory("juegoPU");
        PalabrasJpaController controlPalabras = new PalabrasJpaController(utx, emf);
        Palabras palabra = new Palabras();
        int id = 0;
        String cad = request.getParameter("id").toUpperCase();

        try {
            palabra = controlPalabras.findPalabrasNombre(cad);
            if (palabra != null) {
                response.sendRedirect("mostrarPalabra.jsp?"
                        + "idPalabra=" + palabra.getIdpalabra().toString()
                        + "&nombre=" + palabra.getPalabra()
                        + "&tipo=" + palabra.getTipo().getIdtipos().toString()
                        + "&nivel=" + String.valueOf(palabra.getNivel()));
            } else {
                response.sendRedirect("menuBusqueda_palabra.jsp");
            }
        } catch (Exception e) {
            response.sendRedirect("menuBusqueda_palabra.jsp");
            System.out.println(e.getMessage() + "NO EXISTE EL VALOR");
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
