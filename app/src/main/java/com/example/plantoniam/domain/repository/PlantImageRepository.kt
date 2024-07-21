package com.example.plantoniam.domain.repository

import com.example.plantoniam.domain.models.plantList.PlantList

interface PlantImageRepository {

    suspend fun getAllPlantList(): PlantList


}