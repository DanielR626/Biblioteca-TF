/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.umariana.biblioteca.Biblioteca;
import com.umariana.biblioteca.Libros;
import com.umariana.biblioteca.Usuarios;
import com.umariana.biblioteca.controlUsuario;
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
 * Servlet encargado de gestionar el cambio de estado de un libro cuando se devuelve.
 * @author Daniel
 */
@WebServlet(name = "SvEstadoLibro", urlPatterns = {"/SvEstadoLibro"})
public class SvEstadoLibro extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // No se implementa nada en el método GET
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el ID del libro a devolver desde la solicitud
        String id = request.getParameter("id");
        System.out.println("ID del libro a devolver: " + id);
        
        // Buscar el libro en la biblioteca por ID
        Libros libroDevolver = Biblioteca.buscarLibroPorId(id);
        
        // Obtener la sesión y el contexto del servlet
        HttpSession session = request.getSession(false);
        ServletContext context = getServletContext();

        // Obtener el nombre y cédula del usuario activo desde la sesión
        String nombreUsuario = (String) session.getAttribute("nombre_usuario");
        String cedulaUsuario = (String) session.getAttribute("cedula_usuario");
        
        // Obtener el usuario activo desde el controlUsuario
        Usuarios usuarioActivo = controlUsuario.obtenerUsuarioActivo(nombreUsuario, cedulaUsuario, context);
        
        // Verificar si se encontró el libro y si el ID coincide
        if (libroDevolver != null && libroDevolver.getId().equals(id)) {
            
            // Cambiar el estado del libro a "Disponible" ya que se está devolviendo
            libroDevolver.setEstado("Disponible");
            
            // Eliminar el libro del archivo de libros pedidos del usuario
            Biblioteca.eliminarLibroPedido(libroDevolver, context, nombreUsuario);

            // Crear un nuevo libro con la información del libro devuelto
            Libros nuevoLibro = new Libros(id, libroDevolver.getTitulo(), libroDevolver.getAutor(), libroDevolver.getAnio(), libroDevolver.getFoto(), libroDevolver.getEstado());
        
            // Agregar el nuevo libro al usuario activo
            usuarioActivo.agregarLibro(nuevoLibro);
                
            // Guardar la información actualizada de la biblioteca en el archivo
            Biblioteca.guardarLibrosEnArchivo(context);
        }
        
        // Redireccionar a la página de la biblioteca
        request.getRequestDispatcher("biblioteca.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para gestionar el cambio de estado de un libro cuando se devuelve.";
    }
}
