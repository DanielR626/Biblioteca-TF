/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umariana.biblioteca;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;

/**
 * Clase que proporciona métodos para la gestión de usuarios en el sistema de biblioteca.
 * @author Samir
 */
public class controlUsuario {
    // ArrayList para almacenar temporalmente usuarios nuevos
    private static ArrayList<Usuarios> usuariosNuevo = new ArrayList<>();

    /**
     * Método para guardar usuarios en el archivo "usuarios.txt".
     * @param usuarioIngresar Lista de usuarios a guardar.
     * @param context Contexto del servlet para obtener la ubicación del archivo.
     * @throws IOException
     */
    public static void guardarUsuarios(ArrayList<Usuarios> usuarioIngresar, ServletContext context) throws IOException {
        // Obtener la ubicación del archivo "usuarios.txt" en el directorio de recursos
        String relativePath = "/data/usuarios.txt";
        String absPath = context.getRealPath(relativePath);
        File archivoGuardar = new File(absPath);

        PrintWriter escribir = new PrintWriter(new FileWriter(archivoGuardar, true));

        // Verificar si existe contenido dentro del archivo
        if (archivoGuardar.exists() && archivoGuardar.length() <= 0) {
            escribir.println("====usuarios registrados========");
        } else {
            for (Usuarios usuario : usuarioIngresar) {
                escribir.println("nombre: " + usuario.getNombre_usuario());
                escribir.println("cedula: " + usuario.getCedula());
                escribir.println("contraseña: " + usuario.getContrasenia());
            }
        }
        escribir.close();
    }

    /**
     * Método para cargar usuarios desde el archivo "usuarios.txt".
     * @param context Contexto del servlet para obtener la ubicación del archivo.
     */
    public static void cargarArchivo(ServletContext context) {
        String relativePath = "/data/usuarios.txt";
        String absPath = context.getRealPath(relativePath);
        File archivoCargar = new File(absPath);

        if (archivoCargar.length() != 0) {
            try (BufferedReader leyendo = new BufferedReader(new FileReader(archivoCargar))) {
                String nombreUsuario = null;
                String cedula = null;
                String contrasenia = null;

                String lineaPorLinea;
                while ((lineaPorLinea = leyendo.readLine()) != null) {
                    if (lineaPorLinea.startsWith("nombre:")) {
                        nombreUsuario = lineaPorLinea.substring(lineaPorLinea.indexOf(":") + 1).trim();
                    } else if (lineaPorLinea.startsWith("cedula:")) {
                        cedula = lineaPorLinea.substring(lineaPorLinea.indexOf(":") + 1).trim();
                    } else if (lineaPorLinea.startsWith("contraseña:")) {
                        contrasenia = lineaPorLinea.substring(lineaPorLinea.indexOf(":") + 1).trim();

                        // Crea un nuevo usuario y agrégalo a la lista de usuarios
                        Usuarios nuevoUsuario = new Usuarios(cedula, nombreUsuario, contrasenia);
                        usuariosNuevo.add(nuevoUsuario);
                        
                        // Restablece las variables para el siguiente usuario
                        nombreUsuario = null;
                        cedula = null;
                        contrasenia = null;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Verifica si un usuario con el nombre y contraseña dados existe.
     * @param nombre Nombre del usuario.
     * @param contrasenia Contraseña del usuario.
     * @param context Contexto del servlet para obtener la ubicación del archivo.
     * @return Nombre del usuario si existe, de lo contrario, devuelve null.
     * @throws IOException
     */
    public static String verificarUsuarioCreado(String nombre, String contrasenia, ServletContext context) throws IOException {
        cargarArchivo(context);

        for (Usuarios iUsuario : usuariosNuevo) {
            if (iUsuario.getNombre_usuario().equals(nombre) && iUsuario.getContrasenia().equals(contrasenia)) {
                return iUsuario.getNombre_usuario();
            }
        }
        return null;
    }

    /**
     * Obtiene un objeto de tipo usuario activo.
     * @param nombre Nombre del usuario.
     * @param cedula Cédula del usuario.
     * @param context Contexto del servlet para obtener la ubicación del archivo.
     * @return Objeto de tipo usuario si existe, de lo contrario, devuelve null.
     */
    public static Usuarios obtenerUsuarioActivo(String nombre, String cedula, ServletContext context) {
        cargarArchivo(context);

        for (Usuarios usuario : usuariosNuevo) {
            if (usuario.getNombre_usuario().equals(nombre) && usuario.getContrasenia().equals(cedula)) {
                return usuario;
            }
        }

        return null;
    }
}
