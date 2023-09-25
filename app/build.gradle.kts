plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.education.brcmeducorn"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.education.brcmeducorn"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.9.9")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation("com.applandeo:material-calendar-view:1.9.0-rc03")

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:<latest-version>")
    implementation("com.kizitonwose.calendar:view:2.3.0")
    implementation("com.kizitonwose.calendar:compose:2.3.0")
    implementation ("com.makeramen:roundedimageview:2.3.0")

    // image slider library
    implementation("com.github.denzcoskun:ImageSlideshow:0.1.2")

    // neumorphic library
    implementation("com.github.fornewid:neumorphism:0.2.1")

    //gallery slider library
    //country code picker
    implementation("com.hbb20:ccp:2.5.2")


    //room library
    implementation("androidx.room:room-runtime:2.5.2")

    kapt("androidx.room:room-compiler:2.5.2")
    implementation("androidx.room:room-ktx:2.3.0")


}