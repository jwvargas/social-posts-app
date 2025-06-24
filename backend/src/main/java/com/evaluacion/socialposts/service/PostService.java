package com.evaluacion.socialposts.service;

import com.evaluacion.socialposts.model.Post;
import com.evaluacion.socialposts.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestión de publicaciones de redes sociales.
 * 
 * Esta clase implementa la lógica de negocio para operaciones CRUD
 * y funcionalidades específicas como búsqueda de publicaciones.
 * Actúa como capa intermedia entre el controlador y el repositorio,
 * proporcionando métodos con lógica de negocio específica.
 * 
 * @author [Tu Nombre]
 * @version 1.0
 * @since 2025-01-01
 */
@Service
public class PostService {
    
    /**
     * Repositorio para acceso a datos de publicaciones.
     * Se inyecta automáticamente por Spring.
     */
    @Autowired
    private PostRepository postRepository;
      /**
     * Obtiene todas las publicaciones del sistema ordenadas por fecha de publicación descendente.
     * Las publicaciones más recientes aparecen primero en la lista.
     * 
     * @return Lista de todas las publicaciones ordenadas por fecha de publicación (más recientes primero)
     */
    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByPublishDateDesc();
    }
    
    /**
     * Busca una publicación específica por su identificador único.
     * 
     * @param id Identificador único de la publicación
     * @return Optional conteniendo la publicación si existe, Optional.empty() si no existe
     */
    public Optional<Post> getPostById(String id) {
        return postRepository.findById(id);
    }
    
    /**
     * Busca publicaciones que contengan el texto especificado en su contenido o autor.
     * La búsqueda es case-insensitive y se realiza tanto en el contenido como en el nombre del autor.
     * Si no se proporciona query o está vacío, retorna todas las publicaciones.
     * 
     * @param query Texto a buscar en el contenido o autor de las publicaciones
     * @return Lista de publicaciones que coinciden con el criterio de búsqueda
     */
    public List<Post> searchPosts(String query) {
        if (query == null || query.trim().isEmpty()) {
            return getAllPosts();
        }
        return postRepository.findByTextOrAuthorContainingIgnoreCase(query.trim());
    }
    
    /**
     * Crea y persiste una nueva publicación en el sistema.
     * 
     * @param post Objeto Post con los datos de la nueva publicación
     * @return La publicación creada con su ID asignado automáticamente
     */
    public Post createPost(Post post) {
        return postRepository.save(post);
    }
    
    /**
     * Actualiza una publicación existente con nuevos datos.
     * Asigna el ID especificado al post y lo persiste.
     * 
     * @param id Identificador único de la publicación a actualizar
     * @param post Objeto Post con los nuevos datos
     * @return La publicación actualizada
     */
    public Post updatePost(String id, Post post) {
        post.setId(id);
        return postRepository.save(post);
    }
    
    /**
     * Elimina una publicación del sistema por su identificador único.
     * 
     * @param id Identificador único de la publicación a eliminar
     */
    public void deletePost(String id) {
        postRepository.deleteById(id);
    }
    
    /**
     * Cuenta el número total de publicaciones en el sistema.
     * 
     * @return Número total de publicaciones almacenadas
     */
    public long getTotalPosts() {
        return postRepository.count();
    }
}
