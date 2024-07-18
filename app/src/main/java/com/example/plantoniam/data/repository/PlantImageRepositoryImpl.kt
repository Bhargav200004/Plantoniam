package com.example.plantoniam.data.repository

import com.example.plantoniam.data.network.PlantoniamApiInterface
import com.example.plantoniam.domain.models.plantList.Data
import com.example.plantoniam.domain.models.plantList.PlantList
import com.example.plantoniam.domain.repository.PlantImageRepository
import javax.inject.Inject

class PlantImageRepositoryImpl @Inject constructor(
    private val plantApiService : PlantoniamApiInterface
) : PlantImageRepository {
    override suspend fun getAllPlantList(): PlantList {
        return plantApiService.getPlantData()
    }

    override suspend fun getPlantById(id: Int): Data {
        TODO()
    }


}