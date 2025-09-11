# Pricing Service

Example microservice for price management (pricing-service).

## Description

This is a Java Spring Boot microservice exposing price-related operations. It includes persistence, data initialization scripts and an OpenAPI specification located at `docs/pricing-service-openapi-v1.0.0.yaml`.

## Requirements

See the [detailed requirements documentation](docs/requirements.md) for prioritization, justification, and the system flow diagram.

**Key requirements summary:**
- REST endpoint to query prices by date, product, and brand.
- Price selection by priority and date range.
- In-memory H2 database with sample data.
- Integration tests for the 5 cases in the statement.

---

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

See the [test strategy](docs/test-strategy.md) for details on test levels, tools, coverage, and CI/CD.

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

---

## Static analysis and linters (Docker)

This project includes a `Dockerfile` prepared to run static code analysis with the following tools on Java 21:

- PMD
- Checkstyle
- SpotBugs
- Spotless

### Requirements

- Docker installed
- Source code at the root of the project (as in this repository)

### Build the image

```sh
docker build -t java-linters .
```

### Launch the container

```sh
docker run --rm -it java-linters bash
```

### PMD

Analyze the source code with PMD:

```sh
/opt/pmd/bin/run.sh pmd -d src/main/java -R rulesets/java/quickstart.xml -f text
```
> Note: The script `/opt/pmd/bin/pmd.bat` is for Windows only. In the Linux container, always use `/opt/pmd/bin/run.sh`.

### Checkstyle

Check code style with Checkstyle:

```sh
java -jar /opt/checkstyle.jar -c /app/google_checks.xml src/main/java
```

### SpotBugs

Now SpotBugs runs directly with Gradle, no Docker needed.

First, build the project (optional, SpotBugs will compile automatically if needed):

```sh
./gradlew spotbugsMain
```

The HTML report will be available at `build/reports/spotbugs/main.html`.

To analyze the tests:
```sh
./gradlew spotbugsTest
```
The report will be at `build/reports/spotbugs/test.html`.

### Spotless

Automatically format the code (if configured in `build.gradle`):

```sh
./gradlew spotlessApply
```

With these commands you can run the main linters on the project's source code using the provided Docker container.
