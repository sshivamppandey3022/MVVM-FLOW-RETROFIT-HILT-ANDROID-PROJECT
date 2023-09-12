plugins {
	id("com.android.application")
	id("dagger.hilt.android.plugin")
	id("org.jetbrains.kotlin.android")
	id("kotlin-parcelize")
	id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.shivam.expressiom"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.shivam.expressiom"
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
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    defaultConfig {
        multiDexEnabled = true
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

	implementation("androidx.constraintlayout:constraintlayout:2.1.4")
	implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
	implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
	implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
	implementation ("androidx.activity:activity-ktx:1.7.2")
	implementation("androidx.legacy:legacy-support-v4:1.0.0")
	implementation("androidx.appcompat:appcompat:1.6.1")
	implementation("androidx.core:core-ktx:1.10.1")


	implementation("com.google.android.material:material:1.9.0")
	implementation("com.google.dagger:hilt-android:2.48")
	kapt("com.google.dagger:hilt-compiler:2.48")

	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
	implementation(kotlin("stdlib"))

	implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.9")
	implementation("com.squareup.retrofit2:converter-gson:2.9.0")
	implementation("com.squareup.retrofit2:retrofit:2.9.0")


    implementation("androidx.multidex:multidex:2.0.1")

}
