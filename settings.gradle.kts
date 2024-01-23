rootProject.name = "purifier"

include("core")
include(
    "web:web",
    "web:web-javax",
    "purifier-javax-spring-boot2-starter"
)


dependencyResolutionManagement {


    repositories {
        maven("https://mirrors.huaweicloud.com/repository/maven/")
        mavenLocal()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            version("spring-boot", "2.7.18")
            library("commons-lang3", "org.apache.commons:commons-lang3:3.13.0")
            library("antlr4", "org.antlr:antlr4:4.13.1")
            library("guava", "com.google.guava:guava:33.0.0-jre")
            library("jackson-databind", "com.fasterxml.jackson.core:jackson-databind:2.15.3")

            library("commons-beanutils", "commons-beanutils:commons-beanutils:1.9.4")
            library("jcip-annotations", "net.jcip:jcip-annotations:1.0")
            library("jakarta.servlet-api", "jakarta.servlet:jakarta.servlet-api:6.0.0")
            library("javax.servlet-api", "javax.servlet:javax.servlet-api:4.0.1")

            library(
                "spring-boot-autoconfigure-processor",
                "org.springframework.boot",
                "spring-boot-autoconfigure-processor"
            ).versionRef("spring-boot")
        }
    }
}
