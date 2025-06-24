package com.evaluacion.socialposts.model;

import jakarta.validation.constraints.Min;

/**
 * Clase que representa las métricas de interacción de una publicación.
 * 
 * Esta clase encapsula los diferentes tipos de interacciones que puede recibir
 * una publicación en redes sociales: likes, comentarios y compartidos.
 * Incluye validaciones para asegurar que los valores sean no negativos.
 * 
 * @author [Tu Nombre]
 * @version 1.0
 * @since 2025-01-01
 */
public class Interactions {
    
    /**
     * Número de "me gusta" que ha recibido la publicación.
     * Debe ser un valor no negativo.
     */
    @Min(value = 0, message = "Los me gusta no pueden ser negativos")
    private int likes;
    
    /**
     * Número de comentarios que ha recibido la publicación.
     * Debe ser un valor no negativo.
     */
    @Min(value = 0, message = "Los comentarios no pueden ser negativos")
    private int comments;
    
    /**
     * Número de veces que se ha compartido la publicación.
     * Debe ser un valor no negativo.
     */
    @Min(value = 0, message = "Los compartidos no pueden ser negativos")
    private int shares;
    
    /**
     * Constructor por defecto requerido para la deserialización.
     */
    public Interactions() {}
    
    /**
     * Constructor para crear un objeto Interactions con todos los valores.
     * 
     * @param likes Número de likes
     * @param comments Número de comentarios
     * @param shares Número de compartidos
     */
    public Interactions(int likes, int comments, int shares) {
        this.likes = likes;
        this.comments = comments;
        this.shares = shares;
    }
    
    /**
     * Obtiene el número de likes.
     * @return Cantidad de likes
     */
    public int getLikes() {
        return likes;
    }
    
    /**
     * Establece el número de likes.
     * @param likes Cantidad de likes a asignar
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }
    
    /**
     * Obtiene el número de comentarios.
     * @return Cantidad de comentarios
     */
    public int getComments() {
        return comments;
    }
    
    /**
     * Establece el número de comentarios.
     * @param comments Cantidad de comentarios a asignar
     */
    public void setComments(int comments) {
        this.comments = comments;
    }
    
    /**
     * Obtiene el número de compartidos.
     * @return Cantidad de compartidos
     */
    public int getShares() {
        return shares;
    }
    
    /**
     * Establece el número de compartidos.
     * @param shares Cantidad de compartidos a asignar
     */
    public void setShares(int shares) {
        this.shares = shares;
    }
    
    /**
     * Calcula el total de interacciones sumando likes, comentarios y compartidos.
     * 
     * @return Suma total de todas las interacciones
     */
    public int getTotalInteractions() {
        return likes + comments + shares;
    }
      /**
     * Representación textual de las interacciones para debugging.
     * @return String con todos los valores de interacción
     */
    @Override
    public String toString() {
        return "Interactions{" +
                "likes=" + likes +
                ", comments=" + comments +
                ", shares=" + shares +
                ", total=" + getTotalInteractions() +
                '}';
    }
}
