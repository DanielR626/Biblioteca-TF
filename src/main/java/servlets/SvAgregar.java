/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.umariana.biblioteca.Biblioteca;
import com.umariana.biblioteca.Libros;
import com.umariana.biblioteca.Usuarios;
import com.umariana.biblioteca.controlUsuario;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author daniel
 */
// Anotación que indica que esta clase es un servlet y define su nombre y patrón de URL
@WebServlet(name = "SvAgregar", urlPatterns = {"/SvAgregar"})
// Anotación que indica que este servlet puede procesar solicitudes multipartes (por ejemplo, subir archivos)
@MultipartConfig
public class SvAgregar extends HttpServlet {

    // Método para procesar solicitudes, no utilizado en este caso
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // Método que maneja solicitudes GET, llama a processRequest, no se utiliza
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // Método que maneja solicitudes POST (envío de formulario)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtiene la sesión actual, si existe
        HttpSession session = request.getSession(false);

        // Imprime en la consola un mensaje con la sesión actual
        System.out.println("ya vamos a agregar Libro: " + session);

        // Obtiene el contexto del servlet
        ServletContext context = getServletContext();

        // Obtiene el nombre de usuario y la cédula de la sesión
        String nombreUsuario = (String) session.getAttribute("nombre_usuario");
        String cedulaUsuario = (String) session.getAttribute("cedula_usuario");

        // Obtiene el usuario activo a partir del nombre de usuario y la cédula
        Usuarios usuarioActivo = controlUsuario.obtenerUsuarioActivo(nombreUsuario, cedulaUsuario, context);

        // Imprime en la consola el nombre de usuario activo
        System.out.println(usuarioActivo.getNombre_usuario());

        // Obtiene los parámetros del formulario
        String id = request.getParameter("id");
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String anio = request.getParameter("anio");
        String estado = request.getParameter("estado");

        // Obtiene la parte correspondiente a la imagen del formulario
        Part imagenPart = request.getPart("fotos");
        System.out.println("imagenPart" + imagenPart);

        // Nombre original del archivo de imagen
        String fileName = imagenPart.getSubmittedFileName();
        System.out.println("fileName: " + fileName);

        // Directorio donde se almacenará el archivo de imagen
        String uploadDirectory = getServletContext().getRealPath("imagenes");
        System.out.println("uploadDirectory: " + uploadDirectory);

        // Ruta completa del archivo de imagen
        String filePath = uploadDirectory + File.separator + fileName;
        System.out.println("filePath: " + filePath);

        // Guarda el archivo de imagen en el sistema de archivos
        try (InputStream input = imagenPart.getInputStream(); OutputStream output = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        }

        // Guarda el nombre del archivo de imagen
        String foto = fileName;

        // Verifica si ya existe un libro con el mismo ID
        if (!Biblioteca.existeLibroConId(id)) {
            // Crea un nuevo libro
            Libros nuevoLibro = new Libros(id, titulo, autor, anio, foto, estado);

            // Agrega el nuevo libro al usuario activo
            usuarioActivo.agregarLibro(nuevoLibro);

            // Agrega el nuevo libro a la biblioteca general
            Biblioteca.agregarLibros(nuevoLibro);

            // Guarda la lista de libros en un archivo
            Biblioteca.guardarLibrosEnArchivo(context);
        }

        // Guarda la lista de libros en la sesión
        session.setAttribute("libros", Biblioteca.obtenerTodosLosLibros());

        // Redirige a la página de la biblioteca
        response.sendRedirect("biblioteca.jsp");
    }

    // Método que devuelve información sobre el servlet, no utilizado en este caso
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
