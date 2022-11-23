plugins {
    id("java")
}

group = "ru.vk"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val vertx = "4.3.5"
dependencies {
    implementation("io.vertx:vertx-core:$vertx")
    implementation("io.vertx:vertx-web:$vertx")
    implementation("io.vertx:vertx-web-client:$vertx")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}