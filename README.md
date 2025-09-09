# Pricing Service

A microservice for price management, built with Java and Spring Boot, following clean architecture and Domain-Driven Design (DDD) principles.

---

## About

Pricing Service is a backend microservice designed to manage and expose price-related operations via a RESTful API. It demonstrates best practices in Java backend development, including layered architecture, modularity, and testability.

---

## Features

- Exposes REST endpoints for price queries and management
- In-memory H2 database with auto-initialization scripts
- OpenAPI 3.0 specification for contract-first development
- Modular, testable, and extensible codebase
- Example of clean architecture and DDD in Java

---

## Technologies Used

- **Java 21**: Modern, high-performance JVM language.
- **Spring Boot 3.5.5**: Framework for rapid development of production-ready Java applications.
  - `spring-boot-starter-web`: For building RESTful web services.
  - `spring-boot-starter-data-jpa`: For data persistence using JPA.
- **Gradle**: Build automation tool (wrapper included).
- **H2 Database**: Lightweight, in-memory database for development and testing.
- **Lombok**: Reduces boilerplate code via annotations (e.g., getters/setters).
- **JBoss Logging**: Logging framework required by Lombok's `@JBossLog`.
- **MapStruct**: Code generator for mapping between Java bean types (DTOs, entities, domain models).
- **JUnit Platform**: Modern testing framework for unit and integration tests.

---

## Architecture

The project follows a layered, modular architecture inspired by Clean Architecture and Domain-Driven Design (DDD):

```
src/
└── main/
    └── java/
        └── com.bcncgroup.pricingservice/
            ├── Application.java                # Spring Boot entry point
            ├── prices/
            │   ├── application/                # Application services (business logic)
            │   │   └── port/in/                # Input ports (use cases)
            │   ├── domain/                     # Domain models (entities, value objects)
            │   └── infrastructure/
            │       ├── api/rest/               # REST controllers and API mappers
            │       └── persistence/jpa/        # JPA repositories, adapters, entity mappers
            └── shared/                         # Shared exceptions, error models, utilities
```

**Key architectural concepts:**
- **Domain Layer**: Contains core business logic and domain models, independent of frameworks.
- **Application Layer**: Implements use cases and orchestrates domain logic.
- **Infrastructure Layer**: Handles technical concerns (REST API, persistence, mapping).
- **Ports & Adapters**: Follows hexagonal architecture, separating business logic from external systems.
- **Exception Handling**: Centralized error handling for consistent API responses.

**Configuration:**
- Main config: `src/main/resources/application.yaml`
- Database schema/data: `src/main/resources/schema.sql`, `data.sql`
- OpenAPI spec: `docs/pricing-service-openapi-v1.0.0.yaml`

---

## Getting Started

### Prerequisites

- JDK 21 installed
- No need for a global Gradle installation (wrapper included)

### Build

Open PowerShell in the project root and run:
```
.\gradlew.bat clean build
```
The resulting JAR will be in `build\libs\`.

### Run

Option 1: Run via Gradle (development mode)
```
.\gradlew.bat bootRun
```
Option 2: Run the built JAR
```
java -jar build\libs\pricing-service-1.0.0.jar
```

### Configuration

- Main config: `src/main/resources/application.yaml`
- Change DB or server port via `application.yaml` or environment variables
- DB auto-initialized with `schema.sql` and `data.sql` (H2 in-memory by default)

---

## API Documentation

- OpenAPI 3.0 spec: `docs/pricing-service-openapi-v1.0.0.yaml`
- Use with Swagger UI, Postman, or code generators

---

## Testing

Run all tests:
```
.\gradlew.bat test
```
Test reports: `build/reports/tests/test/index.html`

---

## Project Structure

- `src/main/java` — Java source code (see Architecture)
- `src/main/resources` — configuration and SQL scripts
- `build.gradle`, `gradlew`, `gradlew.bat` — build and wrapper
- `docs/` — OpenAPI spec and additional docs

---

## Contributing

1. Create a branch (`feature/...`, `fix/...`)
2. Add/maintain tests for all changes
3. Open a Pull Request targeting `develop` with a clear description

---

## Troubleshooting

- **Java not found**: Install JDK 21 and check with `java -version`
- **Permissions**: Run PowerShell as Administrator or adjust execution policies
- **Dependency issues**: Delete `.gradle` and run `.\gradlew.bat --refresh-dependencies`

---

## Quick Start

This README is intended for local development and quick onboarding, especially on Windows PowerShell.

---

## Notes

- Build artifacts are in `build/libs/`
- Test results are in `build/reports/tests/`
- Update the OpenAPI spec when endpoints change

---
