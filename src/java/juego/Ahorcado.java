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
import java.util.Random;
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
 * @author Berenice
 */
public class Ahorcado extends HttpServlet {

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
        HttpSession sesion = request.getSession();

        String usuario = (String) sesion.getAttribute("nombre");
        int maxint = Integer.parseInt((String) sesion.getAttribute("maxint"));
        String palabra = (String) sesion.getAttribute("palabra");
        int tipoPregunta = Integer.parseInt((String) sesion.getAttribute("tipo"));
        String aciertos;
        String errados;

        int nivel = 0;
        switch ((String) sesion.getAttribute("niveles")) {
            case "Facil":
                nivel = 1;
                break;
            case "Intermedio":
                nivel = 2;
                break;
            case "Dificil":
                nivel = 3;
                break;
            default:
                nivel = 1;
        }
        // Integer.parseInt((String) sesion.getAttribute("diff"));
        emf = Persistence.createEntityManagerFactory("juegoPU");
        PalabrasJpaController p = new PalabrasJpaController(utx, emf);
        List<Palabras> lista = p.getNivel(tipoPregunta, nivel);

        if (palabra == null) {
            Random aleatorio = new Random();
            palabra = lista.get(aleatorio.nextInt(lista.size())).getPalabra();
            palabra = palabra.toUpperCase();
            aciertos = "";
            errados = "";
            sesion.setAttribute("palabra", palabra);
            sesion.setAttribute("aciertos", aciertos);
            sesion.setAttribute("errados", errados);
        } else {
            aciertos = (String) sesion.getAttribute("aciertos");
            errados = (String) sesion.getAttribute("errados");
            String letra = request.getParameter("letra");
            if (palabra.indexOf(letra) >= 0) {
                aciertos += letra;
            } else {
                errados += letra;
            }
            sesion.setAttribute("aciertos", aciertos);
            sesion.setAttribute("errados", errados);
        }

        //VISTA
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Juego del Ahorcado - Intento #" + (maxint - errados.length()) + "</title>");
            out.println("</head>");
            out.println("<body style=\"background: pink;\">");
            out.println("<center>");
            out.println("<h2 style=\"font-family: cursive; font-size: 28px;\">JUEGO</h2>");
            out.println("<h3 style=\"font-family: cursive; font-size: 18px;\">Selecciona una letra</h3>");
            boolean terminado = true;

            out.println("<h2>");
            for (int i = 0; i < palabra.length(); i++) {
                String letra = palabra.substring(i, i + 1);
                if (aciertos.indexOf(letra) >= 0) {
                    out.println("" + letra);
                } else {
                    out.println("" + "_");
                    terminado = false;
                }
            }
            out.println("</h2>");

            if (maxint > errados.length()) {
                out.println("<br/><br/><br/>");
                for (char car = 'A'; car <= 'Z'; car++) {
                    if (aciertos.indexOf(car) == -1 && errados.indexOf(car) == -1) {
                        out.println("<a href=Ahorcado?letra=" + car + ">" + car + "</a>");
                    }
                }
                //mensaje de ayuda
                if (maxint == maxint) {
                    for (int letra = 0; letra < palabra.length(); letra++) {
                        if(letra == 0){
                          out.println("<p>La letra con la que inicia es: " + palabra.substring(0, 1) + " </p>");  
                        }else if(letra == palabra.length()-(palabra.length()-1)){
                          out.println("<p>La letra con la que inicia es: " + palabra.substring(1, 2) + " </p>");  
                        }else if(letra == palabra.length()-(palabra.length()-2)){
                          out.println("<p>La letra con la que inicia es: " + palabra.substring(2, 3) + " </p>");  
                        }else if(letra == palabra.length()-(palabra.length()-3)){
                          out.println("<p>La letra con la que inicia es: " + palabra.substring(3, 4) + " </p>");  
                        }
                    }
                }

                out.println("<br/>");
                out.println("<h2 style=\"font-family: cursive; font-size: 18px;\">" + "Oportunidades de errar: " + (maxint - errados.length()) + "</h2>");
            } else {
                sesion.invalidate();
                out.println("<br/><h3 style=\"font-family: cursive; font-size: 18px;\"> JUEGO TERMINADO </h2>");
                out.println("<br/><a href='index_nuevo.jsp'>Regresar</a>");
            }
            if (terminado) {
                sesion.invalidate();
                out.println("<br/><h1 style=\"font-family: cursive; font-size: 28px;\"> JUEGO COMPLETO</h1>");
                out.println("<br/><a href='index_nuevo.jsp'>Regresar</a>");
            }
            out.println("<center>");
            out.println("</body");
            out.println("</html>");
        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            out.close();
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
