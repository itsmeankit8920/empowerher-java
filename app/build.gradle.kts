plugins {
    alias(libs.plugins.android.application)
    // Add Kotlin plugin if you're using Kotlin

}

android {
    namespace = "com.ankit.empowerher"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ankit.empowerher"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.recyclerview)
    implementation(libs.opencsv)
    // Razorpay dependency
    implementation("com.razorpay:checkout:1.6.20")
    // Unit testing dependencies
    testImplementation(libs.junit)

    // Android testing dependencies
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
   



    // Add Mockk for mocking in unit tests (if using Kotlin)
}
