import org.apache.tools.ant.filters.ReplaceTokens

plugins {
    kotlin("jvm") version "1.9.22"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "jp.zaemongames"
version = "1.0"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
}

tasks {
    shadowJar {
        archiveBaseName.set("deathTP")
        archiveVersion.set(project.version.toString())
        archiveClassifier.set("")

        mergeServiceFiles()

        manifest {
            attributes(mapOf("Main-Class" to "jp.zaemongames.deathtp.deathTPKt"))
        }
    }

    processResources {
        filteringCharset = "UTF-8"
        from(sourceSets["main"].resources.srcDirs) {
            include("**/*.yml")
            duplicatesStrategy = DuplicatesStrategy.INCLUDE
            filter<ReplaceTokens>("tokens" to mapOf("version" to project.version))
            filter<ReplaceTokens>("tokens" to mapOf("name" to "deathTP"))
        }
    }

    build {
        dependsOn(shadowJar)
    }
}