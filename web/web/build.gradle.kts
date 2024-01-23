plugins {
    `java-library`
//    id("java-conventions")
}

dependencies {
    implementation(project(":core"))

    implementation(libs.jcip.annotations)
    implementation(libs.jakarta.servlet.api)

//    implementation(libs.commons.lang3)
//    implementation(libs.guava)

//    implementation(libs.jackson.databind)
//    implementation(libs.commons.beanutils)
//    implementation(libs.jcip.annotations)
//    implementation(libs.jakarta.servlet.api)
}
