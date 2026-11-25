plugins {
    application
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

sourceSets {
    getByName("main") {
        java.setSrcDirs(listOf("src"))
        resources.setSrcDirs(listOf("resources"))
    }
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
    systemProperty("stdout.encoding", "UTF-8")
}

application {
    mainClass = "Main"
}

dependencies {
    implementation("org.mindrot:jbcrypt:0.4")
    implementation("commons-validator:commons-validator:1.9.0")
    implementation("io.github.cdimascio:dotenv-java:3.0.0")
    implementation("org.postgresql:postgresql:42.7.4")
}
