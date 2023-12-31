plugins {
    id("com.android.application")
}

android {
    namespace = "com.sam.killtivolauncher"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.sam.killtivolauncher"
        minSdk = 21
        targetSdk = 33
        versionCode = 2
        versionName = "1.1"

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

}