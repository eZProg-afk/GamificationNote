plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs")
}

android {

    defaultConfig {
        applicationId = Android.DefaultConfig.applicationId
        minSdk = Android.DefaultConfig.minSdk
        compileSdk = Android.DefaultConfig.compileSdk
        targetSdk = Android.DefaultConfig.targetSdk
        versionCode = Android.DefaultConfig.versionCode
        versionName = Android.DefaultConfig.versionName
        testInstrumentationRunner = Android.DefaultConfig.instrumentationRunner
    }

    buildTypes {
        getByName(Android.BuildTypes.release) {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile(Android.Proguard.androidOptimizedRules),
                Android.Proguard.projectRules
            )
        }
        getByName(Android.BuildTypes.debug) {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile(Android.Proguard.androidOptimizedRules),
                Android.Proguard.projectRules
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = Android.KotlinOptions.jvmTargetVersion
    }

    kapt {
        correctErrorTypes = true
    }

    viewBinding.isEnabled = true
}

dependencies {
    addCommonAndroid()
    addLifecycle()
    addDI()
    addNavigation()
    addCoroutines()
    addRoom()
    addTestRunner()
    addUnitTests()
    addIntegrationTests()
    addUITests()
}