plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

val vertxVersion = "4.3.5"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.vertx:vertx-core:$vertxVersion")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}