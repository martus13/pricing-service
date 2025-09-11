package com.bcncgroup.pricingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
public class Application {

    /**
     * The main method used to launch the Spring Boot application.
     *
     * @param args Command-line arguments that can be passed at application startup.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
