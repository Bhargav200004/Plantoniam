package com.example.plantoniam.data.repository

import androidx.paging.PagingData
import com.example.plantoniam.domain.models.plantDetail.PlantDetail
import com.example.plantoniam.domain.models.plantList.PlantData
import com.example.plantoniam.domain.models.plantList.PlantList
import com.example.plantoniam.domain.repository.PlantImageRepository
import com.example.plantoniam.util.addingParameterAtEnd
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class PlantImageRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
) : PlantImageRepository {


    override suspend fun getAllPlantList(page : Int ,edible: String? , indoor: String? , startRange: String , endRange : String , cycle : String , sunlight : String , watering : String , poisonous : String): PlantList =
        httpClient.get(addingParameterAtEnd("species-list") + "&page=$page" +"&edible=$edible" + "&indoor=$indoor" + "&hardiness=$startRange-$endRange" + "&cycle=$cycle" + "&sunlight=$sunlight" + "&watering=$watering" + "&poisonous=$poisonous").body()


    override suspend fun getPlantDetailById(id: String): PlantDetail =
        httpClient.get("https://perenual.com/api/species/details/${id}?key=sk-pEmc66900e6bdad736221").body()

}

