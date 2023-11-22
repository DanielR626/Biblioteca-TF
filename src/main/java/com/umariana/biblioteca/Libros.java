/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umariana.biblioteca;

/**
 * Clase que representa un libro en el sistema de biblioteca.
 * @author Daniel
 */
public class Libros {
    // Atributos que representan las propiedades de un libro
    private String id;
    private String titulo;
    private String autor;
    private String anio;
    private String foto;
    private String estado; // Agregado para representar el estado del libro (disponible, no disponible)
    
    // Referencias a libros siguiente y anterior (posiblemente para una lista doblemente enlazada)
    Libros siguiente, anterior;

    // Constructor vacío
    public Libros() {
    }

    // Constructor con parámetros para inicializar un libro
    public Libros(String id, String titulo, String autor, String anio, String foto, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.foto = foto;
        this.estado = estado;
        this.siguiente = null;
    }

    // Métodos getters y setters para acceder y modificar los atributos del libro

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Libros getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Libros siguiente) {
        this.siguiente = siguiente;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Libros getAnterior() {
        return anterior;
    }

    public void setAnterior(Libros anterior) {
        this.anterior = anterior;
    }
}
