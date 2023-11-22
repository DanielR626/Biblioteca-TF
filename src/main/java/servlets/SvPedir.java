/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.umariana.biblioteca.Biblioteca;
import com.umariana.biblioteca.Libros;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Daniel
 */
@WebServlet(name = "SvPedir", urlPatterns = {"/SvPedir"})
public class SvPedir extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        System.out.println("id del libro a pedir " + id);
        Libros libroPedir = Biblioteca.buscarLibroPorId(id);
        
        HttpSession session = request.getSession(false);
        ServletContext context = getServletContext();

        String nombreUsuario = (String) session.getAttribute("nombre_usuario");
        //String cedulaUsuario = (String) session.getAttribute("cedula_usuario");
        
        if (libroPedir != null && libroPedir.getId().equals(id)) {
            
            libroPedir.setEstado("No disponible");
            
            Biblioteca.eliminarLibro(libroPedir, context);

            Biblioteca.agregarLibroEnArchivoPedido(context, nombreUsuario,libroPedir);
            
            
        }
        request.getRequestDispatcher("biblioteca.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
