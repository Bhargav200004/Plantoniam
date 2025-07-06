package com.example.plantoniam.di

import android.util.Log
import com.example.plantoniam.data.repository.PlantImageRepositoryImpl
import com.example.plantoniam.domain.repository.PlantImageRepository
import com.example.plantoniam.util.Constant.PLANTONIAM_LOGS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    /*
    Provide ktor client
     */

    @Singleton
    @Provides
    fun provideService(): HttpClient {
        return HttpClient(Android) {

            // Logging for debugging
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.e(PLANTONIAM_LOGS, message)
                    }
                }
            }

            // Timeout configuration
            install(HttpTimeout) {
                requestTimeoutMillis = 15_000L
                connectTimeoutMillis = 15_000L
                socketTimeoutMillis = 15_000L
            }

            // Handle JSON with null safety
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true  // Ignores extra fields from server
                        isLenient = true          // Allows non-strict JSON
                        prettyPrint = false       // Turn off in production
                        explicitNulls = false     // Don't serialize nulls
                        encodeDefaults = true     // Include default values in serialization
                        coerceInputValues = true  // Prevent crashes from unexpected types
                    }
                )
            }

            // Set default headers for all requests
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
        }
    }


    //Providing repository Implementation dependency
    @Singleton
    @Provides
    fun providePlantImageRepositoryImpl(
        httpClient: HttpClient
    ): PlantImageRepository {
        return PlantImageRepositoryImpl(httpClient)
    }


}

