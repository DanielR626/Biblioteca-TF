/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.umariana.biblioteca.Usuarios;
import com.umariana.biblioteca.controlUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Samir
 */
@WebServlet(name = "SvRegistro", urlPatterns = {"/SvRegistro"})
public class SvRegistro extends HttpServlet {

    ArrayList<Usuarios> UsuarioIngresar = new ArrayList<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("usuario");
        String contrasenia = request.getParameter("contrasenia");
        ServletContext context = getServletContext();
        Usuarios usuario = new Usuarios(cedula, nombre, contrasenia);

        System.out.println("si se pudo " + nombre + cedula);

        String nombreUsuario = controlUsuario.verificarUsuarioCreado(nombre, cedula, context);
        System.out.println("holi: " + nombreUsuario);
        if (nombreUsuario == null) {
            UsuarioIngresar.add(usuario);
            controlUsuario.guardarUsuarios(UsuarioIngresar, context);
            String script = "<script>alert('Se ha registrado exitosamente.'); window.location.href = 'index.jsp?nombreUsuario=" + nombreUsuario + "';</script>";
            response.setContentType("text/html");
            response.getWriter().write(script);
        } else {
            //String nombreUsuario = nuevoUsuario.getNombre_usuario();
            String script = "<script>alert('el usuario ya se ha registrado previamente.'); window.location.href = 'index.jsp?nombreUsuario=" + nombreUsuario + "';</script>";
            response.setContentType("text/html");
            response.getWriter().write(script);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
