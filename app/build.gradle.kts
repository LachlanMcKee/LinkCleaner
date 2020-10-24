buildscript {
    repositories {
        google()
        jcenter()
    }
}

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.gms.google-services")
    id("com.google.firebase.appdistribution")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(29)

    defaultConfig {
        applicationId = "net.lachlanmckee.linkcleaner"
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = System.getenv("BITRISE_BUILD_NUMBER")?.toIntOrNull() ?: 1
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        testOptions {
            execution = "ANDROIDX_TEST_ORCHESTRATOR"
        }

        testInstrumentationRunnerArguments["clearPackageData"] = "true"
    }

    signingConfigs {
        getByName("debug") {
            storeFile = file("src/debug/debug-keystore.jks")
            storePassword = "key-store-password"
            keyAlias = "debug-alias"
            keyPassword = "key-password"
        }
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Dependencies.Kotlin.stdlib)

    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.AndroidX.lifecycleExtensions)
    implementation(Dependencies.AndroidX.lifecycleViewModelKtx)
    implementation(Dependencies.AndroidX.lifecycleLiveDataKtx)
    implementation(Dependencies.AndroidX.navigationFragmentKtx)
    implementation(Dependencies.AndroidX.navigationUiKtx)

    implementation(Dependencies.Network.okHttp)
    implementation(Dependencies.Analytics.firebaseAnalytics)

    kapt(Dependencies.Di.daggerCompiler)
    kapt(Dependencies.Di.daggerAndroidProcessor)
    implementation(Dependencies.Di.dagger)
    implementation(Dependencies.Di.daggerAndroid)

    implementation(Dependencies.Kotlin.coroutinesCore)

    implementation(Dependencies.Logging.timber)

    testImplementation(UnitTestDependencies.junit)

    debugImplementation(EspressoTestDependencies.fragmentTesting)
    androidTestImplementation(EspressoTestDependencies.junit)
    androidTestImplementation(EspressoTestDependencies.espressoCore)
    androidTestImplementation(EspressoTestDependencies.espressoIntents)
    androidTestImplementation(EspressoTestDependencies.runner)
    androidTestImplementation(EspressoTestDependencies.rules)
    androidTestImplementation(EspressoTestDependencies.navigation)
    androidTestUtil(EspressoTestDependencies.orchestrator)
}
