package com.example.plantoniam.domain.repository

import com.example.plantoniam.domain.models.plantList.PlantList

interface PlantImageRepository {

    suspend fun getAllPlantList(edible: String? = null , indoor: String? = null , startRange : String = "1" , endRange : String = "13" , cycle : String = "" , sunlight : String = "" , watering : String = "" , poisonous : String = "0") : PlantList


}