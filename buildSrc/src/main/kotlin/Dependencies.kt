import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {

    private object Versions {
        const val room = "2.4.2"
        const val coroutines = "1.5.0"
        const val navigationFragmentKtx = "2.3.0-rc01"
        const val navigationUiKtx = "2.3.0-rc01"
        const val lifecycleKtx = "2.2.0"
        const val coreKtx = "1.8.0"
        const val appCompat = "1.4.2"
        const val material = "1.6.1"
        const val viewBindingPropertyDelegate = "1.4.2"
        const val smoothBottomBar = "1.7.9"
        const val roundedImageView = "2.3.0"
        const val adapterDelegates = "4.3.1"
        const val constraintlayout = "2.1.4"
        const val kodein = "7.12.0"
    }

    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigationFragmentKtx}"
    const val navigationUiKtx =
        "androidx.navigation:navigation-ui-ktx:${Versions.navigationUiKtx}"

    const val lifecycleViewModelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleKtx}"
    const val lifecycleRuntimeKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleKtx}"
    const val lifecycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleKtx}"

    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val adapterDelegatesDsl = "com.hannesdorfmann:adapterdelegates4-kotlin-dsl:${Versions.adapterDelegates}"
    const val adapterDelegatesViewBinding = "com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding:${Versions.adapterDelegates}"
    const val viewBindingDelegate = "com.kirich1409.viewbindingpropertydelegate:viewbindingpropertydelegate:${Versions.viewBindingPropertyDelegate}"
    const val smoothBottomBar = "com.github.ibrahimsn98:SmoothBottomBar:${Versions.smoothBottomBar}"
    const val roundedImageView = "com.makeramen:roundedimageview:${Versions.roundedImageView}"

    const val kodeinDI = "org.kodein.di:kodein-di:${Versions.kodein}"
    const val kodeinAndroidX = "org.kodein.di:kodein-di-framework-android-x:${Versions.kodein}"
    const val kodeinViewModel = "org.kodein.di:kodein-di-framework-android-x-viewmodel:${Versions.kodein}"
}

fun DependencyHandler.addCommonAndroid() {
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.constraintlayout)
    implementation(Dependencies.adapterDelegatesDsl)
    implementation(Dependencies.adapterDelegatesViewBinding)
    implementation(Dependencies.viewBindingDelegate)
    implementation(Dependencies.smoothBottomBar)
    implementation(Dependencies.roundedImageView)
}

fun DependencyHandler.addDI() {
    implementation(Dependencies.kodeinDI)
    implementation(Dependencies.kodeinAndroidX)
    implementation(Dependencies.kodeinViewModel)
}

fun DependencyHandler.addLifecycle() {
    implementation(Dependencies.lifecycleViewModelKtx)
    implementation(Dependencies.lifecycleRuntimeKtx)
    implementation(Dependencies.lifecycleExtensions)
}

fun DependencyHandler.addNavigation() {
    implementation(Dependencies.navigationUiKtx)
    implementation(Dependencies.navigationFragmentKtx)
}

fun DependencyHandler.addCoroutines() {
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.coroutinesAndroid)
}

fun DependencyHandler.addRoom() {
    implementation(Dependencies.room)
    implementation(Dependencies.roomKtx)
    kapt(Dependencies.roomCompiler)
}