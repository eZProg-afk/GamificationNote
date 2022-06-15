pluginManagement {
    repositories {
        maven { setUrl("https://kotlin.bintray.com/kotlinx") }
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
}

rootProject.name = "GamificationNote"
include(":app")
