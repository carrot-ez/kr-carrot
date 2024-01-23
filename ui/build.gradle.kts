plugins {
    id("core-conventions")
    id("swagger-conventions")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":stock"))
}