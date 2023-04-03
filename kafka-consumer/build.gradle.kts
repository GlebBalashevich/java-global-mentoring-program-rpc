plugins {
    java
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
}

repositories {
    maven {
        url = uri("https://packages.confluent.io/maven/")
    }
}

dependencies {
    implementation(project(":avro-api"))
    implementation("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    implementation("org.springframework.cloud:spring-cloud-stream-binder-kafka:4.0.1")
    implementation("org.springframework.cloud:spring-cloud-stream-schema:2.2.1.RELEASE")

    implementation("io.confluent:kafka-avro-serializer:7.3.2")

}
