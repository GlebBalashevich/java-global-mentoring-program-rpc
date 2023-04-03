import com.google.protobuf.gradle.id

plugins {
    java
    id("com.google.protobuf") version "0.9.2"
}

val grpcVersion = "1.51.0"

dependencies{
    implementation("com.google.protobuf:protobuf-java:3.22.2")
    implementation("io.grpc:grpc-protobuf:${grpcVersion}")
    implementation("io.grpc:grpc-stub:${grpcVersion}")
    implementation("javax.annotation:javax.annotation-api:1.3.1")
}

protobuf{
    protoc {
        artifact = "com.google.protobuf:protoc:3.22.2"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.51.0"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                id("grpc") { }
            }
        }
    }
}
