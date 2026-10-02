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