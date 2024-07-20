package com.example.plantoniam.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


//    //Provide retrofit
//    @Singleton
//    @Provides
//    fun provideApiService() : PlantoniamApiInterface {
//
////        val builder = OkHttpClient.Builder()
////        val interceptor  = HttpLoggingInterceptor()
////
////
////
////
////        val gson = GsonBuilder()
////            .setLenient()
////            .create()
////        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
////        builder.addInterceptor(interceptor = interceptor)
////
////        builder.addNetworkInterceptor(Interceptor { chain ->
////            val requestBuilder = chain.request().newBuilder()
////            requestBuilder.header("Content-Type", "application/json")
////            chain.proceed(requestBuilder.build())
////        }).build()
//
//
//        val contentType = "application/json".toMediaType()
//        val json = Json { ignoreUnknownKeys = true }
//
//        val retrofit = Retrofit.Builder()
//            .addConverterFactory(json.asConverterFactory(contentType))
//            .baseUrl(BASEURL)
//            .build()
//
////        val retrofit = Retrofit.Builder()
////            .addConverterFactory(GsonConverterFactory.create())
//////            .client(builder.build())
////            .baseUrl(BASEURL)
////            .build()
//
//        return retrofit.create(PlantoniamApiInterface::class.java)
//    }
//
//
//    //Providing repository Implementation dependency
//    @Singleton
//    @Provides
//    fun providePlantImageRepositoryImpl(
//        apiInterface: PlantoniamApiInterface
//    ): PlantImageRepository {
//        return PlantImageRepositoryImpl(apiInterface)
//    }


}

