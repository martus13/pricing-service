# Interpretación de Requisitos y Priorización (MoSCoW)

## 1. Diagrama de Flujo de Datos (DFD) / Blueprint

```mermaid
flowchart TD
    A[Cliente REST] -->|GET /prices?date=...&productId=...&brandId=...| B[Controlador REST (PriceController)]
    B --> C[Aplicación (PriceService)]
    C --> D[Repositorio (PriceRepositoryAdapter)]
    D --> E[Base de datos H2 (Tabla PRICES)]
    E --> D
    D --> C
    C --> B
    B -->|Respuesta: id producto, id cadena, tarifa, fechas, precio| A
```

**Descripción del flujo:**
1. El cliente realiza una petición GET al endpoint `/prices` con los parámetros requeridos.
2. El controlador REST recibe la petición y delega la lógica al servicio de aplicación.
3. El servicio consulta el repositorio, que accede a la base de datos H2.
4. Se selecciona el precio aplicable según las reglas de prioridad y fechas.
5. Se devuelve la respuesta con los datos solicitados.

---

## 2. Priorización de Requisitos (MoSCoW)

| Requisito                                                                 | Prioridad  | Justificación                                                                                 |
|---------------------------------------------------------------------------|------------|----------------------------------------------------------------------------------------------|
| Endpoint REST GET que acepte fecha, id de producto e id de cadena         | Must-have  | Es el núcleo funcional del servicio.                                                         |
| Devolver: id producto, id cadena, tarifa, fechas de aplicación, precio    | Must-have  | Especificado como salida obligatoria.                                                        |
| Base de datos en memoria H2, inicializada con los datos de ejemplo        | Must-have  | Permite pruebas y funcionamiento autónomo.                                                   |
| Selección de tarifa por prioridad y rango de fechas                       | Must-have  | Lógica de negocio principal.                                                                 |
| Pruebas de integración para los 5 casos del enunciado                     | Must-have  | Validación funcional y de negocio.                                                           |
| Arquitectura hexagonal                                                    | Should-have| Mejora mantenibilidad y separación de capas.                                                 |
| Claridad y calidad de código (SOLID, buenas prácticas)                    | Should-have| Facilita mantenimiento y escalabilidad.                                                      |
| Documentación (README, requisitos, diagrama)                              | Should-have| Mejora la comprensión y validación del desarrollo.                                           |
| Control de versiones (Git)                                                | Should-have| Buenas prácticas de desarrollo colaborativo.                                                 |
| Eficiencia en la extracción de datos                                      | Could-have | Optimización, pero no bloqueante para la funcionalidad básica.                               |
| Configuración externa (application.yaml, Dockerfile)                      | Could-have | Facilita despliegue y portabilidad.                                                          |
| Devolver único resultado (no lista)                                       | Must-have  | Especificado en el enunciado.                                                                |
| Validación formal de requisitos con el equipo/cliente                     | Must-have  | Evita malentendidos y asegura alineación de expectativas.                                    |
| Ampliación a otros productos/cadenas/monedas                              | Won't-have | Fuera del alcance del enunciado actual.                                                      |

---

## 3. Validación Formal de Requisitos

**Recomendación:**  
Antes de continuar con la implementación, se debe revisar este documento con el equipo o cliente para confirmar que la interpretación y priorización de los requisitos es correcta y completa. Esto evitará malentendidos y permitirá alinear expectativas.
