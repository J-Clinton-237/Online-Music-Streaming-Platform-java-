package com.musicstreaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Main Spring Boot application class for the Music Streaming Platform.
 *
 * This is the entry point of the application that bootstraps the Spring Boot framework.
 * The application provides RESTful APIs for music streaming functionality including:
 * - User authentication and management
 * - Music library management (songs, artists, albums)
 * - Playlist creation and management
 * - Music recommendations
 * - File upload for music and images
 *
 * @SpringBootApplication enables auto-configuration, component scanning, and configuration properties
 * @EntityScan specifies packages to scan for JPA entities
 */
@SpringBootApplication
@EntityScan(basePackages = {"com.musicstreaming", "MODEL"})
public class MusicStreamingApplication {

    /**
     * Main method that starts the Spring Boot application.
     *
     * @param args command line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(MusicStreamingApplication.class, args);
    }

    /**
     * Configuration class for CORS (Cross-Origin Resource Sharing) and static resource handling.
     *
     * CORS configuration allows the frontend (served from different origins) to make requests
     * to the backend API endpoints. Resource handlers configure how uploaded files are served.
     */
    @Configuration
    public class CorsConfig implements WebMvcConfigurer {

        /**
         * Configures CORS mappings for API endpoints.
         * Allows all origins, methods, and headers for development purposes.
         * In production, origins should be restricted to specific domains.
         *
         * @param registry the CORS registry to configure
         */
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/api/**")
                    .allowedOrigins("*")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .allowCredentials(false)
                    .maxAge(3600);
        }

        /**
         * Configures resource handlers for serving uploaded files.
         * Maps /uploads/** URL pattern to the local uploads directory.
         * This allows accessing uploaded music files and images via HTTP.
         *
         * @param registry the resource handler registry to configure
         */
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            // Serve uploaded music files
            registry.addResourceHandler("/uploads/**")
                    .addResourceLocations("file:uploads/");
        }
    }
}
