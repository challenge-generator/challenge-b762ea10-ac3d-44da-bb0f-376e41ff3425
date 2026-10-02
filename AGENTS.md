# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Manejo de errores y validaciones en un sistema de comentarios E2E**.

| | |
|---|---|
| Tema | E2E prod comments 1790905766 |
| Nivel | senior-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean |
| Tiempo estimado | 10 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web n/a
- org.springframework.boot:spring-boot-starter-data-jpa n/a
- org.springframework.boot:spring-boot-starter-validation n/a
- org.springframework.boot:spring-boot-starter-test n/a
- org.projectlombok:lombok 1.18.30
- org.springdoc:springdoc-openapi-starter-webmvc-ui 2.5.0
- com.h2database:h2 2.2.224
- org.postgresql:postgresql n/a

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Identificación de errores y validaciones actuales**: Documento que describe los errores y validaciones actuales en el sistema de comentarios.
- **Fase 2 — Diseño de mejoras en el manejo de errores y validaciones**: Documento que describe las mejoras propuestas en el manejo de errores y validaciones.
- **Fase 3 — Implementación y prueba de las mejoras propuestas**: Sistema de comentarios mejorado con el manejo de errores y validaciones implementado y probado.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Lo que falta y tenes que completar

### 1. Referencias colgando (21)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/pragma/comments/application/usecase/CommentUseCase.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/comments/infrastructure/controller/CommentController.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/comments/infrastructure/config/OpenApiConfig.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/pragma/comments/infrastructure/controller/CommentControllerTest.java` — `com.fasterxml.jackson`
      El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/comments/domain/exception/CommentProcessingException.java` — `ErrorType.name`
      Se invoca `name` sobre `ErrorType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/application/usecase/CommentUseCase.java` — `CommentRequest.getPostId`
      Se invoca `getPostId` sobre `CommentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/application/usecase/CommentUseCase.java` — `CommentRequest.getUserId`
      Se invoca `getUserId` sobre `CommentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/application/usecase/CommentUseCase.java` — `CommentRequest.getContent`
      Se invoca `getContent` sobre `CommentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/application/validator/CommentValidator.java` — `CommentRequest.getPostId`
      Se invoca `getPostId` sobre `CommentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/application/validator/CommentValidator.java` — `CommentRequest.getUserId`
      Se invoca `getUserId` sobre `CommentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/application/validator/CommentValidator.java` — `CommentRequest.getContent`
      Se invoca `getContent` sobre `CommentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/controller/CommentController.java` — `CommentRequest.postId`
      Se invoca `postId` sobre `CommentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/controller/CommentController.java` — `CommentRequest.userId`
      Se invoca `userId` sobre `CommentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/comments/infrastructure/controller/CommentController.java` — `CommentRequest.content`
      Se invoca `content` sobre `CommentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/comments/application/usecase/CommentUseCaseTest.java` — `Comment.size`
      Se invoca `size` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/comments/application/usecase/CommentUseCaseTest.java` — `Comment.stream`
      Se invoca `stream` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/comments/application/usecase/CommentUseCaseTest.java` — `Comment.isEmpty`
      Se invoca `isEmpty` sobre `Comment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/comments/application/validator/CommentValidatorTest.java` — `CommentValidator.validate`
      Se invoca `validate` sobre `CommentValidator`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/comments/application/validator/CommentValidatorTest.java` — `CommentValidator.validateRequiredField`
      Se invoca `validateRequiredField` sobre `CommentValidator`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/comments/application/validator/CommentValidatorTest.java` — `InvalidCommentException.getMessage`
      Se invoca `getMessage` sobre `InvalidCommentException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/comments/application/validator/CommentValidatorTest.java` — `CommentValidator.validateContentLength`
      Se invoca `validateContentLength` sobre `CommentValidator`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (19)

- `pom.xml`
- `src/main/java/com/pragma/comments/CommentsApplication.java`
- `src/main/resources/application.yml`
- `src/main/java/com/pragma/comments/domain/model/Comment.java`
- `src/main/java/com/pragma/comments/domain/port/CommentRepository.java`
- `src/main/java/com/pragma/comments/domain/exception/InvalidCommentException.java`
- `src/main/java/com/pragma/comments/domain/exception/CommentNotFoundException.java`
- `src/main/java/com/pragma/comments/domain/exception/CommentProcessingException.java`
- `src/main/java/com/pragma/comments/infrastructure/dto/CommentRequest.java`
- `src/main/java/com/pragma/comments/infrastructure/dto/CommentResponse.java`
- `src/main/java/com/pragma/comments/application/usecase/CommentUseCase.java`
- `src/main/java/com/pragma/comments/application/validator/CommentValidator.java`
- `src/main/java/com/pragma/comments/infrastructure/adapter/CommentJpaAdapter.java`
- `src/main/java/com/pragma/comments/infrastructure/controller/CommentController.java`
- `src/main/java/com/pragma/comments/infrastructure/config/GlobalExceptionHandler.java`
- `src/main/java/com/pragma/comments/infrastructure/config/OpenApiConfig.java`
- `src/test/java/com/pragma/comments/application/usecase/CommentUseCaseTest.java`
- `src/test/java/com/pragma/comments/infrastructure/controller/CommentControllerTest.java`
- `src/test/java/com/pragma/comments/application/validator/CommentValidatorTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/pragma/comments`
- `src/main/java/com/pragma/comments/domain`
- `src/main/java/com/pragma/comments/domain/model`
- `src/main/java/com/pragma/comments/domain/port`
- `src/main/java/com/pragma/comments/domain/exception`
- `src/main/java/com/pragma/comments/application`
- `src/main/java/com/pragma/comments/application/usecase`
- `src/main/java/com/pragma/comments/application/validator`
- `src/main/java/com/pragma/comments/infrastructure`
- `src/main/java/com/pragma/comments/infrastructure/adapter`
- `src/main/java/com/pragma/comments/infrastructure/config`
- `src/main/java/com/pragma/comments/infrastructure/controller`
- `src/main/java/com/pragma/comments/infrastructure/dto`
- `src/main/resources`
- `src/test/java/com/pragma/comments`

## Verificacion

```bash
mvn clean compile
```

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Spring, Tecnología Java, Senior L2
- Brecha que el reto ataca: Comprueba manejo de errores y validaciones

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
