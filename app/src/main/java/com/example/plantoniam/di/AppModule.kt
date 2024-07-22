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

            // For Logging
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.e(PLANTONIAM_LOGS, message)
                    }
                }
            }

            // Timeout plugin
            install(HttpTimeout) {
                requestTimeoutMillis = 15000L
                connectTimeoutMillis = 15000L
                socketTimeoutMillis = 15000L
            }

            // JSON Response properties
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                        explicitNulls = false
                    }
                )
            }

            // Default request for POST, PUT, DELETE,etc...
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                //add this accept() for accept Json Body or Raw Json as Request Body
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

