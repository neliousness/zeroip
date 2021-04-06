FROM openjdk:8-alpine
LABEL maintainer=Nelio
WORKDIR /app
COPY libs libs/
COPY resources resources/
COPY classes classes/
ENTRYPOINT ["java", "-cp", "/app/resources:/app/classes:/app/libs/*", "com.neliolucas.zeroip.ZeroipApplication"]
EXPOSE 9000
