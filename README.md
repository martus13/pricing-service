# Pricing Service

Example microservice for price management (pricing-service).

## Description

This is a Java Spring Boot microservice exposing price-related operations. It includes persistence, data initialization scripts and an OpenAPI specification located at `docs/pricing-service-openapi-v1.0.0.yaml`.

## Technologies

- Java + Spring Boot
- Gradle (wrapper included)
- SQL initialization scripts: `src/main/resources/schema.sql` and `src/main/resources/data.sql`

## Assumptions

- The project uses the Gradle wrapper (`gradlew` / `gradlew.bat`).
- You are working on Windows using PowerShell — commands below are tailored for that shell.
- The main application class is `com.bcncgroup.pricingservice.Application` (inferred from the compiled classes).

## API contract (brief)

- Input: REST HTTP requests with JSON bodies according to the OpenAPI spec in `docs/`.
- Output: JSON responses containing price resources and appropriate HTTP status codes.
- Errors: 4xx/5xx responses with JSON error details.

## Development prerequisites

- JDK installed (Java 21 recommended).
- No global Gradle installation required — use the included wrapper.

## Build (PowerShell)

Open PowerShell in the project root and run:

```
.\gradlew.bat clean build
```

The resulting jar will be available under `build\libs\`.

## Run (PowerShell)

Option A — run via Gradle (starts the app in the Gradle process):

```
.\gradlew.bat bootRun
```

Option B — run the built artifact:

```
java -jar build\libs\pricing-service-1.0.0.jar
```

## Configuration

- Main configuration file: `src/main/resources/application.yaml`.
- To change the database or the server port, edit `application.yaml` or provide environment variables.
- The `schema.sql` and `data.sql` files in `src/main/resources/` are used to initialize the database where supported.

## Tests

Run the test suite with:

```
.\gradlew.bat test
```

Test reports are available at `build/reports/tests/test/index.html`.

## API / Documentation

- The OpenAPI spec is located at `docs/pricing-service-openapi-v1.0.0.yaml`.
- Use it to generate clients or test endpoints with tools like Postman or Swagger UI.

## Project structure (summary)

- `src/main/java` — Java source code
- `src/main/resources` — configuration and SQL scripts
- `build.gradle`, `gradlew`, `gradlew.bat` — build and wrapper
- `docs/` — OpenAPI spec and additional docs

## Local best practices

- Run `clean build` before opening a PR.
- Keep tests green on the `develop` branch.
- Update `docs/pricing-service-openapi-v1.0.0.yaml` when endpoints change.

## Contributing

1. Create a branch with a clear name (`feature/...`, `fix/...`).
2. Add tests for functional changes.
3. Open a Pull Request targeting `develop` and describe the purpose and verification steps.

## Troubleshooting

- If Java is missing: install JDK 21 and verify with `java -version`.
- Permission issues: run PowerShell as Administrator or adjust execution policies.
- Dependency problems: delete `.gradle` and run `.\gradlew.bat --refresh-dependencies`.

## License & contact

- Add the project license here (e.g. MIT) or list the contact person/team.

## Quick verification

- This README is intended to help local development and quick start on Windows PowerShell.

## Notes

- The repository already contains build artifacts in `build/libs/` and test results in `test-results/`.

## Next steps I can do for you

- Add endpoint examples (HTTP requests) extracted from the OpenAPI spec.
- Create a Dockerfile and docker instructions.
- Add a CI workflow (GitHub Actions) for build and tests.

If you want any of the above, tell me which one and I'll implement it.
