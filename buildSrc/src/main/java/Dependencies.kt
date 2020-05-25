object Apps {
    const val compileSdk = 29
    const val minSdk = 21
    const val targetSdk = 29
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Versions {
    const val gradle = "3.6.3"
    const val kotlin = "1.3.71"
    const val androidLifecycle = "2.2.0"
    const val navigation = "2.3.0-beta01"

    /* test */
    const val junit = "4.12"

    /* espresso */
    const val espresso = "3.2.0"
}

object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val appcompat = "androidx.appcompat:appcompat:1.1.0"
    const val coreKtx = "androidx.core:core-ktx:1.2.0"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.androidLifecycle}"
    const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidLifecycle}"

    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    const val firebaseAnalytics = "com.google.firebase:firebase-analytics:17.4.2"
}

object TestLibs {
    const val junit = "junit:junit:${Versions.junit}"
}

object EspressoLibs {
    const val junit = "androidx.test.ext:junit:1.1.1"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val navigation = "androidx.navigation:navigation-testing:${Versions.navigation}"
}
