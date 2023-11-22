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

/**
 *
 * @author Brian
 */
@WebServlet(name = "SvSearch", urlPatterns = {"/SvSearch"})
public class SvSearch extends HttpServlet {

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
        ServletContext context = getServletContext();
        String nombre = request.getParameter("titulo_libro");
        Biblioteca.cargarLibrosDesdeArchivo(context);
        Libros libroBuscado = Biblioteca.buscarLibroPorTitulo(nombre);

        //System.out.println("holi desde el metodo post en buscar " +libroBuscado.getFoto());
        if (libroBuscado != null) {
            // Genera la respuesta HTML con los detalles del perro
            //System.out.println("desde buscar se ha contrado el libor deseado " + libroBuscado.getTitulo());
            String perroHtml = "<h2>Titulo: " + libroBuscado.getTitulo() + "</h2>"
                    + "<p>Año publicacion: " + libroBuscado.getAnio() + "</p>"
                    + "<p>Autor: " + libroBuscado.getAutor() + "</p>"
                    + "<p>Estado: " + libroBuscado.getEstado() + "</p>"
                    + "<img src='./imagenes/" + libroBuscado.getFoto() + "' alt='" + libroBuscado.getTitulo() + "' width='100%'/>";
            response.setContentType("text/html");
            response.getWriter().write(perroHtml);
        } else {
            // Maneja el caso en el que no se encuentra el perro
            response.setContentType("text/plain");
            response.getWriter().write("libro no encontrado");
        }

    }
//./imagenes/<%=libro.getFoto()%>
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
