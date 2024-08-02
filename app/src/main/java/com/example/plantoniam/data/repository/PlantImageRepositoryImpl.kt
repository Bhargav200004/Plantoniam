package com.example.plantoniam.data.repository

import com.example.plantoniam.domain.models.plantList.PlantList
import com.example.plantoniam.domain.repository.PlantImageRepository
import com.example.plantoniam.util.addingParameterAtEnd
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject


class PlantImageRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
) : PlantImageRepository {


    override suspend fun getAllPlantList(edible: String? , indoor: String? , startRange: String , endRange : String , cycle : String , sunlight : String , watering : String , poisonous : String): PlantList =
      httpClient.get(addingParameterAtEnd("species-list") + "&edible=$edible" + "&indoor=$indoor" + "&hardiness=$startRange-$endRange" + "&cycle=$cycle" + "&sunlight=$sunlight" + "&watering=$watering" + "&poisonous=$poisonous").body()


}

