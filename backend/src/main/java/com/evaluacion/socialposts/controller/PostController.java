package com.evaluacion.socialposts.controller;

import com.evaluacion.socialposts.model.Post;
import com.evaluacion.socialposts.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para la gestión de publicaciones de redes sociales.
 * 
 * Proporciona endpoints para operaciones CRUD sobre publicaciones:
 * - Obtener todas las publicaciones
 * - Obtener una publicación específica por ID
 * - Buscar publicaciones por contenido
 * - Crear nuevas publicaciones
 * - Actualizar publicaciones existentes
 * - Eliminar publicaciones
 * 
 * Todos los endpoints incluyen manejo de errores y respuestas HTTP apropiadas.
 * Configurado con CORS para permitir requests desde el frontend en React.
 * 
 * @author [Tu Nombre]
 * @version 1.0
 * @since 2025-01-01
 */
@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:3000") // Para permitir requests desde React
public class PostController {
    
    /**
     * Servicio de negocio para operaciones con publicaciones.
     * Se inyecta automáticamente por Spring.
     */
    @Autowired
    private PostService postService;
      /**
     * Obtiene todas las publicaciones disponibles en el sistema.
     * 
     * @return ResponseEntity con la lista de publicaciones y estado HTTP 200,
     *         o estado HTTP 500 en caso de error interno
     */
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        try {
            List<Post> posts = postService.getAllPosts();
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
      /**
     * Obtiene una publicación específica por su identificador único.
     * 
     * @param id Identificador único de la publicación
     * @return ResponseEntity con la publicación encontrada y estado HTTP 200,
     *         estado HTTP 404 si no se encuentra la publicación,
     *         o estado HTTP 500 en caso de error interno
     */
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable String id) {
        try {
            Optional<Post> post = postService.getPostById(id);
            if (post.isPresent()) {
                return new ResponseEntity<>(post.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
      /**
     * Busca publicaciones que contengan el texto especificado en su contenido.
     * Realiza búsqueda case-insensitive en el contenido de las publicaciones.
     * 
     * @param query Texto a buscar en las publicaciones (parámetro opcional)
     * @return ResponseEntity con la lista de publicaciones que coinciden con la búsqueda
     *         y estado HTTP 200, o estado HTTP 500 en caso de error interno
     */
    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchPosts(@RequestParam(value = "q", required = false) String query) {
        try {
            List<Post> posts = postService.searchPosts(query);
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
      /**
     * Crea una nueva publicación en el sistema.
     * Valida los datos de entrada antes de persistir la publicación.
     * 
     * @param post Objeto Post con los datos de la nueva publicación (validado automáticamente)
     * @return ResponseEntity con la publicación creada y estado HTTP 201,
     *         o estado HTTP 500 en caso de error interno
     */
    @PostMapping
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {
        try {
            Post createdPost = postService.createPost(post);
            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
      /**
     * Actualiza una publicación existente con nuevos datos.
     * Verifica que la publicación exista antes de realizar la actualización.
     * 
     * @param id Identificador único de la publicación a actualizar
     * @param post Objeto Post con los nuevos datos (validado automáticamente)
     * @return ResponseEntity con la publicación actualizada y estado HTTP 200,
     *         estado HTTP 404 si no se encuentra la publicación,
     *         o estado HTTP 500 en caso de error interno
     */
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable String id, @Valid @RequestBody Post post) {
        try {
            Optional<Post> existingPost = postService.getPostById(id);
            if (existingPost.isPresent()) {
                Post updatedPost = postService.updatePost(id, post);
                return new ResponseEntity<>(updatedPost, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
      /**
     * Elimina una publicación del sistema.
     * Verifica que la publicación exista antes de proceder con la eliminación.
     * 
     * @param id Identificador único de la publicación a eliminar
     * @return ResponseEntity con estado HTTP 204 (No Content) si se elimina correctamente,
     *         estado HTTP 404 si no se encuentra la publicación,
     *         o estado HTTP 500 en caso de error interno
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id) {
        try {
            Optional<Post> existingPost = postService.getPostById(id);
            if (existingPost.isPresent()) {
                postService.deletePost(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
