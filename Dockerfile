FROM eclipse-temurin:17.0.14_7-jdk

#puerto
EXPOSE 8080

#directorio raiz del contenedor
WORKDIR /root

#copiar y pegar archivos dentro del contenedor
COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root

#instalar dependencia
RUN ./mvnw dependency:go-offline

#copia del src
COPY ./src /root/src

#instalacion de las dependencias
RUN ./mvnw clean install -DskipTests


ENTRYPOINT ["java", "-jar", "/root/target/demo-0.0.1-SNAPSHOT.jar"]

