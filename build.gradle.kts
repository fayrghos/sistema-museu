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
    }
}

application {
    mainClass = "Main"
}

dependencies {
    implementation("org.mindrot:jbcrypt:0.4")
    implementation("commons-validator:commons-validator:1.9.0")
}
