buildscript {
    repositories {
        google()
    }

    dependencies {
        classpath("com.google.firebase:firebase-appdistribution-gradle:1.4.1")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}")
    }
}

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")

    id("com.google.gms.google-services")
}

apply(plugin = "com.google.firebase.appdistribution")
apply(plugin = "androidx.navigation.safeargs.kotlin")

android {
    compileSdkVersion(Apps.compileSdk)

    defaultConfig {
        applicationId = "net.lachlanmckee.linkcleaner"
        minSdkVersion(Apps.minSdk)
        targetSdkVersion(Apps.targetSdk)
        versionCode = System.getenv("BITRISE_BUILD_NUMBER")?.toInt() ?: 1
        versionName = Apps.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

}

dependencies {
    implementation(Libs.kotlin)
    implementation(Libs.appcompat)
    implementation(Libs.coreKtx)
    implementation(Libs.constraintLayout)
    implementation(Libs.lifecycleExtensions)
    implementation(Libs.lifecycleViewModelKtx)

    implementation(Libs.navigationFragmentKtx)
    implementation(Libs.navigationUiKtx)

    implementation(Libs.firebaseAnalytics)

    testImplementation(TestLibs.junit)

    androidTestImplementation(EspressoLibs.junit)
    androidTestImplementation(EspressoLibs.espressoCore)
    androidTestImplementation(EspressoLibs.navigation)
}
