package com.adkhamjon.rikmasterstest

import android.app.Application
import android.content.res.Resources
import dagger.hilt.android.HiltAndroidApp
import io.realm.kotlin.Realm

@HiltAndroidApp
class App : Application() {
    companion object {
        lateinit var resources: Resources
    }

    override fun onCreate() {
        super.onCreate()
        Companion.resources = resources
    }
}