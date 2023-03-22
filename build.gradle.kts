plugins {
    // Apply the Java Gradle plugin development plugin to add support for developing Gradle plugins
    `java-gradle-plugin`
    `maven-publish`

    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.8.20-RC"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
    maven("https://maven.fabricmc.net")
}

java {
	group = "xyz.spruceloader"
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Use the Kotlin test library.
//    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
//    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    implementation("net.fabricmc:mapping-io:0.3.0")
}

gradlePlugin {
    // Define the plugin
    val roots by plugins.creating {
        id = "xyz.spruceloader.roots"
        implementationClass = "xyz.spruceloader.roots.RootsPlugin"
    }
}

// Add a source set for the functional test suite
//val functionalTestSourceSet = sourceSets.create("functionalTest") {
//}
//
//gradlePlugin.testSourceSets(functionalTestSourceSet)
//configurations["functionalTestImplementation"].extendsFrom(configurations["testImplementation"])
//
//// Add a task to run the functional tests
//val functionalTest by tasks.registering(Test::class) {
//    testClassesDirs = functionalTestSourceSet.output.classesDirs
//    classpath = functionalTestSourceSet.runtimeClasspath
//}
//
//tasks.check {
//    // Run the functional tests as part of `check`
//    dependsOn(functionalTest)
//}
