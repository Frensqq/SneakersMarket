plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.createinlager"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.createinlager"
        minSdk = 30
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
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

    implementation("androidx.core:core-splashscreen:1.0.0")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    implementation(libs.androidx.navigation.compose)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    implementation ("com.squareup.retrofit2:retrofit:2.9.0") // Подключаем саму библиотеку Retrofit
    implementation("io.coil-kt:coil-compose:2.7.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0") // Подключаем конвертер для работы с JSON
    implementation("androidx.navigation:navigation-compose")
    implementation(libs.play.services.maps)

    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation (libs.maps.mobile)
    implementation("ru.sulgik.mapkit:yandex-mapkit-kmp-compose:0.1.1")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("com.google.accompanist:accompanist-permissions:0.31.2-alpha")

    implementation("com.google.android.gms:play-services-location:21.3.0")

}