plugins {
    antlr
    `java-library`
//    id("java-conventions")
}

dependencies {
    antlr(libs.antlr4)

    implementation(libs.commons.lang3)
    implementation(libs.guava)

    implementation(libs.jackson.databind)
    implementation(libs.commons.beanutils)
    implementation(libs.jcip.annotations)
}

tasks.generateGrammarSource {
    arguments = arguments + listOf("-visitor", "-long-messages")
}
