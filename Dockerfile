# =================================
# build stage
# ---------------------------------
FROM amazoncorretto:21-alpine-jdk AS builder
LABEL authors="vivek"

WORKDIR /app
COPY . .

RUN chmod +x gradlew
RUN ./gradlew clean build --no-daemon

# =================================
# run stage
# ---------------------------------

FROM gradle:8.12.1-jdk21-alpine

WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]