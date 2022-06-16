buildscript {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://jitpack.io")
    }

    dependencies {
        classpath(Android.Classpaths.gradle)
        classpath(Android.Classpaths.kotlin)
        classpath(Android.Classpaths.navigation)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    }
}

tasks.register("clean", Delete::class) {
    rootProject.buildDir
}