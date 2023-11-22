/*
 * Paquete donde se encuentra la clase.
 */
package com.umariana.biblioteca;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;

/**
 * Clase que representa la biblioteca de libros y proporciona métodos para
 * gestionarlos.
 */
public class Biblioteca {

    /*
     * Atributos
     */
    public static Libros cabeza; // Puntero al primer libro de la lista

    /*
     * Constructor de la clase Biblioteca.
     */
    public Biblioteca() {
        cabeza = null;
    }

    /*
     * Método para agregar libros a la lista.
     */
    public static void agregarLibros(Libros nuevoLibro) {
        if (cabeza == null) {
            cabeza = nuevoLibro;
        } else {
            Libros actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoLibro);
        }
    }

    /*
     * Método para buscar un libro por su título.
     */
    public static Libros buscarLibroPorTitulo(String titulo) {
        Libros actual = cabeza;
        while (actual != null) {
            if (actual.getTitulo().equals(titulo)) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    /*
     * Método para eliminar un libro de la lista.
     */
    public static void eliminarLibro(Libros libroAEliminar, ServletContext context) {
        if (cabeza == null) {
            return;
        }

        if (cabeza == libroAEliminar) {
            cabeza = cabeza.getSiguiente();
            guardarLibrosEnArchivo(context);
            return;
        }

        Libros actual = cabeza;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente() == libroAEliminar) {
                actual.setSiguiente(libroAEliminar.getSiguiente());
                guardarLibrosEnArchivo(context);
                return;
            }
            actual = actual.getSiguiente();
        }

        guardarLibrosEnArchivo(context);
    }

    /*
     * Método para eliminar un libro de la lista de libros pedidos.
     */
    public static void eliminarLibroPedido(Libros libroAEliminar, ServletContext context, String nombreUsuario) {
        List<Libros> librosPedidos = cargarDesdeArchivoPedido(context, nombreUsuario);
        eliminarArchivo(context, nombreUsuario);

        for (Libros libroActual : librosPedidos) {
            if (!libroActual.getId().equals(libroAEliminar.getId())) {
                System.out.println("DESDE ELIMINAR HABER SI FUNCIONA " + libroActual.getId() + " eliminar " + libroAEliminar.getId());
                agregarLibroEnArchivoPedido(context, nombreUsuario, libroActual);
            }
        }
    }

    /*
     * Método para obtener todos los libros de la lista.
     */
    public static List<Libros> obtenerTodosLosLibros() {
        List<Libros> listaTodosLosLibros = new ArrayList<>();
        Libros actual = cabeza;

        while (actual != null) {
            listaTodosLosLibros.add(actual);
            actual = actual.getSiguiente();
        }

        return listaTodosLosLibros;
    }

    /*
     * Método para limpiar la lista de libros.
     */
    public static void limpiarLibros() {
        cabeza = null;
    }

    /*
     * Método para editar la información de un libro.
     */
    public static void editarLibro(Libros libro, String nuevoTitulo, String nuevoAutor, String nuevoAnio, String nuevaFoto, ServletContext context, String nombreUsuario) {
        if (libro == null) {
            return;
        }

        libro.setTitulo(nuevoTitulo);
        libro.setAutor(nuevoAutor);
        libro.setAnio(nuevoAnio);
        libro.setFoto(nuevaFoto);

        guardarLibrosEnArchivo(context);
    }

    /*
     * Método para buscar un libro por su ID.
     */
    public static Libros buscarLibroPorId(String id) {
        Libros actual = cabeza;
        while (actual != null) {
            if (actual.getId().equals(id)) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    /*
     * Método para verificar si ya existe un libro con el mismo ID.
     */
    public static boolean existeLibroConId(String id) {
        Libros actual = cabeza;
        while (actual != null) {
            if (actual.getId().equals(id)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    /*
     * Métodos para controlar la información de los libros en los archivos.
     */

 /*
     * Método para guardar la información de los libros en un archivo de texto.
     */
    public static void guardarLibrosEnArchivo(ServletContext context) {
        String relativePath = "/data/libros_disponibles.txt";
        String absPath = context.getRealPath(relativePath);
        File archivoGuardar = new File(absPath);

        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoGuardar));

            Libros actual = cabeza;
            while (actual != null) {
                if (actual.getEstado().equals("Disponible")) {
                    escritor.write("ID: " + actual.getId());
                    escritor.newLine();
                    escritor.write("Título: " + actual.getTitulo());
                    escritor.newLine();
                    escritor.write("Autor: " + actual.getAutor());
                    escritor.newLine();
                    escritor.write("Año de lanzamiento: " + actual.getAnio());
                    escritor.newLine();
                    escritor.write("Foto: " + actual.getFoto());
                    escritor.newLine();
                    escritor.write("Estado: " + actual.getEstado());
                    escritor.newLine();
                    escritor.write("-----------------------");
                    escritor.newLine();
                }
                actual = actual.getSiguiente();
            }

            escritor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Método para agregar la información de un libro al archivo de libros pedidos.
     */
    public static void agregarLibroEnArchivoPedido(ServletContext context, String nombreUsuario, Libros nuevoLibro) {
        String relativePath = "/data/libros_" + nombreUsuario + ".txt";
        String absPath = context.getRealPath(relativePath);
        File archivoGuardar = new File(absPath);

        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoGuardar, true));

            escritor.write("ID: " + nuevoLibro.getId());
            escritor.newLine();
            escritor.write("Título: " + nuevoLibro.getTitulo());
            escritor.newLine();
            escritor.write("Autor: " + nuevoLibro.getAutor());
            escritor.newLine();
            escritor.write("Año de lanzamiento: " + nuevoLibro.getAnio());
            escritor.newLine();
            escritor.write("Foto: " + nuevoLibro.getFoto());
            escritor.newLine();
            escritor.write("Estado: " + nuevoLibro.getEstado());
            escritor.newLine();
            escritor.write("-----------------------");
            escritor.newLine();

            escritor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Método para cargar la información de los libros desde un archivo.
     */
    public static void cargarLibrosDesdeArchivo(ServletContext context) {
        limpiarLibros();
        String relativePath = "/data/libros_disponibles.txt";
        String absPath = context.getRealPath(relativePath);
        File archivoCargar = new File(absPath);
        boolean tieneContenido = archivoCargar.length() != 0;
        System.out.println("Desde carga: " + relativePath + " Verificando si tiene contenido: " + tieneContenido);

        if (tieneContenido) {
            try (BufferedReader leyendo = new BufferedReader(new FileReader(archivoCargar))) {
                String id = null;
                String titulo = null;
                String autor = null;
                String anio = null;
                String foto = null;
                String estado = null;

                String lineaPorLinea;
                while ((lineaPorLinea = leyendo.readLine()) != null) {
                    if (lineaPorLinea.startsWith("ID:")) {
                        id = lineaPorLinea.substring(lineaPorLinea.indexOf(":") + 1).trim();
                    } else if (lineaPorLinea.startsWith("Título:")) {
                        titulo = lineaPorLinea.substring(lineaPorLinea.indexOf(":") + 1).trim();
                    } else if (lineaPorLinea.startsWith("Autor:")) {
                        autor = lineaPorLinea.substring(lineaPorLinea.indexOf(":") + 1).trim();
                    } else if (lineaPorLinea.startsWith("Año de lanzamiento:")) {
                        anio = lineaPorLinea.substring(lineaPorLinea.indexOf(":") + 1).trim();
                    } else if (lineaPorLinea.startsWith("Foto:")) {
                        foto = lineaPorLinea.substring(lineaPorLinea.indexOf(":") + 1).trim();
                        System.out.println("desde cargar verificando si se carga la foto " + foto);
                    } else if (lineaPorLinea.startsWith("Estado:")) {
                        estado = lineaPorLinea.substring(lineaPorLinea.indexOf(":") + 1).trim();

                        Libros nuevaTarea = new Libros(id, titulo, autor, anio, foto, estado);
                        System.out.println("se creo nuevo libro: " + nuevaTarea.getAnio());
                        agregarLibros(nuevaTarea);

                        id = null;
                        titulo = null;
                        autor = null;
                        anio = null;
                        foto = null;
                        estado = null;
                    }
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    /*
     * Método para cargar la información de los libros pedidos desde un archivo.
     */
    public static List<Libros> cargarDesdeArchivoPedido(ServletContext context, String nombreUsuario) {
        List<Libros> listaLibros = new ArrayList<>();
        String relativePath = "/data/libros_" + nombreUsuario + ".txt";
        String absPath = context.getRealPath(relativePath);
        File archivoCargar = new File(absPath);
        boolean tieneContenido = archivoCargar.length() != 0;
        System.out.println("Desde carga: " + relativePath + " Verificando si tiene contenido: " + tieneContenido);

        if (tieneContenido) {
            try (BufferedReader leyendo = new BufferedReader(new FileReader(archivoCargar))) {
                String id = null;
                String titulo = null;
                String autor = null;
                String anio = null;
                String foto = null;
                String estado = null;

                String lineaPorLinea;
                while ((lineaPorLinea = leyendo.readLine()) != null) {
                    if (lineaPorLinea.startsWith("ID:")) {
                        id = lineaPorLinea.substring(lineaPorLinea.indexOf(":") + 1).trim();
                    } else if (lineaPorLinea.startsWith("Título:")) {
                        titulo = lineaPorLinea.substring(lineaPorLinea.indexOf(":") + 1).trim();
                    } else if (lineaPorLinea.startsWith("Autor:")) {
                        autor = lineaPorLinea.substring(lineaPorLinea.indexOf(":") + 1).trim();
                    } else if (lineaPorLinea.startsWith("Año de lanzamiento:")) {
                        anio = lineaPorLinea.substring(lineaPorLinea.indexOf(":") + 1).trim();
                    } else if (lineaPorLinea.startsWith("Foto:")) {
                        foto = lineaPorLinea.substring(lineaPorLinea.indexOf(":") + 1).trim();
                    } else if (lineaPorLinea.startsWith("Estado:")) {
                        estado = lineaPorLinea.substring(lineaPorLinea.indexOf(":") + 1).trim();

                        Libros nuevaTarea = new Libros(id, titulo, autor, anio, foto, estado);
                        System.out.println("se creo nuevo libro: " + nuevaTarea.getAnio());
                        listaLibros.add(nuevaTarea);
                        agregarLibros(nuevaTarea);

                        id = null;
                        titulo = null;
                        autor = null;
                        anio = null;
                        foto = null;
                        estado = null;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return listaLibros;
    }

    /*
     * Método para eliminar un archivo.
     */
    public static void eliminarArchivo(ServletContext context, String nombreUsuario) {
        String relativePath = "/data/libros_" + nombreUsuario + ".txt";
        String absPath = context.getRealPath(relativePath);
        File archivoCargar = new File(absPath);

        try {

            FileWriter fileWriter = new FileWriter(archivoCargar);

            fileWriter.write("");

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
