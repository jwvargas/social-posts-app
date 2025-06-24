package com.evaluacion.socialposts.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Entidad que representa una publicación de red social.
 * 
 * Esta clase define el modelo de datos para las publicaciones que se almacenan
 * en MongoDB. Cada publicación contiene el texto del contenido, fecha de publicación,
 * información del autor y métricas de interacción.
 * 
 * @author [Tu Nombre]
 * @version 1.0
 * @since 2025-01-01
 */
@Document(collection = "posts")
public class Post {
    
    /**
     * Identificador único de la publicación generado por MongoDB.
     */
    @Id
    private String id;
    
    /**
     * Contenido textual de la publicación.
     * Este campo es obligatorio y no puede estar vacío.
     */
    @NotBlank(message = "El contenido es obligatorio")
    private String content;
    
    /**
     * Fecha y hora en que se realizó la publicación.
     * Se almacena en formato LocalDateTime para facilitar las operaciones de fecha.
     */
    @NotNull(message = "La fecha de publicación es obligatoria")
    private LocalDateTime publishDate;
    
    /**
     * Información del autor de la publicación.
     * Incluye el nombre y usuario en formato "Nombre (@usuario)".
     */
    @NotBlank(message = "El autor es obligatorio")
    private String author;
    
    /**
     * Métricas de interacción de la publicación.
     * Contiene likes, comentarios y compartidos.
     */
    @NotNull(message = "Las interacciones son obligatorias")
    private Interactions interactions;
    
    /**
     * Constructor por defecto requerido por Spring Data MongoDB.
     */
    public Post() {}
    
    /**
     * Constructor para crear una nueva publicación con todos los campos.
     * 
     * @param content Contenido textual de la publicación
     * @param publishDate Fecha de publicación
     * @param author Autor de la publicación
     * @param interactions Métricas de interacción
     */
    public Post(String content, LocalDateTime publishDate, String author, Interactions interactions) {
        this.content = content;
        this.publishDate = publishDate;
        this.author = author;
        this.interactions = interactions;
    }
    
    // Getters y Setters con documentación
    
    /**
     * Obtiene el ID único de la publicación.
     * @return ID de la publicación
     */
    public String getId() {
        return id;
    }
    
    /**
     * Establece el ID de la publicación.
     * @param id ID a asignar
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Obtiene el contenido de la publicación.
     * @return Contenido textual
     */
    public String getContent() {
        return content;
    }
    
    /**
     * Establece el contenido de la publicación.
     * @param content Contenido a asignar
     */
    public void setContent(String content) {
        this.content = content;
    }
    
    /**
     * Obtiene la fecha de publicación.
     * @return Fecha de publicación
     */
    public LocalDateTime getPublishDate() {
        return publishDate;
    }
    
    /**
     * Establece la fecha de publicación.
     * @param publishDate Fecha a asignar
     */
    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }
    
    /**
     * Obtiene la información del autor.
     * @return Autor de la publicación
     */
    public String getAuthor() {
        return author;
    }
    
    /**
     * Establece el autor de la publicación.
     * @param author Autor a asignar
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    
    /**
     * Obtiene las métricas de interacción.
     * @return Objeto con likes, comentarios y compartidos
     */
    public Interactions getInteractions() {
        return interactions;
    }
    
    /**
     * Establece las métricas de interacción.
     * @param interactions Métricas a asignar
     */
    public void setInteractions(Interactions interactions) {
        this.interactions = interactions;
    }
    
    /**
     * Representación textual de la publicación para debugging.
     * @return String con todos los campos de la publicación
     */
    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", publishDate=" + publishDate +
                ", author='" + author + '\'' +
                ", interactions=" + interactions +
                '}';
    }
}
