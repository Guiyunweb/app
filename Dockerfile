FROM eclipse-temurin:17-jre
RUN mkdir /opt/app
COPY start/build/libs/start-0.0.1-SNAPSHOT.jar /opt/app/japp.jar
CMD ["java","-jar","-Dfile.encoding=UTF-8","-XX:+UseG1GC","-Duser.timezone=GMT+8", "-Xmx256m", "-Xms256m", "-Djava.security.egd=file:/dev/./urandom", "/opt/app/japp.jar"]