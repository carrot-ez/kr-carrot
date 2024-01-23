plugins {
    id("java")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
}