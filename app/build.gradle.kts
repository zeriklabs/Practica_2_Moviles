plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.zeriklabs.dm_jamt_08"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.zeriklabs.dm_jamt_08"
        minSdk = 26
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.activity.ktx)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ext.junit)
    // cliente retrofit - permite consumir una API
    implementation(libs.retrofit)
    // Gson - Mapea clase y Json
    implementation(libs.converter.gson)
}