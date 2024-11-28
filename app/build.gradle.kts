plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.kotlin.android)
}


android {
    namespace = "com.thi.btl_gplx"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.thi.btl_gplx"
        minSdk = 24
        targetSdk = 34
        versionCode = 5
        versionName = "2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

   buildFeatures{
       viewBinding = true
   }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    implementation ("androidx.room:room-runtime:2.6.1")
    implementation(libs.firebase.messaging)
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")
    implementation ("com.google.code.gson:gson:2.8.9")
    implementation(libs.core.ktx)
    implementation(libs.firebase.database)
    annotationProcessor ("androidx.room:room-compiler:2.6.1")
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")
    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.room.common)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}