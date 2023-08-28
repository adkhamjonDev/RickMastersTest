package com.adkhamjon.rikmasterstest.di

import com.adkhamjon.rikmasterstest.data.remote.ApiService
import com.adkhamjon.rikmasterstest.data.remote.ApiServiceImpl
import com.adkhamjon.rikmasterstest.data.repository.RickMasterRepositoryImpl
import com.adkhamjon.rikmasterstest.domain.repository.RickMasterRepository
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
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        apiService: ApiService
    ): RickMasterRepository {
        return RickMasterRepositoryImpl(apiService)
    }
}