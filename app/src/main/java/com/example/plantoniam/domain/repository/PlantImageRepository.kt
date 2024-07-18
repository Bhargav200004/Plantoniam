package com.example.plantoniam.domain.repository

import com.example.plantoniam.domain.models.plantList.Data
import com.example.plantoniam.domain.models.plantList.PlantList

interface PlantImageRepository {

    suspend fun getAllPlantList(): PlantList

    suspend fun getPlantById(id : Int): Data

}