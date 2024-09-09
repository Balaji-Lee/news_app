plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.mykotlinapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mykotlinapp"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        compose = true
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
    composeOptions {
        kotlinCompilerExtensionVersion ="1.5.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.material3.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.5")

    // RxJava2 dependencies
    implementation ( "io.reactivex.rxjava3:rxjava:3.1.6")
    implementation ("io.reactivex.rxjava3:rxandroid:3.0.2")

    // Retrofit RxJava2 adapter
    implementation ("com.squareup.retrofit2:adapter-rxjava3:2.9.0")

    //Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.0")

    // For RetroFit  API Calls
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //Jetpack Compose
    implementation ("androidx.activity:activity-compose:1.7.2")
    implementation ("androidx.compose.ui:ui:1.5.2")
    implementation ("androidx.compose.material:material:1.5.2")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.5.2")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.5.2")

    // For Material 3 components
    implementation ("androidx.activity:activity-compose:1.7.2")
    implementation ("androidx.compose.material3:material3:1.2.0")

}