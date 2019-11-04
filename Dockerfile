FROM java:8-alpine

WORKDIR /
RUN apk update \
    && apk add curl

ADD target/cli-0.0.1.jar /cli.jar

VOLUME /config


HEALTHCHECK --interval=10s --timeout=5s --retries=3 \
  CMD curl -f http://localhost:9999/ || exit 1

EXPOSE 9999
CMD java -jar cli.jar --spring.config.location=/config/application.yml