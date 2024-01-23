plugins {
    `java-library`
//    id("java-conventions")
}

dependencies {
    implementation(project(":core"))

    implementation(libs.jcip.annotations)
    implementation(libs.javax.servlet.api)

}
