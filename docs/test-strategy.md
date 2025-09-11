# Test Strategy – Pricing Service

## 1. Objectives

- Ensure the quality, robustness, and maintainability of the service.
- Detect defects early and efficiently.
- Provide fast and reliable feedback on every change.
- Facilitate safe extensibility and evolution of the system.

## 2. Test Levels

- **Unit Tests:**  
  Validate the behavior of individual components (services, controllers, mappers, etc.).  
  Tools: JUnit 5, Mockito.

- **Integration Tests:**  
  Verify the interaction between components and integration with external dependencies (repositories, database, etc.).  
  Tools: JUnit 5, Spring Boot Test, H2 (in-memory DB).

- **API Tests (optional):**  
  Validate the exposed REST endpoints.  
  Tools: MockMvc, RestAssured.

## 3. Tools

- **JUnit 5:** Main testing framework.
- **Mockito:** Dependency mocking.
- **Jacoco:** Code coverage measurement.
- **SonarQube:** Static analysis for quality and security.
- **CI/CD (GitHub Actions, Jenkins, GitLab CI, etc.):** Automated test execution and analysis.

## 4. Desired Coverage

- **Recommended minimum coverage:**  
  - Unit: ≥ 80% of lines and critical branches.
  - Integration: Coverage of main business flows.
- **Code coverage periodically reviewed in SonarQube.**

## 5. Test Prioritization (RBT – Risk Based Testing)

- Identify critical functionalities (e.g., pricing logic, validations, persistence).
- Prioritize tests on:
  - Core business logic.
  - Validations and error handling.
  - Integrations with external systems.
- Lower priority for accessory or low-impact code.

## 6. Automation and CI/CD

- All unit and integration tests must run automatically on every push/pull request.
- The CI/CD pipeline must:
  - Run tests and static analysis.
  - Block merges if tests fail or coverage drops.
  - Generate coverage and quality reports.

## 7. Static Analysis and Peer Review

- **SonarQube** integrated in CI to detect code smells, bugs, and vulnerabilities.
- **Peer review** required for every pull request:
  - Validate code and test quality.
  - Review coverage and edge cases.

## 8. Extensibility

- New features must include their own tests.
- Refactorings must maintain or improve coverage.
- Document relevant test cases in each PR.

---

**Visual summary:**

```mermaid
flowchart TD
    A[Push/PR] --> B[CI/CD: Run tests]
    B --> C{Tests OK?}
    C -- No --> D[Fix errors]
    C -- Yes --> E[Static analysis SonarQube]
    E --> F{Quality OK?}
    F -- No --> D
    F -- Yes --> G[Peer review]
    G --> H[Merge allowed]
