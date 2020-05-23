buildscript {
    repositories {
        google()
    }

    dependencies {
        classpath("com.google.firebase:firebase-appdistribution-gradle:1.4.1")
    }
}

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")

    id("com.google.gms.google-services")
}

apply(plugin = "com.google.firebase.appdistribution")

android {
    compileSdkVersion(Apps.compileSdk)

    defaultConfig {
        applicationId = "net.lachlanmckee.linkcleaner"
        minSdkVersion(Apps.minSdk)
        targetSdkVersion(Apps.targetSdk)
        versionCode = Apps.versionCode
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

    implementation(Libs.firebaseAnalytics)

    testImplementation(TestLibs.junit)

    androidTestImplementation(EspressoLibs.junit)
    androidTestImplementation(EspressoLibs.espressoCore)
}
