package com.evaluacion.socialposts.repository;

import com.evaluacion.socialposts.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio para el acceso a datos de publicaciones en MongoDB.
 * 
 * Esta interfaz extiende MongoRepository para proporcionar operaciones CRUD básicas
 * y define consultas personalizadas para búsqueda de publicaciones.
 * Utiliza consultas MongoDB nativas para funcionalidades específicas como
 * búsqueda case-insensitive y ordenamiento por fecha.
 * 
 * @author [Tu Nombre]
 * @version 1.0
 * @since 2025-01-01
 */
@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    
    /**
     * Busca publicaciones que contengan el texto especificado en su contenido.
     * La búsqueda es case-insensitive utilizando expresiones regulares de MongoDB.
     * 
     * @param text Texto a buscar en el contenido de las publicaciones
     * @return Lista de publicaciones que contienen el texto en su contenido
     */
    @Query("{ 'text': { $regex: ?0, $options: 'i' } }")
    List<Post> findByTextContainingIgnoreCase(String text);
    
    /**
     * Busca publicaciones por nombre de autor.
     * La búsqueda es case-insensitive utilizando expresiones regulares de MongoDB.
     * 
     * @param author Texto a buscar en el nombre del autor
     * @return Lista de publicaciones cuyo autor contiene el texto especificado
     */
    @Query("{ 'author': { $regex: ?0, $options: 'i' } }")
    List<Post> findByAuthorContainingIgnoreCase(String author);
    
    /**
     * Búsqueda combinada que busca el término tanto en el contenido como en el autor.
     * Utiliza el operador $or de MongoDB para buscar en múltiples campos.
     * La búsqueda es case-insensitive.
     * 
     * @param searchTerm Término de búsqueda a aplicar en contenido y autor
     * @return Lista de publicaciones que contienen el término en contenido o autor
     */
    @Query("{ $or: [ " +
           "{ 'text': { $regex: ?0, $options: 'i' } }, " +
           "{ 'author': { $regex: ?0, $options: 'i' } } ] }")
    List<Post> findByTextOrAuthorContainingIgnoreCase(String searchTerm);
    
    /**
     * Obtiene todas las publicaciones ordenadas por fecha de publicación descendente.
     * Las publicaciones más recientes aparecen primero en el resultado.
     * 
     * @return Lista de todas las publicaciones ordenadas por fecha (más recientes primero)
     */
    List<Post> findAllByOrderByPublishDateDesc();
}
