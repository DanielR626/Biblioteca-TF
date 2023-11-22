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
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "SvIngreso", urlPatterns = {"/SvIngreso"})
public class SvIngreso extends HttpServlet {


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
         ServletContext context = getServletContext();
                String usuario = request.getParameter("usuario");
                String contrasenia = request.getParameter("contrasenia");
                System.out.println("nombre: "+ usuario);
                System.out.println("contraseña " + contrasenia);
                String nombreUsuario = controlUsuario.verificarUsuarioCreado(usuario, contrasenia, context);
                System.out.println("aqui estamos jeje");
                System.out.println("nombre usuario "+nombreUsuario);
                List<Libros>  tareasUsuarioActivo= new ArrayList<>();
                
                Usuarios usuarioActivo = controlUsuario.obtenerUsuarioActivo( usuario, contrasenia, context);
                
                if(nombreUsuario!=null){
                       HttpSession session = request.getSession();
                       
                       session.setAttribute("nombre_usuario", nombreUsuario);
                      
                       session.setAttribute("cedula_usuario", contrasenia);
                                       
                        Biblioteca.cargarLibrosDesdeArchivo(context);
                        //tareasUsuarioActivo=ControlTareas.cargarTareasDesdeArchivo(context, nombreUsuario);
                        // Guarda las tareas en la sesión para que estén disponibles en tu JSP
                        System.out.println("control ver libros: "+Biblioteca.obtenerTodosLosLibros());
                        session.setAttribute("libros", Biblioteca.obtenerTodosLosLibros());

                       request.setAttribute("nombre_usuario", nombreUsuario);
                    
                       request.getRequestDispatcher("biblioteca.jsp").forward(request, response);

                   }else{
                       System.out.println("usuario incorrecto");
                       String script = "<script>alert('Usuario incorrecto'); window.location.href = 'index.jsp?nombre_usuario=" + nombreUsuario + "';</script>";
                       response.setContentType("text/html");
                       response.getWriter().write(script);
                     //  request.getRequestDispatcher("index.jsp").forward(request, response);
                   }
              
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
