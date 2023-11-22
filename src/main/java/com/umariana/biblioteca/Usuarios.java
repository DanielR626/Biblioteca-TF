/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umariana.biblioteca;

import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class Usuarios {
    /*-------------------------------------------------------
     * Atributos 
     *-------------------------------------------------*/
    
    private String cedula;
    private String nombre_usuario;
    private String contrasenia;
    private ArrayList<Libros> libros;

 
     
     /*----------------------------------------------------------
      * Metodos
      *---------------------------------------------------------*/

     public Usuarios(String cedula, String nombre_usuario, String contrasenia) {
        this.cedula = cedula;
        this.nombre_usuario = nombre_usuario;
        this.contrasenia = contrasenia;
        this.libros = new ArrayList<>();
    }
     
    public void agregarLibro(Libros libro) {
        libros.add(libro);
    }
   
    public String getCedula() {
         return cedula;
     }
    public void setCedula(String cedula) {
         this.cedula = cedula;
     }
     public String getNombre_usuario() {
         return nombre_usuario;
     }
     public void setNombre_usuario(String nombre_usuario) {
         this.nombre_usuario = nombre_usuario;
     }
     public String getContrasenia() {
         return contrasenia;
     }
     public void setContrasenia(String contrasenia) {
         this.contrasenia = contrasenia;
     }
 
 
}
