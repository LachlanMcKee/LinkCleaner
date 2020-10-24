import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.10")

        classpath("com.google.gms:google-services:4.3.4")
        classpath("com.google.firebase:firebase-appdistribution-gradle:2.0.1")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Dependencies.AndroidX.navigationVersion}")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

subprojects {
    repositories {
        jcenter()
    }

    pluginManager.withPlugin("java") {
        configure<JavaPluginExtension> {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}
