package com.evaluacion.socialposts.config;

import com.evaluacion.socialposts.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Inicializador de datos que se ejecuta al arrancar la aplicación.
 * 
 * Esta clase implementa CommandLineRunner para ejecutar código de inicialización
 * cuando la aplicación Spring Boot se inicia. Su función principal es verificar
 * la conectividad con MongoDB y reportar el estado de los datos.
 * 
 * No realiza inicialización de datos automática para evitar duplicados,
 * los datos deben importarse manualmente utilizando herramientas como
 * MongoDB Compass o mongoimport.
 * 
 * @author [Tu Nombre]
 * @version 1.0
 * @since 2025-01-01
 */
@Component
public class DataInitializer implements CommandLineRunner {
    
    /**
     * Repositorio para verificar el estado de los datos en MongoDB.
     * Se inyecta automáticamente por Spring.
     */
    @Autowired
    private PostRepository postRepository;
    
    /**
     * Método que se ejecuta automáticamente al iniciar la aplicación.
     * Verifica la conectividad con MongoDB y reporta información sobre
     * el estado de los datos almacenados.
     * 
     * @param args Argumentos de línea de comandos pasados a la aplicación
     * @throws Exception Si ocurre un error durante la verificación
     */
    @Override
    public void run(String... args) throws Exception {
        try {
            long count = postRepository.count();
            System.out.println("✅ Conexión exitosa con MongoDB. Base de datos contiene: " + count + " publicaciones.");
            
            if (count > 0) {
                System.out.println("📊 Sistema funcionando correctamente con datos reales de MongoDB.");
            } else {
                System.out.println("⚠️  La colección está vacía. Importar datos manualmente usando MongoDB Compass o mongoimport.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error al conectar con MongoDB: " + e.getMessage());
            System.out.println("   Verificar que MongoDB esté ejecutándose en localhost:27017");
            e.printStackTrace();
        }
    }
}
