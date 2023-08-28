package com.adkhamjon.rikmasterstest.di

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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideKtorClient() = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL

        }
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    @Singleton
    @Provides
    fun provideApiService(
        httpClient: HttpClient
    ): ApiService {
        return ApiServiceImpl(httpClient = httpClient)
    }
}