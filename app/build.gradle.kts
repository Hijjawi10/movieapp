plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id ("kotlin-kapt")
}

android {
    namespace = "com.example.movie"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.movie"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation (libs.squareup.moshi)
    implementation (libs.moshi.kotlin)

    kapt ("com.squareup.moshi:moshi-kotlin-codegen:1.15.0")


    implementation (libs.androidx.lifecycle.viewmodel.compose)
    implementation (libs.coil.compose)

    implementation (libs.retrofit)
    implementation (libs.converter.moshi)
    implementation (libs.moshi.v1140)
    implementation (libs.moshi.kotlin.v1140)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.runtime.livedata)

    implementation (libs.hilt.android)
    kapt (libs.hilt.android.compiler)
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.gson)

    implementation (libs.firebase.auth.ktx)

    implementation (libs.firebase.firestore.ktx)

    implementation (libs.hilt.android.v245)
    kapt (libs.hilt.android.compiler.v245)
    kapt (libs.androidx.hilt.compiler)
    implementation (libs.androidx.hilt.navigation.compose)


    implementation (libs.androidx.room.runtime)
    kapt (libs.androidx.room.compiler)
    implementation (libs.androidx.room.ktx)
    implementation(libs.coil.compose.v222)

    implementation (libs.androidx.constraintlayout.compose)

}