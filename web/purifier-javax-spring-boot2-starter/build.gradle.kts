plugins {
    `java-library`
//    id("java-conventions")
}

dependencies {
//    annotationProcessor("org.springframework.boot:spring-boot-autoconfigure-processor")
//    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    implementation(project(":core"))
    implementation(project(":web:web"))


}
