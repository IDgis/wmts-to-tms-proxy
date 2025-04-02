FROM azul/zulu-openjdk:8

EXPOSE 8080

# Create java user
RUN useradd -M -u 8080 -s /bin/false java

# COPY jar and config to the container
COPY build/libs/wmts-to-tms-proxy-?.?.?.jar /app/wmts-to-tms-proxy.jar
COPY config /app/config

RUN chown -R java:java /app

USER java

WORKDIR /app

SHELL ["/bin/bash", "-c"]
ENTRYPOINT exec java $JAVA_OPTS -jar \
    ./wmts-to-tms-proxy.jar
