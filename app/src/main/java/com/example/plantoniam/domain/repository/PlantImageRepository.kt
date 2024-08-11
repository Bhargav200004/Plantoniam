package com.example.plantoniam.domain.repository

import com.example.plantoniam.domain.models.plantDetail.PlantDetail
import com.example.plantoniam.domain.models.plantList.PlantList

interface PlantImageRepository {

    suspend fun getAllPlantList(page : Int = 1 , edible: String? = null, indoor: String? = null, startRange : String = "1", endRange : String = "13", cycle : String = "", sunlight : String = "", watering : String = "", poisonous : String = "0") : PlantList


    suspend fun getPlantDetailById(id : String = "1") : PlantDetail

}