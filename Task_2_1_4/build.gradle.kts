import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    application
}

group = "ru.nsu.voronova"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.gradle.org/gradle/libs-releases")
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("scripting-jsr223"))
    implementation("org.jetbrains.kotlin:kotlin-script-runtime:1.5.21")
    implementation("commons-cli:commons-cli:1.5.0")
    implementation("org.eclipse.jgit:org.eclipse.jgit:6.1.0.202203080745-r")
    implementation("org.slf4j:slf4j-api:1.7.36")
    implementation("org.slf4j:slf4j-simple:1.7.36")
    implementation("org.gradle:gradle-tooling-api:7.3-20210825160000+0000")
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.5")
    implementation("org.jetbrains.kotlinx:kotlinx-html:0.7.5")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

application {
    mainClass.set("ru.nsu.voronova.CourseReportBuilder")
}