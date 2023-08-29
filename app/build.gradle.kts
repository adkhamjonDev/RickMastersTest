plugins {
    id(Plugins.android_application)
    id(Plugins.jetbrains_kotlin_android)
    id(Plugins.kapt)
    id(Plugins.dagger_hilt)
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = AppConfig.applicationId
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.testInstrumentationRunner

        multiDexEnabled = AppConfig.multiDexEnabled

        vectorDrawables {
            useSupportLibrary = AppConfig.useSupportLibrary
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = BuildType.isMinifyEnabled
            isShrinkResources = BuildType.isShrinkResources
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
        jvmTarget = AppConfig.jvmTarget
    }
    buildFeatures {
        compose = AppConfig.compose
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AppConfig.kotlinCompilerExtensionVersion
    }
    packaging {
        resources {
            excludes += AppConfig.excludes
        }
    }
}

dependencies {

    implementation(Dependencies.Core.core_ktx)
    implementation(Dependencies.Core.lifecycle_runtime_ktx)
    implementation(Dependencies.Core.activity_compose)
    implementation(Dependencies.Core.compose_material)
    implementation(Dependencies.Core.compose_ui_tooling)
    implementation(Dependencies.Core.compose_ui_tooling_preview)
    implementation(Dependencies.Core.compose_ui)
    testImplementation(Dependencies.Test.junit)
    androidTestImplementation(Dependencies.Test.ext_junit)
    androidTestImplementation(Dependencies.Test.espresso_core)
    hilt()
    implementation(Dependencies.Coroutine.coroutine)
    implementation(Dependencies.Gson.gson)
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.27.0")

    implementation("io.ktor:ktor-client-android:1.5.0")
    implementation("io.ktor:ktor-client-serialization:1.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
    implementation("io.ktor:ktor-client-logging-jvm:1.5.0")

    implementation("io.coil-kt:coil-compose:2.4.0")

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.27.0")
}