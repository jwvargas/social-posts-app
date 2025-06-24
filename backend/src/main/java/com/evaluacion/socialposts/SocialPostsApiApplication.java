package com.evaluacion.socialposts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Social Posts API.
 * 
 * Esta aplicación Spring Boot proporciona una API REST para la gestión
 * de publicaciones de redes sociales. Incluye funcionalidades para:
 * - Operaciones CRUD sobre publicaciones
 * - Búsqueda de publicaciones por contenido y autor
 * - Integración con MongoDB para persistencia de datos
 * - Soporte para CORS para frontend React
 * 
 * La aplicación utiliza Spring Boot para autoconfiguración y gestión
 * de dependencias, Spring Data MongoDB para persistencia, y Spring Web
 * para la creación de endpoints REST.
 * 
 * @author [Tu Nombre]
 * @version 1.0
 * @since 2025-01-01
 */
@SpringBootApplication
public class SocialPostsApiApplication {

	/**
	 * Método principal que inicia la aplicación Spring Boot.
	 * 
	 * @param args Argumentos de línea de comandos
	 */
	public static void main(String[] args) {
		SpringApplication.run(SocialPostsApiApplication.class, args);
	}

}
