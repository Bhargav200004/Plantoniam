package com.example.plantoniam.di

import com.example.plantoniam.data.network.PlantoniamApiInterface
import com.example.plantoniam.data.repository.PlantImageRepositoryImpl
import com.example.plantoniam.domain.repository.PlantImageRepository
import com.example.plantoniam.util.Constant.BASEURL
import com.example.plantoniam.util.helper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    //Provide retrofit
    @Singleton
    @Provides
    fun provideApiService() : PlantoniamApiInterface {
        val gson = GsonBuilder()
            .setLenient()
            .create()



        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASEURL)
            .build()

        return retrofit.create(PlantoniamApiInterface::class.java)
    }


    //Providing repository Implementation dependency
    @Singleton
    @Provides
    fun providePlantImageRepositoryImpl(
        apiInterface: PlantoniamApiInterface
    ): PlantImageRepository {
        return PlantImageRepositoryImpl(apiInterface)
    }

}

