plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.lab7"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.lab7"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    
    // RecyclerView pour afficher la liste
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    // CardView
    implementation("androidx.cardview:cardview:1.0.0")
    // Image circulaire
    implementation("de.hdodenhof:circleimageview:3.1.0")
    // Chargement d'images depuis internet
    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}