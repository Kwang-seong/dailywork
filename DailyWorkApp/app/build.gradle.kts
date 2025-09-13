plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android") version "1.9.0"  // Kotlin 버전 명시
    id("com.google.gms.google-services")
}


android {
    namespace = "com.example.dailyworkapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.dailyworkapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        kotlinOptions {
            jvmTarget = "11"
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
}

dependencies {
    // AndroidX 기본 라이브러리
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.activity:activity-ktx:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Firebase BOM (버전 명시)
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))

    // Firebase 모듈 (원하는 기능만 골라서 사용 가능)
    implementation("com.google.firebase:firebase-analytics-ktx")
    // implementation("com.google.firebase:firebase-auth-ktx")
    // implementation("com.google.firebase:firebase-database-ktx")
    // 필요 시 위 항목 주석 해제

    // 테스트 관련
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
