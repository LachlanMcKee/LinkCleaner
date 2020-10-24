object Dependencies {
    object Analytics {
        const val firebaseAnalytics = "com.google.firebase:firebase-analytics:17.4.2"
    }
    object AndroidX {
        private const val lifecycleVersion = "2.2.0"
        const val navigationVersion = "2.3.0-beta01"
        const val appcompat = "androidx.appcompat:appcompat:1.1.0"
        const val coreKtx = "androidx.core:core-ktx:1.2.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:$lifecycleVersion"
        const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
        const val lifecycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
        const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
        const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:$navigationVersion"
    }
    object Di {
        private const val version = "2.27"
        const val dagger = "com.google.dagger:dagger:$version"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:$version"
        const val daggerAndroid = "com.google.dagger:dagger-android-support:$version"
        const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:$version"
    }
    object Kotlin {
        const val version = "1.4.10"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9"
    }
    object Logging {
        const val timber = "com.jakewharton.timber:timber:4.7.1"
    }
    object Network {
        const val okHttp = "com.squareup.okhttp3:okhttp:4.7.2"
    }
}
