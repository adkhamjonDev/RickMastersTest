// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.android_application) version Plugins.PluginsVersion.android_application apply false
    id(Plugins.android_library) version Plugins.PluginsVersion.android_library apply false
    id(Plugins.jetbrains_kotlin_android) version Plugins.PluginsVersion.jetbrains_kotlin_android apply false
    id(Plugins.dagger_hilt) version Plugins.PluginsVersion.dagger_hilt apply false
    id(Plugins.io_realm) version Plugins.PluginsVersion.io_realm apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.8.10")
    }

}