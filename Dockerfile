# Dockerfile para ejecutar PMD, Checkstyle, SpotBugs y Spotless en un proyecto Java

FROM openjdk:21-jdk-bullseye

# Instala certificados raíz, wget, unzip y xargs (findutils)
RUN apt-get update && apt-get install -y ca-certificates wget unzip findutils && rm -rf /var/lib/apt/lists/*

# Variables de versión
ENV PMD_VERSION=6.55.0
ENV CHECKSTYLE_VERSION=10.16.0
ENV SPOTBUGS_VERSION=4.8.3

# Descarga PMD (URL para 6.x)
RUN wget -O /tmp/pmd.zip https://github.com/pmd/pmd/releases/download/pmd_releases%2F${PMD_VERSION}/pmd-bin-${PMD_VERSION}.zip \
    && unzip /tmp/pmd.zip -d /opt \
    && mv /opt/pmd-bin-${PMD_VERSION} /opt/pmd \
    && rm /tmp/pmd.zip

# Descarga Checkstyle
RUN wget -O /opt/checkstyle.jar https://github.com/checkstyle/checkstyle/releases/download/checkstyle-${CHECKSTYLE_VERSION}/checkstyle-${CHECKSTYLE_VERSION}-all.jar

# Da permisos de ejecución a los scripts de PMD
RUN chmod +x /opt/pmd/bin/pmd*

# Añade binarios al PATH
ENV PATH="/opt/pmd/bin:${PATH}"

# Directorio de trabajo
WORKDIR /app

# Copia el código fuente al contenedor (puedes cambiar esto por un volumen en producción)
COPY . /app

# Copia el archivo de configuración de Checkstyle correspondiente a la versión instalada
RUN wget -O /app/google_checks.xml https://raw.githubusercontent.com/checkstyle/checkstyle/checkstyle-10.16.0/src/main/resources/google_checks.xml

# Comando por defecto: muestra ayuda de las herramientas (sintaxis JSON recomendada)
CMD ["/bin/sh", "-c", "echo 'PMD: pmd.sh check -d src/main/java -R rulesets/java/quickstart.xml -f text' && echo 'Checkstyle: java -jar /opt/checkstyle.jar -c /app/google_checks.xml src/main/java' && echo 'Spotless: ./gradlew spotlessApply'"]
