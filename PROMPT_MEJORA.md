# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragma/comments/application/usecase/CommentUseCase.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/comments/infrastructure/controller/CommentController.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/comments/infrastructure/config/OpenApiConfig.java` — `io.swagger.v3`: El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/comments/infrastructure/controller/CommentControllerTest.java` — `com.fasterxml.jackson`: El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/comments/domain/exception/CommentProcessingException.java` — `ErrorType.name`: Se invoca `name` sobre `ErrorType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/application/usecase/CommentUseCase.java` — `CommentRequest.getPostId`: Se invoca `getPostId` sobre `CommentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/application/usecase/CommentUseCase.java` — `CommentRequest.getUserId`: Se invoca `getUserId` sobre `CommentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/application/usecase/CommentUseCase.java` — `CommentRequest.getContent`: Se invoca `getContent` sobre `CommentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/application/validator/CommentValidator.java` — `CommentRequest.getPostId`: Se invoca `getPostId` sobre `CommentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/application/validator/CommentValidator.java` — `CommentRequest.getUserId`: Se invoca `getUserId` sobre `CommentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/application/validator/CommentValidator.java` — `CommentRequest.getContent`: Se invoca `getContent` sobre `CommentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/controller/CommentController.java` — `CommentRequest.postId`: Se invoca `postId` sobre `CommentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/controller/CommentController.java` — `CommentRequest.userId`: Se invoca `userId` sobre `CommentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/comments/infrastructure/controller/CommentController.java` — `CommentRequest.content`: Se invoca `content` sobre `CommentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/comments/application/usecase/CommentUseCaseTest.java` — `Comment.size`: Se invoca `size` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/comments/application/usecase/CommentUseCaseTest.java` — `Comment.stream`: Se invoca `stream` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/comments/application/usecase/CommentUseCaseTest.java` — `Comment.isEmpty`: Se invoca `isEmpty` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/comments/application/validator/CommentValidatorTest.java` — `CommentValidator.validate`: Se invoca `validate` sobre `CommentValidator`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/comments/application/validator/CommentValidatorTest.java` — `CommentValidator.validateRequiredField`: Se invoca `validateRequiredField` sobre `CommentValidator`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/comments/application/validator/CommentValidatorTest.java` — `InvalidCommentException.getMessage`: Se invoca `getMessage` sobre `InvalidCommentException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/comments/application/validator/CommentValidatorTest.java` — `CommentValidator.validateContentLength`: Se invoca `validateContentLength` sobre `CommentValidator`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Spring, Tecnología Java, Senior L2

### Brecha de conocimiento
Comprueba manejo de errores y validaciones

### Reto
- Tema: E2E prod comments 1790905766
- Seniority: senior-l2
- Tipo: practical
- Título: Manejo de errores y validaciones en un sistema de comentarios E2E
- Tiempo estimado: 10 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Identificación de errores y validaciones actuales — objetivo: Identificar los errores y validaciones actuales en el sistema de comentarios — entregable (NO resolver): Documento que describe los errores y validaciones actuales en el sistema de comentarios.
- Fase 2: Diseño de mejoras en el manejo de errores y validaciones — objetivo: Diseñar mejoras en el manejo de errores y validaciones en el sistema de comentarios — entregable (NO resolver): Documento que describe las mejoras propuestas en el manejo de errores y validaciones.
- Fase 3: Implementación y prueba de las mejoras propuestas — objetivo: Implementar y probar las mejoras propuestas en el manejo de errores y validaciones — entregable (NO resolver): Sistema de comentarios mejorado con el manejo de errores y validaciones implementado y probado.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.pragma</groupId>
    <artifactId>comments</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>comments</name>
    <description>Sistema de manejo de comentarios con validaciones y manejo de errores</description>

    <properties>
        <java.version>21</java.version>
        <springdoc-openapi-ui.version>2.5.0</springdoc-openapi-ui.version>
        <lombok.version>1.18.30</lombok.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>${springdoc-openapi-ui.version}</version>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>2.2.224</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/pragma/comments/CommentsApplication.java ===
package com.pragma.comments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CommentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommentsApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: comments-service
  datasource:
    url: jdbc:h2:mem:commentsdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    driver-class-name: org.h2.Driver
    username: sa
    password: password
  h2:
    console:
      enabled: true
      path: /h2-console
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
  profiles:
    active: dev

server:
  port: 8080
  error:
    include-message: always
    include-binding-errors: always

springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui.html
    operationsSorter: method

// === ARCHIVO: src/main/java/com/pragma/comments/domain/model/Comment.java ===
package com.pragma.comments.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

public class Comment {
    private final UUID id;
    private final UUID postId;
    private final UUID userId;

    @NotBlank(message = "El contenido del comentario no puede estar vacío")
    @Size(max = 500, message = "El contenido del comentario no puede exceder los 500 caracteres")
    private final String content;

    @NotNull(message = "La fecha de creación no puede ser nula")
    private final LocalDateTime createdAt;

    private Comment(UUID id, UUID postId, UUID userId, String content, LocalDateTime createdAt) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
        validateInvariants();
    }

    public static Comment create(UUID postId, UUID userId, String content) {
        return new Comment(
                UUID.randomUUID(),
                postId,
                userId,
                content,
                LocalDateTime.now()
        );
    }

    public Comment withUpdatedContent(String newContent) {
        if (newContent == null || newContent.trim().isEmpty()) {
            throw new IllegalArgumentException("El nuevo contenido no puede ser vacío");
        }
        if (newContent.length() > 500) {
            throw new IllegalArgumentException("El nuevo contenido no puede exceder los 500 caracteres");
        }
        return new Comment(this.id, this.postId, this.userId, newContent, this.createdAt);
    }

    private void validateInvariants() {
        if (postId == null) {
            throw new IllegalArgumentException("El postId no puede ser nulo");
        }
        if (userId == null) {
            throw new IllegalArgumentException("El userId no puede ser nulo");
        }
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("El contenido no puede estar vacío");
        }
        if (content.length() > 500) {
            throw new IllegalArgumentException("El contenido no puede exceder los 500 caracteres");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("La fecha de creación no puede ser nula");
        }
    }

    public UUID getId() {
        return id;
    }

    public UUID getPostId() {
        return postId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

// === ARCHIVO: src/main/java/com/pragma/comments/domain/port/CommentRepository.java ===
package com.pragma.comments.domain.port;

import com.pragma.comments.domain.model.Comment;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentRepository {
    Comment save(Comment comment);
    Optional<Comment> findById(UUID id);
    List<Comment> findByPostId(UUID postId);
    List<Comment> findByUserId(UUID userId);
    void deleteById(UUID id);
    boolean existsById(UUID id);
}

// === ARCHIVO: src/main/java/com/pragma/comments/domain/exception/InvalidCommentException.java ===
package com.pragma.comments.domain.exception;

import java.util.Map;
import java.util.Collections;
import java.util.HashMap;

public class InvalidCommentException extends RuntimeException {
    private final Map<String, String> errors;

    public InvalidCommentException(String message) {
        super(message);
        this.errors = Collections.emptyMap();
    }

    public InvalidCommentException(String message, Map<String, String> errors) {
        super(message);
        this.errors = new HashMap<>(errors);
    }

    public Map<String, String> getErrors() {
        return Collections.unmodifiableMap(errors);
    }

    public static InvalidCommentException forEmptyContent() {
        Map<String, String> errors = new HashMap<>();
        errors.put("content", "El contenido del comentario no puede estar vacío");
        return new InvalidCommentException("Validación fallida para el comentario", errors);
    }

    public static InvalidCommentException forMaxLengthExceeded() {
        Map<String, String> errors = new HashMap<>();
        errors.put("content", "El contenido del comentario no puede exceder los 500 caracteres");
        return new InvalidCommentException("Validación fallida para el comentario", errors);
    }

    public static InvalidCommentException forNullField(String fieldName) {
        Map<String, String> errors = new HashMap<>();
        errors.put(fieldName, "El campo " + fieldName + " no puede ser nulo");
        return new InvalidCommentException("Validación fallida para el comentario", errors);
    }

    public static InvalidCommentException forMultipleErrors(Map<String, String> errors) {
        return new InvalidCommentException("Validación fallida para el comentario", errors);
    }
}

// === ARCHIVO: src/main/java/com/pragma/comments/domain/exception/CommentNotFoundException.java ===
package com.pragma.comments.domain.exception;


import com.pragma.comments.domain.model.Comment;
import java.util.Map;
import java.util.UUID;

public class CommentNotFoundException extends RuntimeException {
    private final UUID commentId;
    private final Map<String, String> additionalInfo;

    public CommentNotFoundException(UUID commentId) {
        super("Comment not found with id: " + commentId);
        this.commentId = commentId;
        this.additionalInfo = Map.of("commentId", commentId.toString(), "error", "NOT_FOUND");
    }

    public CommentNotFoundException(UUID commentId, String message) {
        super(message);
        this.commentId = commentId;
        this.additionalInfo = Map.of("commentId", commentId.toString(), "error", "NOT_FOUND");
    }

    public CommentNotFoundException(UUID commentId, Throwable cause) {
        super("Comment not found with id: " + commentId, cause);
        this.commentId = commentId;
        this.additionalInfo = Map.of("commentId", commentId.toString(), "error", "NOT_FOUND");
    }

    public UUID getCommentId() {
        return commentId;
    }

    public Map<String, String> getAdditionalInfo() {
        return additionalInfo;
    }

    public static CommentNotFoundException forComment(UUID commentId) {
        return new CommentNotFoundException(commentId);
    }

    public static CommentNotFoundException forCommentWithMessage(UUID commentId, String message) {
        return new CommentNotFoundException(commentId, message);
    }
}

// === ARCHIVO: src/main/java/com/pragma/comments/domain/exception/CommentProcessingException.java ===
package com.pragma.comments.domain.exception;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class CommentProcessingException extends RuntimeException {
    private final UUID commentId;
    private final String processingStage;
    private final ErrorType errorType;
    private final Map<String, String> context;
    private final LocalDateTime timestamp;

    public enum ErrorType {
        VALIDATION_ERROR,
        PERSISTENCE_ERROR,
        EXTERNAL_SERVICE_ERROR,
        INTERNAL_ERROR,
        TIMEOUT_ERROR,
        CONFLICT_ERROR
    }

    public CommentProcessingException(String message, ErrorType errorType) {
        super(message);
        this.commentId = null;
        this.processingStage = "UNKNOWN";
        this.errorType = errorType;
        this.context = Map.of("errorType", errorType.name());
        this.timestamp = LocalDateTime.now();
    }

    public CommentProcessingException(UUID commentId, String message, ErrorType errorType) {
        super(message);
        this.commentId = commentId;
        this.processingStage = "UNKNOWN";
        this.errorType = errorType;
        this.context = Map.of(
            "commentId", commentId.toString(),
            "errorType", errorType.name()
        );
        this.timestamp = LocalDateTime.now();
    }

    public CommentProcessingException(UUID commentId, String processingStage, String message, ErrorType errorType) {
        super(message);
        this.commentId = commentId;
        this.processingStage = processingStage;
        this.errorType = errorType;
        this.context = Map.of(
            "commentId", commentId.toString(),
            "processingStage", processingStage,
            "errorType", errorType.name()
        );
        this.timestamp = LocalDateTime.now();
    }

    public CommentProcessingException(UUID commentId, String processingStage, String message, ErrorType errorType, Throwable cause) {
        super(message, cause);
        this.commentId = commentId;
        this.processingStage = processingStage;
        this.errorType = errorType;
        this.context = Map.of(
            "commentId", commentId.toString(),
            "processingStage", processingStage,
            "errorType", errorType.name(),
            "cause", cause.getClass().getSimpleName()
        );
        this.timestamp = LocalDateTime.now();
    }

    public UUID getCommentId() {
        return commentId;
    }

    public String getProcessingStage() {
        return processingStage;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public Map<String, String> getContext() {
        return context;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public boolean isRetryable() {
        return errorType == ErrorType.EXTERNAL_SERVICE_ERROR || 
               errorType == ErrorType.TIMEOUT_ERROR ||
               errorType == ErrorType.PERSISTENCE_ERROR;
    }

    public static CommentProcessingException validationError(String message) {
        return new CommentProcessingException(null, message, ErrorType.VALIDATION_ERROR);
    }

    public static CommentProcessingException persistenceError(UUID commentId, Throwable cause) {
        return new CommentProcessingException(commentId, "PERSISTENCE", "Failed to persist comment", ErrorType.PERSISTENCE_ERROR, cause);
    }

    public static CommentProcessingException externalServiceError(UUID commentId, String serviceName, Throwable cause) {
        return new CommentProcessingException(commentId, "EXTERNAL_SERVICE_" + serviceName, 
            "External service error during comment processing", ErrorType.EXTERNAL_SERVICE_ERROR, cause);
    }
}

// === ARCHIVO: src/main/java/com/pragma/comments/infrastructure/dto/CommentRequest.java ===
package com.pragma.comments.infrastructure.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record CommentRequest(
    @NotNull(message = "Post ID is required")
    UUID postId,
    
    @NotNull(message = "User ID is required")
    UUID userId,
    
    @NotNull(message = "Content is required")
    @Size(min = 1, max = 1000, message = "Content must be between 1 and 1000 characters")
    String content,
    
    @Size(max = 50, message = "Author name cannot exceed 50 characters")
    String authorName,
    
    String metadata
) {
    public CommentRequest {
        if (content != null) {
            content = content.trim();
        }
        if (authorName != null) {
            authorName = authorName.trim();
        }
    }

    public static CommentRequest create(UUID postId, UUID userId, String content) {
        return new CommentRequest(postId, userId, content, null, null);
    }

    public static CommentRequest createWithAuthor(UUID postId, UUID userId, String content, String authorName) {
        return new CommentRequest(postId, userId, content, authorName, null);
    }

    public boolean hasAuthor() {
        return authorName != null && !authorName.isBlank();
    }

    public boolean hasMetadata() {
        return metadata != null && !metadata.isBlank();
    }

    public String getContentPreview(int maxLength) {
        if (content == null) {
            return "";
        }
        return content.length() <= maxLength ? content : content.substring(0, maxLength) + "...";
    }
}

// === ARCHIVO: src/main/java/com/pragma/comments/infrastructure/dto/CommentResponse.java ===
package com.pragma.comments.infrastructure.dto;

import com.pragma.comments.domain.model.Comment;
import java.time.LocalDateTime;
import java.util.UUID;

public record CommentResponse(
    UUID id,
    UUID postId,
    UUID userId,
    String content,
    LocalDateTime createdAt
) {
    public static CommentResponse fromDomain(Comment comment) {
        return new CommentResponse(
            comment.getId(),
            comment.getPostId(),
            comment.getUserId(),
            comment.getContent(),
            comment.getCreatedAt()
        );
    }

    public static CommentResponse withoutSensitiveData(Comment comment) {
        return new CommentResponse(
            comment.getId(),
            comment.getPostId(),
            comment.getUserId(),
            maskContent(comment.getContent()),
            comment.getCreatedAt()
        );
    }

    private static String maskContent(String content) {
        if (content == null || content.length() <= 4) {
            return "****";
        }
        return content.substring(0, 2) + "**" + content.substring(content.length() - 2);
    }

    public boolean isValid() {
        return id != null && postId != null && userId != null 
            && content != null && !content.isBlank() && createdAt != null;
    }

    public String toDisplayString() {
        return String.format("Comment[id=%s, postId=%s, userId=%s, contentLength=%d, createdAt=%s]",
            id, postId, userId, content != null ? content.length() : 0, createdAt);
    }
}

// === ARCHIVO: src/main/java/com/pragma/comments/application/usecase/CommentUseCase.java ===
package com.pragma.comments.application.usecase;

import com.pragma.comments.application.validator.CommentValidator;
import com.pragma.comments.domain.exception.CommentNotFoundException;
import com.pragma.comments.domain.exception.CommentProcessingException;
import com.pragma.comments.domain.exception.InvalidCommentException;
import com.pragma.comments.domain.model.Comment;
import com.pragma.comments.domain.port.CommentRepository;
import com.pragma.comments.infrastructure.dto.CommentRequest;
import com.pragma.comments.infrastructure.dto.CommentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CommentUseCase.class);

    private final CommentRepository commentRepository;
    private final CommentValidator commentValidator;

    public CommentUseCase(CommentRepository commentRepository, CommentValidator commentValidator) {
        this.commentRepository = commentRepository;
        this.commentValidator = commentValidator;
    }

    @Transactional
    public CommentResponse createComment(CommentRequest request) {
        logger.info("Creating comment for postId: {} and userId: {}", request.getPostId(), request.getUserId());
        
        try {
            commentValidator.validateForCreation(request);
            
            Comment comment = Comment.create(
                request.getPostId(),
                request.getUserId(),
                request.getContent()
            );
            
            Comment savedComment = commentRepository.save(comment);
            logger.info("Comment created successfully with id: {}", savedComment.getId());
            
            return CommentResponse.fromDomain(savedComment);
            
        } catch (InvalidCommentException e) {
            logger.warn("Validation failed while creating comment: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error creating comment: {}", e.getMessage(), e);
            throw CommentProcessingException.forCreation(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public CommentResponse getCommentById(UUID id) {
        logger.info("Retrieving comment with id: {}", id);
        
        if (id == null) {
            throw InvalidCommentException.forNullField("id");
        }
        
        return commentRepository.findById(id)
            .map(CommentResponse::fromDomain)
            .orElseThrow(() -> {
                logger.warn("Comment not found with id: {}", id);
                return CommentNotFoundException.forId(id);
            });
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsByPostId(UUID postId) {
        logger.info("Retrieving comments for postId: {}", postId);
        
        if (postId == null) {
            throw InvalidCommentException.forNullField("postId");
        }
        
        List<Comment> comments = commentRepository.findByPostId(postId);
        logger.info("Found {} comments for postId: {}", comments.size(), postId);
        
        return comments.stream()
            .map(CommentResponse::fromDomain)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsByUserId(UUID userId) {
        logger.info("Retrieving comments for userId: {}", userId);
        
        if (userId == null) {
            throw InvalidCommentException.forNullField("userId");
        }
        
        List<Comment> comments = commentRepository.findByUserId(userId);
        logger.info("Found {} comments for userId: {}", comments.size(), userId);
        
        return comments.stream()
            .map(CommentResponse::fromDomain)
            .collect(Collectors.toList());
    }

    @Transactional
    public CommentResponse updateComment(UUID id, String newContent) {
        logger.info("Updating comment with id: {}", id);
        
        try {
            commentValidator.validateContent(newContent);
            
            Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Comment not found for update with id: {}", id);
                    return CommentNotFoundException.forId(id);
                });
            
            Comment updatedComment = existingComment.withUpdatedContent(newContent);
            Comment savedComment = commentRepository.save(updatedComment);
            
            logger.info("Comment updated successfully with id: {}", savedComment.getId());
            return CommentResponse.fromDomain(savedComment);
            
        } catch (InvalidCommentException e) {
            logger.warn("Validation failed while updating comment: {}", e.getMessage());
            throw e;
        } catch (CommentNotFoundException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error updating comment: {}", e.getMessage(), e);
            throw CommentProcessingException.forUpdate(e.getMessage());
        }
    }

    @Transactional
    public void deleteComment(UUID id) {
        logger.info("Deleting comment with id: {}", id);
        
        if (id == null) {
            throw InvalidCommentException.forNullField("id");
        }
        
        if (!commentRepository.existsById(id)) {
            logger.warn("Comment not found for deletion with id: {}", id);
            throw CommentNotFoundException.forId(id);
        }
        
        try {
            commentRepository.deleteById(id);
            logger.info("Comment deleted successfully with id: {}", id);
        } catch (Exception e) {
            logger.error("Error deleting comment: {}", e.getMessage(), e);
            throw CommentProcessingException.forDeletion(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public boolean existsComment(UUID id) {
        if (id == null) {
            return false;
        }
        return commentRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public long countCommentsByPostId(UUID postId) {
        if (postId == null) {
            return 0;
        }
        return commentRepository.findByPostId(postId).size();
    }
}

// === ARCHIVO: src/main/java/com/pragma/comments/application/validator/CommentValidator.java ===
package com.pragma.comments.application.validator;

import com.pragma.comments.domain.exception.InvalidCommentException;
import com.pragma.comments.infrastructure.dto.CommentRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class CommentValidator {

    private static final int MIN_CONTENT_LENGTH = 1;
    private static final int MAX_CONTENT_LENGTH = 1000;
    private static final Pattern CONTENT_PATTERN = Pattern.compile("^[^<>\\x00-\\x1F]*$");
    private static final int MAX_POST_ID_LENGTH = 36;
    private static final int MAX_USER_ID_LENGTH = 36;

    public void validateForCreation(CommentRequest request) {
        Map<String, String> errors = new HashMap<>();
        
        validatePostId(request.getPostId(), errors);
        validateUserId(request.getUserId(), errors);
        validateContent(request.getContent(), errors);
        
        if (!errors.isEmpty()) {
            throw InvalidCommentException.forMultipleErrors(errors);
        }
    }

    public void validateContent(String content) {
        Map<String, String> errors = new HashMap<>();
        validateContent(content, errors);
        
        if (!errors.isEmpty()) {
            throw InvalidCommentException.forMultipleErrors(errors);
        }
    }

    private void validatePostId(java.util.UUID postId, Map<String, String> errors) {
        if (postId == null) {
            errors.put("postId", "El ID del post no puede ser nulo");
            return;
        }
        
        String postIdString = postId.toString();
        if (postIdString.isBlank()) {
            errors.put("postId", "El ID del post no puede estar vacío");
        } else if (postIdString.length() > MAX_POST_ID_LENGTH) {
            errors.put("postId", "El ID del post excede la longitud máxima permitida");
        }
    }

    private void validateUserId(java.util.UUID userId, Map<String, String> errors) {
        if (userId == null) {
            errors.put("userId", "El ID del usuario no puede ser nulo");
            return;
        }
        
        String userIdString = userId.toString();
        if (userIdString.isBlank()) {
            errors.put("userId", "El ID del usuario no puede estar vacío");
        } else if (userIdString.length() > MAX_USER_ID_LENGTH) {
            errors.put("userId", "El ID del usuario excede la longitud máxima permitida");
        }
    }

    private void validateContent(String content, Map<String, String> errors) {
        if (content == null) {
            errors.put("content", "El contenido del comentario no puede ser nulo");
            return;
        }
        
        String trimmedContent = content.trim();
        
        if (trimmedContent.isEmpty()) {
            errors.put("content", "El contenido del comentario no puede estar vacío");
            return;
        }
        
        if (trimmedContent.length() < MIN_CONTENT_LENGTH) {
            errors.put("content", "El contenido del comentario debe tener al menos " + MIN_CONTENT_LENGTH + " carácter(es)");
        }
        
        if (trimmedContent.length() > MAX_CONTENT_LENGTH) {
            errors.put("content", "El contenido del comentario excede el límite máximo de " + MAX_CONTENT_LENGTH + " caracteres");
        }
        
        if (!CONTENT_PATTERN.matcher(trimmedContent).matches()) {
            errors.put("content", "El contenido del comentario contiene caracteres no permitidos");
        }
        
        if (containsForbiddenPatterns(trimmedContent)) {
            errors.put("content", "El contenido del comentario contiene patrones forbidden");
        }
    }

    private boolean containsForbiddenPatterns(String content) {
        String lowerContent = content.toLowerCase();
        return lowerContent.contains("spam") || 
               lowerContent.contains("malicious") ||
               lowerContent.contains("<script>") ||
               lowerContent.contains("javascript:");
    }

    public boolean isValidContentLength(String content) {
        if (content == null) {
            return false;
        }
        int length = content.trim().length();
        return length >= MIN_CONTENT_LENGTH && length <= MAX_CONTENT_LENGTH;
    }

    public boolean isValidPostId(java.util.UUID postId) {
        return postId != null && !postId.toString().isBlank();
    }

    public boolean isValidUserId(java.util.UUID userId) {
        return userId != null && !userId.toString().isBlank();
    }

    public int getMaxContentLength() {
        return MAX_CONTENT_LENGTH;
    }

    public int getMinContentLength() {
        return MIN_CONTENT_LENGTH;
    }
}

// === ARCHIVO: src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java ===
package com.pragma.comments.infrastructure.adapter;

import com.pragma.comments.domain.model.Comment;
import com.pragma.comments.domain.port.CommentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CommentJpaAdapter implements CommentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == null) {
            entityManager.persist(comment);
            return comment;
        } else {
            return entityManager.merge(comment);
        }
    }

    @Override
    public Optional<Comment> findById(UUID id) {
        Comment comment = entityManager.find(Comment.class, id);
        return Optional.ofNullable(comment);
    }

    @Override
    public List<Comment> findByPostId(UUID postId) {
        return entityManager.createQuery(
                "SELECT c FROM Comment c WHERE c.postId = :postId ORDER BY c.createdAt DESC",
                Comment.class)
                .setParameter("postId", postId)
                .getResultList();
    }

    @Override
    public List<Comment> findByUserId(UUID userId) {
        return entityManager.createQuery(
                "SELECT c FROM Comment c WHERE c.userId = :userId ORDER BY c.createdAt DESC",
                Comment.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public void deleteById(UUID id) {
        Comment comment = entityManager.find(Comment.class, id);
        if (comment != null) {
            entityManager.remove(comment);
        }
    }

    @Override
    public boolean existsById(UUID id) {
        Comment comment = entityManager.find(Comment.class, id);
        return comment != null;
    }
}

// === ARCHIVO: src/main/java/com/pragma/comments/infrastructure/controller/CommentController.java ===
package com.pragma.comments.infrastructure.controller;

import com.pragma.comments.application.usecase.CommentUseCase;
import com.pragma.comments.domain.model.Comment;
import com.pragma.comments.infrastructure.dto.CommentRequest;
import com.pragma.comments.infrastructure.dto.CommentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comments")
@Tag(name = "Comments", description = "API para gestión de comentarios")
public class CommentController {

    private final CommentUseCase commentUseCase;

    public CommentController(CommentUseCase commentUseCase) {
        this.commentUseCase = commentUseCase;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo comentario", description = "Crea un nuevo comentario en el sistema")
    public ResponseEntity<CommentResponse> createComment(@Valid @RequestBody CommentRequest request) {
        Comment comment = commentUseCase.createComment(
                request.postId(),
                request.userId(),
                request.content()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(CommentResponse.fromDomain(comment));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un comentario por ID", description = "Retrieva un comentario específico por su identificador")
    public ResponseEntity<CommentResponse> getCommentById(@PathVariable UUID id) {
        Comment comment = commentUseCase.getCommentById(id);
        return ResponseEntity.ok(CommentResponse.fromDomain(comment));
    }

    @GetMapping("/post/{postId}")
    @Operation(summary = "Obtener comentarios por post", description = "Retrieva todos los comentarios asociados a un post específico")
    public ResponseEntity<List<CommentResponse>> getCommentsByPostId(@PathVariable UUID postId) {
        List<Comment> comments = commentUseCase.getCommentsByPostId(postId);
        List<CommentResponse> responses = comments.stream()
                .map(CommentResponse::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Obtener comentarios por usuario", description = "Retrieva todos los comentarios creados por un usuario específico")
    public ResponseEntity<List<CommentResponse>> getCommentsByUserId(@PathVariable UUID userId) {
        List<Comment> comments = commentUseCase.getCommentsByUserId(userId);
        List<CommentResponse> responses = comments.stream()
                .map(CommentResponse::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un comentario", description = "Actualiza el contenido de un comentario existente")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable UUID id,
            @Valid @RequestBody CommentRequest request) {
        Comment comment = commentUseCase.updateComment(id, request.content());
        return ResponseEntity.ok(CommentResponse.fromDomain(comment));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un comentario", description = "Elimina un comentario del sistema por su identificador")
    public ResponseEntity<Void> deleteComment(@PathVariable UUID id) {
        commentUseCase.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}

// === ARCHIVO: src/main/java/com/pragma/comments/infrastructure/config/GlobalExceptionHandler.java ===
package com.pragma.comments.infrastructure.config;

import com.pragma.comments.domain.exception.CommentNotFoundException;
import com.pragma.comments.domain.exception.CommentProcessingException;
import com.pragma.comments.domain.exception.InvalidCommentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCommentException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidCommentException(InvalidCommentException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Validación de comentario fallida");
        response.put("message", ex.getMessage());
        response.put("details", ex.getErrors());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleCommentNotFoundException(CommentNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("error", "Comentario no encontrado");
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(CommentProcessingException.class)
    public ResponseEntity<Map<String, Object>> handleCommentProcessingException(CommentProcessingException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error", "Error al procesar el comentario");
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> validationErrors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            validationErrors.put(fieldName, errorMessage);
        });

        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Error de validación de datos");
        response.put("message", "Los datos proporcionados no son válidos");
        response.put("details", validationErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error", "Error interno del servidor");
        response.put("message", "Ha ocurrido un error inesperado. Por favor, contacte al administrador.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

// === ARCHIVO: src/main/java/com/pragma/comments/infrastructure/config/OpenApiConfig.java ===
package com.pragma.comments.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuración centralizada de OpenAPI/Swagger para la documentación automática
 * de los endpoints de la API de comentarios.
 * 
 * Esta configuración personaliza la experiencia de documentación interactiva
 * proporcionando información del proyecto, servidores disponibles y componentes
 * reutilizables como esquemas de respuesta y parámetros.
 */
@Configuration
public class OpenApiConfig {

    @Value("${spring.application.name:comments}")
    private String applicationName;

    @Value("${server.port:8080}")
    private String serverPort;

    /**
     * Define la configuración global de la API которая se muestra en la interfaz
     * de Swagger UI. Incluye título, versión, descripción y información de contacto.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión de Comentarios")
                        .version("1.0.0")
                        .description("""
                                Sistema de manejo de comentarios E2E con validaciones robustas
                                y manejo centralizado de errores. La API permite crear, consultar,
                                actualizar y eliminar comentarios asociados a publicaciones.
                                
                                ## Características principales:
                                - CRUD completo de comentarios
                                - Validación de contenido con límites de longitud
                                - Manejo centralizado de excepciones
                                - Documentación interactiva con Swagger UI
                                
                                ## Códigos de respuesta:
                                - 200: Operación exitosa
                                - 201: Recurso creado correctamente
                                - 400: Error de validación o datos inválidos
                                - 404: Recurso no encontrado
                                - 500: Error interno del servidor
                                """)
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("desarrollo@pragma.com")
                                .url("https://www.pragma.com.co"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .servers(List.of(
                        createServer("http://localhost:" + serverPort, "Servidor local de desarrollo"),
                        createServer("https://api.pragma.com/v1", "Servidor de producción"),
                        createServer("https://staging.pragma.com/v1", "Servidor de staging")
                ))
                .components(new Components());
    }

    /**
     * Crea un objeto de servidor que se muestra en la lista desplegable
     * de la interfaz de Swagger para cambiar entre entornos de ejecución.
     * 
     * @param url La URL base del servidor
     * @param description Descripción legible del propósito del servidor
     * @return Instancia configurada de Server para OpenAPI
     */
    private Server createServer(String url, String description) {
        return new Server()
                .url(url)
                .description(description);
    }

    /**
     * Configura componentes adicionales como esquemas reutilizables, headers
     * estándar y autenticación. Este método permite definir objetos que se
     * comparten entre múltiples endpoints para mantener consistencia.
     */
    @Bean
    public Components customComponents() {
        return new Components();
    }

// === ARCHIVO: src/test/java/com/pragma/comments/application/usecase/CommentUseCaseTest.java ===
package com.pragma.comments.application.usecase;

import com.pragma.comments.domain.exception.CommentNotFoundException;
import com.pragma.comments.domain.exception.InvalidCommentException;
import com.pragma.comments.domain.model.Comment;
import com.pragma.comments.domain.port.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentUseCaseTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentUseCase commentUseCase;

    private UUID testPostId;
    private UUID testUserId;
    private String validContent;

    @BeforeEach
    void setUp() {
        testPostId = UUID.randomUUID();
        testUserId = UUID.randomUUID();
        validContent = "Este es un comentario válido para testing";
    }

    @Test
    void createComment_shouldReturnCreatedComment() {
        when(commentRepository.save(any(Comment.class))).thenAnswer(invocation -> {
            Comment comment = invocation.getArgument(0);
            return Comment.create(comment.getPostId(), comment.getUserId(), comment.getContent());
        });

        Comment result = commentUseCase.createComment(testPostId, testUserId, validContent);

        assertNotNull(result);
        assertEquals(testPostId, result.getPostId());
        assertEquals(testUserId, result.getUserId());
        assertEquals(validContent, result.getContent());
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    void createComment_shouldThrowExceptionForEmptyContent() {
        when(commentRepository.save(any(Comment.class))).thenThrow(InvalidCommentException.forEmptyContent());

        assertThrows(InvalidCommentException.class, () ->
            commentUseCase.createComment(testPostId, testUserId, ""));
    }

    @Test
    void createComment_shouldThrowExceptionForNullContent() {
        assertThrows(InvalidCommentException.class, () ->
            commentUseCase.createComment(testPostId, testUserId, null));
    }

    @Test
    void getCommentById_shouldReturnCommentWhenExists() {
        UUID commentId = UUID.randomUUID();
        Comment existingComment = Comment.create(testPostId, testUserId, validContent);

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(existingComment));

        Comment result = commentUseCase.getCommentById(commentId);

        assertNotNull(result);
        assertEquals(existingComment.getId(), result.getId());
        verify(commentRepository, times(1)).findById(commentId);
    }

    @Test
    void getCommentById_shouldThrowExceptionWhenNotFound() {
        UUID nonExistentId = UUID.randomUUID();
        when(commentRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        assertThrows(CommentNotFoundException.class, () ->
            commentUseCase.getCommentById(nonExistentId));
    }

    @Test
    void getCommentsByPostId_shouldReturnListOfComments() {
        UUID postId = UUID.randomUUID();
        List<Comment> comments = List.of(
            Comment.create(postId, testUserId, "Comentario 1"),
            Comment.create(postId, testUserId, "Comentario 2"),
            Comment.create(postId, testUserId, "Comentario 3")
        );

        when(commentRepository.findByPostId(postId)).thenReturn(comments);

        List<Comment> result = commentUseCase.getCommentsByPostId(postId);

        assertEquals(3, result.size());
        assertTrue(result.stream().allMatch(c -> c.getPostId().equals(postId)));
        verify(commentRepository, times(1)).findByPostId(postId);
    }

    @Test
    void getCommentsByPostId_shouldReturnEmptyListWhenNoComments() {
        UUID postIdWithoutComments = UUID.randomUUID();
        when(commentRepository.findByPostId(postIdWithoutComments)).thenReturn(Collections.emptyList());

        List<Comment> result = commentUseCase.getCommentsByPostId(postIdWithoutComments);

        assertTrue(result.isEmpty());
        verify(commentRepository, times(1)).findByPostId(postIdWithoutComments);
    }

    @Test
    void getCommentsByUserId_shouldReturnListOfComments() {
        UUID userId = UUID.randomUUID();
        List<Comment> comments = List.of(
            Comment.create(testPostId, userId, "Comentario del usuario 1"),
            Comment.create(testPostId, userId, "Comentario del usuario 2")
        );

        when(commentRepository.findByUserId(userId)).thenReturn(comments);

        List<Comment> result = commentUseCase.getCommentsByUserId(userId);

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(c -> c.getUserId().equals(userId)));
        verify(commentRepository, times(1)).findByUserId(userId);
    }

    @Test
    void updateComment_shouldUpdateAndReturnComment() {
        UUID commentId = UUID.randomUUID();
        String newContent = "Contenido actualizado";
        Comment existingComment = Comment.create(testPostId, testUserId, validContent);

        when(commentRepository.existsById(commentId)).thenReturn(true);
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(existingComment));
        when(commentRepository.save(any(Comment.class))).thenAnswer(inv -> inv.getArgument(0));

        Comment result = commentUseCase.updateComment(commentId, newContent);

        assertNotNull(result);
        assertEquals(newContent, result.getContent());
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    void updateComment_shouldThrowExceptionWhenNotFound() {
        UUID nonExistentId = UUID.randomUUID();
        when(commentRepository.existsById(nonExistentId)).thenReturn(false);

        assertThrows(CommentNotFoundException.class, () ->
            commentUseCase.updateComment(nonExistentId, "Nuevo contenido"));
    }

    @Test
    void deleteComment_shouldCallRepositoryDelete() {
        UUID commentId = UUID.randomUUID();
        when(commentRepository.existsById(commentId)).thenReturn(true);

        commentUseCase.deleteComment(commentId);

        verify(commentRepository, times(1)).deleteById(commentId);
    }

    @Test
    void deleteComment_shouldThrowExceptionWhenNotFound() {
        UUID nonExistentId = UUID.randomUUID();
        when(commentRepository.existsById(nonExistentId)).thenReturn(false);

        assertThrows(CommentNotFoundException.class, () ->
            commentUseCase.deleteComment(nonExistentId));
    }
}

// === ARCHIVO: src/test/java/com/pragma/comments/infrastructure/controller/CommentControllerTest.java ===
package com.pragma.comments.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.comments.application.usecase.CommentUseCase;
import com.pragma.comments.domain.exception.CommentNotFoundException;
import com.pragma.comments.domain.exception.InvalidCommentException;
import com.pragma.comments.domain.model.Comment;
import com.pragma.comments.infrastructure.dto.CommentRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CommentUseCase commentUseCase;

    private UUID testPostId;
    private UUID testUserId;
    private String validContent;

    @BeforeEach
    void setUp() {
        testPostId = UUID.randomUUID();
        testUserId = UUID.randomUUID();
        validContent = "Contenido válido para prueba de comentario";
    }

    @Test
    void createComment_shouldReturn201WhenSuccessful() throws Exception {
        CommentRequest request = new CommentRequest(testPostId, testUserId, validContent);
        Comment createdComment = Comment.create(testPostId, testUserId, validContent);

        when(commentUseCase.createComment(any(UUID.class), any(UUID.class), any(String.class)))
            .thenReturn(createdComment);

        mockMvc.perform(post("/api/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.postId").value(testPostId.toString()))
            .andExpect(jsonPath("$.userId").value(testUserId.toString()))
            .andExpect(jsonPath("$.content").value(validContent));

        verify(commentUseCase, times(1)).createComment(any(UUID.class), any(UUID.class), any(String.class));
    }

    @Test
    void createComment_shouldReturn400ForInvalidContent() throws Exception {
        CommentRequest request = new CommentRequest(testPostId, testUserId, "");

        when(commentUseCase.createComment(any(UUID.class), any(UUID.class), any(String.class)))
            .thenThrow(InvalidCommentException.forEmptyContent());

        mockMvc.perform(post("/api/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getCommentById_shouldReturn200WhenFound() throws Exception {
        UUID commentId = UUID.randomUUID();
        Comment comment = Comment.create(testPostId, testUserId, validContent);

        when(commentUseCase.getCommentById(commentId)).thenReturn(comment);

        mockMvc.perform(get("/api/comments/{id}", commentId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(commentId.toString()))
            .andExpect(jsonPath("$.content").value(validContent));

        verify(commentUseCase, times(1)).getCommentById(commentId);
    }

    @Test
    void getCommentById_shouldReturn404WhenNotFound() throws Exception {
        UUID nonExistentId = UUID.randomUUID();

        when(commentUseCase.getCommentById(nonExistentId))
            .thenThrow(new CommentNotFoundException("Comentario no encontrado"));

        mockMvc.perform(get("/api/comments/{id}", nonExistentId))
            .andExpect(status().isNotFound());
    }

    @Test
    void getCommentsByPostId_shouldReturn200WithList() throws Exception {
        UUID postId = UUID.randomUUID();
        List<Comment> comments = List.of(
            Comment.create(postId, testUserId, "Comentario 1"),
            Comment.create(postId, testUserId, "Comentario 2")
        );

        when(commentUseCase.getCommentsByPostId(postId)).thenReturn(comments);

        mockMvc.perform(get("/api/comments/post/{postId}", postId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2));

        verify(commentUseCase, times(1)).getCommentsByPostId(postId);
    }

    @Test
    void getCommentsByUserId_shouldReturn200WithList() throws Exception {
        UUID userId = UUID.randomUUID();
        List<Comment> comments = List.of(
            Comment.create(testPostId, userId, "Comentario del usuario")
        );

        when(commentUseCase.getCommentsByUserId(userId)).thenReturn(comments);

        mockMvc.perform(get("/api/comments/user/{userId}", userId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(1))
            .andExpect(jsonPath("$[0].userId").value(userId.toString()));

        verify(commentUseCase, times(1)).getCommentsByUserId(userId);
    }

    @Test
    void updateComment_shouldReturn200WhenSuccessful() throws Exception {
        UUID commentId = UUID.randomUUID();
        String newContent = "Contenido actualizado correctamente";
        CommentRequest request = new CommentRequest(null, null, newContent);
        Comment updatedComment = Comment.create(testPostId, testUserId, newContent);

        when(commentUseCase.updateComment(eq(commentId), any(String.class))).thenReturn(updatedComment);

        mockMvc.perform(put("/api/comments/{id}", commentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content").value(newContent));

        verify(commentUseCase, times(1)).updateComment(eq(commentId), any(String.class));
    }

    @Test
    void updateComment_shouldReturn404WhenNotFound() throws Exception {
        UUID nonExistentId = UUID.randomUUID();
        CommentRequest request = new CommentRequest(null, null, "Nuevo contenido");

        when(commentUseCase.updateComment(eq(nonExistentId), any(String.class)))
            .thenThrow(new CommentNotFoundException("Comentario no encontrado"));

        mockMvc.perform(put("/api/comments/{id}", nonExistentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isNotFound());
    }

    @Test
    void deleteComment_shouldReturn204WhenSuccessful() throws Exception {
        UUID commentId = UUID.randomUUID();

        doNothing().when(commentUseCase).deleteComment(commentId);

        mockMvc.perform(delete("/api/comments/{id}", commentId))
            .andExpect(status().isNoContent());

        verify(commentUseCase, times(1)).deleteComment(commentId);
    }

    @Test
    void deleteComment_shouldReturn404WhenNotFound() throws Exception {
        UUID nonExistentId = UUID.randomUUID();

        doThrow(new CommentNotFoundException("Comentario no encontrado"))
            .when(commentUseCase).deleteComment(nonExistentId);

        mockMvc.perform(delete("/api/comments/{id}", nonExistentId))
            .andExpect(status().isNotFound());
    }

    @Test
    void createComment_shouldReturn400ForNullFields() throws Exception {
        CommentRequest request = new CommentRequest(null, null, null);

        when(commentUseCase.createComment(any(), any(), any()))
            .thenThrow(InvalidCommentException.forMultipleErrors(Map.of(
                "postId", "no puede ser nulo",
                "userId", "no puede ser nulo",
                "content", "no puede ser vacío"
            )));

        mockMvc.perform(post("/api/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }
}

// === ARCHIVO: src/test/java/com/pragma/comments/application/validator/CommentValidatorTest.java ===
package com.pragma.comments.application.validator;

import com.pragma.comments.domain.exception.InvalidCommentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CommentValidatorTest {

    @InjectMocks
    private CommentValidator commentValidator;

    private UUID validPostId;
    private UUID validUserId;
    private String validContent;

    @BeforeEach
    void setUp() {
        validPostId = UUID.randomUUID();
        validUserId = UUID.randomUUID();
        validContent = "Contenido válido para el comentario de prueba";
    }

    @Test
    void validate_shouldNotThrowExceptionForValidData() {
        assertDoesNotThrow(() ->
            commentValidator.validate(validPostId, validUserId, validContent));
    }

    @Test
    void validate_shouldThrowExceptionForNullPostId() {
        InvalidCommentException exception = assertThrows(
            InvalidCommentException.class,
            () -> commentValidator.validate(null, validUserId, validContent)
        );

        Map<String, String> errors = exception.getErrors();
        assertTrue(errors.containsKey("postId"));
        assertEquals("no puede ser nulo", errors.get("postId"));
    }

    @Test
    void validate_shouldThrowExceptionForNullUserId() {
        InvalidCommentException exception = assertThrows(
            InvalidCommentException.class,
            () -> commentValidator.validate(validPostId, null, validContent)
        );

        Map<String, String> errors = exception.getErrors();
        assertTrue(errors.containsKey("userId"));
        assertEquals("no puede ser nulo", errors.get("userId"));
    }

    @Test
    void validate_shouldThrowExceptionForEmptyContent() {
        InvalidCommentException exception = assertThrows(
            InvalidCommentException.class,
            () -> commentValidator.validate(validPostId, validUserId, "")
        );

        Map<String, String> errors = exception.getErrors();
        assertTrue(errors.containsKey("content"));
        assertTrue(errors.get("content").contains("vacío") ||
                    errors.get("content").contains("empty"));
    }

    @Test
    void validate_shouldThrowExceptionForNullContent() {
        InvalidCommentException exception = assertThrows(
            InvalidCommentException.class,
            () -> commentValidator.validate(validPostId, validUserId, null)
        );

        Map<String, String> errors = exception.getErrors();
        assertTrue(errors.containsKey("content"));
    }

    @Test
    void validate_shouldThrowExceptionForContentExceedingMaxLength() {
        String longContent = "a".repeat(501);

        InvalidCommentException exception = assertThrows(
            InvalidCommentException.class,
            () -> commentValidator.validate(validPostId, validUserId, longContent)
        );

        Map<String, String> errors = exception.getErrors();
        assertTrue(errors.containsKey("content"));
        assertTrue(errors.get("content").contains("500") ||
                    errors.get("content").contains("máximo") ||
                    errors.get("content").contains("max"));
    }

    @Test
    void validate_shouldCollectMultipleErrors() {
        InvalidCommentException exception = assertThrows(
            InvalidCommentException.class,
            () -> commentValidator.validate(null, null, "")
        );

        Map<String, String> errors = exception.getErrors();
        assertTrue(errors.containsKey("postId"));
        assertTrue(errors.containsKey("userId"));
        assertTrue(errors.containsKey("content"));
    }

    @Test
    void validateContent_shouldPassForValidContent() {
        assertDoesNotThrow(() -> commentValidator.validateContent(validContent));
    }

    @Test
    void validateContent_shouldPassForContentAtMaxLength() {
        String maxContent = "a".repeat(500);
        assertDoesNotThrow(() -> commentValidator.validateContent(maxContent));
    }

    @Test
    void validateContent_shouldPassForSingleCharacter() {
        assertDoesNotThrow(() -> commentValidator.validateContent("a"));
    }

    @Test
    void validateRequiredField_shouldThrowForNullValue() {
        InvalidCommentException exception = assertThrows(
            InvalidCommentException.class,
            () -> commentValidator.validateRequiredField(null, "testField")
        );

        assertEquals("testField no puede ser nulo", exception.getMessage());
    }

    @Test
    void validateRequiredField_shouldNotThrowForNonNullValue() {
        assertDoesNotThrow(() ->
            commentValidator.validateRequiredField("valor", "testField"));
    }

    @Test
    void validateContentLength_shouldPassForValidLength() {
        assertDoesNotThrow(() ->
            commentValidator.validateContentLength(validContent));
    }

    @Test
    void validateContentLength_shouldThrowForContentTooLong() {
        String tooLongContent = "x".repeat(1000);

        InvalidCommentException exception = assertThrows(
            InvalidCommentException.class,
            () -> commentValidator.validateContentLength(tooLongContent)
        );

        assertNotNull(exception.getErrors().get("content"));
    }

    @Test
    void validateContentLength_shouldThrowForEmptyContent() {
        InvalidCommentException exception = assertThrows(
            InvalidCommentException.class,
            () -> commentValidator.validateContentLength("")
        );

        assertNotNull(exception.getErrors().get("content"));
    }

    @Test
    void validateContentLength_shouldThrowForBlankContent() {
        InvalidCommentException exception = assertThrows(
            InvalidCommentException.class,
            () -> commentValidator.validateContentLength("   ")
        );

        assertNotNull(exception.getErrors().get("content"));
    }
}
```
