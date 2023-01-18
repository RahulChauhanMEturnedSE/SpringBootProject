FROM openjdk:8-jre
VOLUME /tmp
ADD target/count-application.jar count-application.jar
EXPOSE 5000
WORKDIR ./count-application/
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-Xms512m", "-Xmx1g", "-jar", "count-application.jar"]