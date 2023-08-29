package com.adkhamjon.rikmasterstest.di

import com.adkhamjon.rikmasterstest.data.local.entity.CameraEntity
import com.adkhamjon.rikmasterstest.data.remote.ApiService
import com.adkhamjon.rikmasterstest.data.remote.ApiServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRealmDatabase(): Realm =
        Realm.open(
            RealmConfiguration.Builder(
                schema = setOf(
                    CameraEntity::class
                )
            )
                .compactOnLaunch()
                .build()
        )
}