/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import modelo.Palabras;
import modelo.Tipos;
import controles.PalabrasJpaController;
import java.io.IOException;
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

/**
 *
 * @author BereniceAnaya
 */
@WebServlet(name = "Operacion", urlPatterns = {"/Operacion"})
public class Operacion extends HttpServlet {

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
        PalabrasJpaController palabras = new PalabrasJpaController(utx, emf);
        Palabras p = new Palabras();
        String sid = null;

        try {
            sid = request.getParameter("accion");

            p.setIdpalabra(Integer.parseInt(request.getParameter("idPalabra")));
            p.setTipo(new Tipos(Integer.parseInt(request.getParameter("tipo"))));
            p.setPalabra(request.getParameter("palabra").toUpperCase());
            p.setNivel(Integer.parseInt(request.getParameter("nivel")));

            if (sid.equals("Eliminar")) {
                palabras.destroy(p.getIdpalabra());
                response.sendRedirect("ListarPalabra?tipo="+request.getParameter("tipo"));
                
            } else if (sid.equals("Editar")) {
                palabras.edit(p);
                response.sendRedirect("ListarPalabra?tipo="+request.getParameter("tipo"));
            } else {
                response.sendRedirect("BuscarPalabra.jsp");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
