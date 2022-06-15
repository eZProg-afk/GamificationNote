buildscript {

    repositories {
        google()
        mavenCentral()
        maven { setUrl(Android.Repositories.kotlinx) }
        maven { setUrl(Android.Repositories.jitpack) }
    }

    dependencies {
        classpath(Android.Classpaths.gradle)
        classpath(Android.Classpaths.kotlin)
        classpath(Android.Classpaths.navigation)
    }
}

tasks.register("clean", Delete::class) {
    rootProject.buildDir
}