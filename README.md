# Social Posts App - Prueba Técnica

[![GitHub license](https://img.shields.io/github/license/jwvargas/social-posts-app)](https://github.com/jwvargas/social-posts-app/blob/main/LICENSE)
[![GitHub stars](https://img.shields.io/github/stars/jwvargas/social-posts-app)](https://github.com/jwvargas/social-posts-app/stargazers)
[![GitHub forks](https://img.shields.io/github/forks/jwvargas/social-posts-app)](https://github.com/jwvargas/social-posts-app/network)
[![Java](https://img.shields.io/badge/Java-17-orange)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React-18-blue)](https://reactjs.org/)
[![TypeScript](https://img.shields.io/badge/TypeScript-5.0-blue)](https://www.typescriptlang.org/)
[![MongoDB](https://img.shields.io/badge/MongoDB-7.0-green)](https://www.mongodb.com/)

Una aplicación full-stack para visualizar y buscar publicaciones de redes sociales, desarrollada con React (frontend) y Spring Boot (backend).

## 📋 Descripción del Proyecto

Esta aplicación permite:
- Visualizar un listado paginado de **500 publicaciones reales** de redes sociales
- **Navegación por páginas** (10 publicaciones por página)
- Realizar búsquedas en tiempo real por texto o autor
- Ver detalles completos de cada publicación
- Navegar fluidamente entre la vista de lista y detalle

## 🚀 Tecnologías Utilizadas

### Backend
- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data MongoDB**
- **Maven** para gestión de dependencias

### Frontend
- **React 18** con TypeScript
- **Componentes funcionales** con Hooks
- **CSS personalizado** para estilos
- **Fetch API** para comunicación con el backend

### Base de Datos
- **MongoDB** para almacenamiento de publicaciones

## 📁 Estructura del Proyecto

```
prueba_tecnica/
├── backend/                 # API REST con Spring Boot
│   ├── src/main/java/
│   │   └── com/evaluacion/socialposts/
│   │       ├── SocialPostsApiApplication.java
│   │       ├── controller/
│   │       │   └── PostController.java
│   │       ├── model/
│   │       │   ├── Post.java
│   │       │   └── Interactions.java
│   │       ├── repository/
│   │       │   └── PostRepository.java
│   │       ├── service/
│   │       │   └── PostService.java
│   │       └── config/
│   │           └── DataInitializer.java
│   └── pom.xml
├── frontend/                # Aplicación React
│   ├── src/
│   │   ├── components/
│   │   │   ├── PostCard.tsx
│   │   │   ├── PostList.tsx
│   │   │   ├── PostDetail.tsx
│   │   │   ├── SearchBar.tsx
│   │   │   └── Pagination.tsx    # ✨ Componente de paginación
│   │   ├── hooks/
│   │   │   └── usePosts.ts       # ✨ Hook personalizado para gestión de estado
│   │   ├── types/
│   │   │   └── Post.ts
│   │   ├── services/
│   │   │   └── postService.ts
│   │   ├── App.tsx
│   │   ├── index.tsx
│   │   └── index.css
│   └── package.json
└── README.md
```

## 🛠️ Instalación y Ejecución

### Prerrequisitos
- **Java 17** o superior
- **Node.js 16** o superior  
- **MongoDB** (local o en la nube)
- **Maven 3.6** o superior

### 1. Configurar MongoDB

#### Opción A: MongoDB Local (Rápido para desarrollo)
```bash
# Instalar MongoDB y ejecutar
mongod --dbpath /data/db
```

#### Opción B: MongoDB Atlas (Recomendado para producción)
1. Crear cuenta gratuita en [MongoDB Atlas](https://www.mongodb.com/atlas)
2. Crear un cluster gratuito (M0)
3. Configurar acceso de red (permitir tu IP)
4. Crear usuario de base de datos
5. Obtener la cadena de conexión
6. Actualizar `backend/src/main/resources/application.properties`:
```properties
spring.data.mongodb.uri=mongodb+srv://usuario:password@cluster.mongodb.net/social_posts_db
```

#### Opción C: Sin MongoDB (Usar datos mock)
El sistema funciona sin MongoDB usando datos de ejemplo en memoria.

> **💡 Nota:** El proyecto viene con **500 publicaciones reales** ya configuradas para importar a MongoDB. Consulta los archivos `posts-for-compass.json` para la importación manual.

### 2. Ejecutar el Backend

#### Método 1: Con Maven
```bash
# Navegar al directorio del backend
cd backend

# Compilar el proyecto
mvn clean install

# Ejecutar la aplicación
mvn spring-boot:run
```

#### Método 2: Con JAR
```bash
cd backend
mvn clean install
java -jar target/social-posts-api-0.0.1-SNAPSHOT.jar
```

#### Método 3: Desde VS Code
1. Abrir el proyecto en VS Code
2. Ejecutar la tarea "Start Backend" o
3. Presionar `F5` para debug

**El backend estará disponible en:** `http://localhost:8080`

### 3. Ejecutar el Frontend

```bash
# Navegar al directorio del frontend
cd frontend

# Instalar dependencias (solo la primera vez)
npm install

# Ejecutar en modo desarrollo
npm start
```

**El frontend estará disponible en:** `http://localhost:3000`

## 🎯 Características Destacadas

### 📊 **500 Publicaciones Reales**
- Base de datos MongoDB con contenido real de redes sociales
- Backend optimizado para gestión de grandes volúmenes

### 📄 **Paginación Completa**
- **10 publicaciones por página** para optimal UX
- **50 páginas** de navegación disponibles
- Controles de navegación: anterior, siguiente, ir a página específica
- Información contextual: "Mostrando 1-10 de 500 publicaciones"

### 🔍 **Búsqueda Inteligente**
- Búsqueda en tiempo real con debounce
- Filtrado por contenido y autor
- Reseteo automático a página 1 en nuevas búsquedas
- Fallback local si el backend no está disponible

### 📱 **Diseño Responsive**
- Adaptable a móviles, tablets y desktop
- Controles de paginación optimizados para touch
- Layout fluido con CSS Grid y Flexbox

### 4. Verificar la Instalación

#### Backend (API)
```bash
# Probar endpoint de publicaciones
curl http://localhost:8080/api/posts

# Probar búsqueda
curl "http://localhost:8080/api/posts/search?q=React"
```

#### Frontend
1. Abrir `http://localhost:3000` en el navegador
2. Verificar que aparece la interfaz con **10 publicaciones por página**
3. Probar la **navegación entre páginas** (50 páginas disponibles)
4. Probar la funcionalidad de búsqueda (resetea a página 1)
5. Verificar que muestra "Mostrando X-Y de 500 publicaciones"

#### Script de Pruebas Automatizadas
```powershell
# Windows PowerShell
.\test-api.ps1

# Linux/Mac
./test-api.sh
```

## 📚 API Endpoints

### Publicaciones

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/posts` | Obtener todas las publicaciones |
| GET | `/api/posts/{id}` | Obtener una publicación por ID |
| GET | `/api/posts/search?q={query}` | Buscar publicaciones por texto |
| POST | `/api/posts` | Crear una nueva publicación |
| PUT | `/api/posts/{id}` | Actualizar una publicación |
| DELETE | `/api/posts/{id}` | Eliminar una publicación |

### Ejemplos de Uso

**Obtener todas las publicaciones:**
```bash
curl http://localhost:8080/api/posts
```

**Buscar publicaciones:**
```bash
curl "http://localhost:8080/api/posts/search?q=React"
```

**Obtener una publicación específica:**
```bash
curl http://localhost:8080/api/posts/64f5b1a2c8d4e5f6a7b8c9d0
```

## 🧪 Ejecutar Pruebas

### Backend
```bash
cd backend
mvn test
```

### Frontend
```bash
cd frontend
npm test
```

## 🎨 Características Implementadas

### Funcionalidades Principales
- ✅ **Lista paginada** con 10 publicaciones por página (**500 total**)
- ✅ **Navegación entre páginas** con controles anterior/siguiente
- ✅ **Navegación directa** a cualquier página (1-50)
- ✅ **Búsqueda en tiempo real** (insensible a mayúsculas/minúsculas)
- ✅ **Vista de detalle** de publicaciones con navegación de regreso
- ✅ **Diseño responsive** para móviles y tablets
- ✅ **Contador de resultados** ("Mostrando X-Y de Z publicaciones")

### Características Técnicas
- ✅ **Componentes reutilizables** en React con TypeScript
- ✅ **Gestión de estado** con hooks personalizados (`usePosts`)
- ✅ **Paginación inteligente** con reseteo en búsquedas
- ✅ **Hook personalizado** para manejo completo de estado
- ✅ **Debounce** en búsqueda para optimizar rendimiento
- ✅ **Datos reales** desde MongoDB (500 publicaciones)
- ✅ **Fallback a datos mock** para funcionamiento sin backend
- ✅ **Manejo de errores** y estados de carga
- ✅ **CORS configurado** para desarrollo
- ✅ **API REST completa** con Spring Boot

### Mejoras de UX/UI
- ✅ **Paginación moderna** con números y navegación directa
- ✅ **Información de contexto** (mostrando X de Y resultados)
- ✅ **Avatars con iniciales** de autores
- ✅ **Fechas relativas** (ej: "Hace 2 horas")
- ✅ **Animaciones** y transiciones suaves
- ✅ **Diseño moderno** con gradientes y sombras
- ✅ **Estados de carga** y mensajes informativos
- ✅ **Controles intuitivos** para navegación entre páginas

## 🏗️ Patrones y Buenas Prácticas

### Backend
- **Arquitectura MVC** con separación clara de responsabilidades
- **DTO/Entity pattern** para transferencia de datos
- **Repository pattern** para acceso a datos
- **Service layer** para lógica de negocio
- **Validación** con annotations de Bean Validation
- **Manejo de errores** con ResponseEntity

### Frontend
- **Componentes funcionales** con hooks
- **Props typing** con TypeScript
- **Single Responsibility Principle** en componentes
- **Custom hooks** para lógica reutilizable (`usePosts`)
- **Paginación client-side** con gestión eficiente de estado
- **Debouncing** para optimización de búsquedas
- **Error boundaries** implícitos
- **Responsive design** mobile-first

## 📝 Decisiones Técnicas

1. **TypeScript en Frontend**: Mejor tipado y detección temprana de errores
2. **MongoDB**: Flexibilidad para el modelo de datos de publicaciones
3. **Spring Boot**: Rápido desarrollo de APIs REST con convenciones
4. **Componentes funcionales**: Código más limpio y mejor rendimiento
5. **CSS personalizado**: Control total sobre el diseño sin dependencias externas
6. **Paginación client-side**: Mejor experiencia de usuario con 500 registros
7. **Hook personalizado**: Encapsulación de lógica compleja de estado
8. **Datos reales**: 500 publicaciones reales en lugar de datos mock

## 👨‍💻 Autor

**jwvargas** - [GitHub](https://github.com/jwvargas)

Desarrollado como prueba técnica para demostrar habilidades full-stack en React y Spring Boot.

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor, lee [CONTRIBUTING.md](CONTRIBUTING.md) para más detalles sobre el proceso.

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) for más detalles.

## 🔗 Enlaces

- **Repositorio**: [https://github.com/jwvargas/social-posts-app](https://github.com/jwvargas/social-posts-app)
- **Demo**: [Ejecutar localmente](http://localhost:3000) siguiendo las instrucciones de instalación
- **Documentación API**: Generar con `mvn javadoc:javadoc` y abrir `target/site/apidocs/index.html`

## 📚 Documentación del Código

### Backend (Java/Spring Boot)
Todas las clases del backend están completamente documentadas con **JavaDoc**:

#### Modelos
- **`Post.java`** - Entidad principal con validaciones y mapeo MongoDB
- **`Interactions.java`** - Métricas de interacción (likes, comentarios, shares)

#### Controladores
- **`PostController.java`** - API REST con endpoints CRUD y búsqueda
  - `GET /api/posts` - Obtener todas las publicaciones
  - `GET /api/posts/{id}` - Obtener publicación específica
  - `GET /api/posts/search?q={query}` - Búsqueda de publicaciones
  - `POST /api/posts` - Crear nueva publicación
  - `PUT /api/posts/{id}` - Actualizar publicación
  - `DELETE /api/posts/{id}` - Eliminar publicación

#### Servicios
- **`PostService.java`** - Lógica de negocio para gestión de publicaciones

#### Repositorios
- **`PostRepository.java`** - Acceso a datos con consultas MongoDB personalizadas

#### Configuración
- **`DataInitializer.java`** - Inicializador que verifica conectividad MongoDB
- **`SocialPostsApiApplication.java`** - Clase principal de la aplicación

### Características de la Documentación
- **JavaDoc completo** en todas las clases y métodos públicos
- **Descripción detallada** de parámetros y valores de retorno
- **Información de versión** y autoría
- **Ejemplos de uso** y consideraciones especiales
- **Documentación de excepciones** y manejo de errores

### Acceso a la Documentación
```bash
# Generar documentación JavaDoc
cd backend
mvn javadoc:javadoc

# La documentación se genera en: target/site/apidocs/
```
