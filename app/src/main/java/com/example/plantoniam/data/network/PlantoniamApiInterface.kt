package com.example.plantoniam.data.network

import com.example.plantoniam.domain.models.plantList.PlantList
import com.example.plantoniam.util.Constant.APIKEY
import retrofit2.http.GET
import retrofit2.http.Headers

interface PlantoniamApiInterface {

    @Headers("key: $APIKEY")
    @GET("species-list")
    suspend fun getPlantData() : PlantList



}