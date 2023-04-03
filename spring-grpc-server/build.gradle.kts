plugins {
    java
    id("org.springframework.boot") version "2.6.13"
    id("io.spring.dependency-management") version "1.1.0"
}

dependencies {
    implementation(project(":grpc-api"))
    implementation("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    implementation("net.devh:grpc-spring-boot-starter:2.14.0.RELEASE")
}
