FROM openjdk:8-jdk-alpine
#VOLUME /tmp

ADD ./build/resources/main/certs/idptruststore.jks oauth_client.jks
ADD ./build/libs/oidc-bootclient-1.0.jar  app.jar

ENV JAVA_OPTS="-Xss2048k -Djavax.net.ssl.trustStore=/oauth_client.jks -Djavax.net.ssl.trustStoreType=jks -Djavax.net.ssl.trustStorePassword=truststorepass"
#ENV JAVA_OPTS="-Xss2048k"
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar
