package com.bcncgroup.pricingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Main class and entry point for the Pricing Service application.
 * <p>
 * The {@link SpringBootApplication @SpringBootApplication} annotation enables
 * key
 * Spring Boot features, such as auto-configuration, component scanning, and
 * Java-based configuration. This class starts the embedded application server
 * (e.g., Tomcat) and deploys the application.
 * </p>
 */
@SpringBootApplication
@EnableCaching
public class Application {

    /**
     * Entry point for the Spring Boot application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
