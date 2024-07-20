package com.example.plantoniam.data.network

import com.example.plantoniam.domain.models.plantList.PlantList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiInterfaceImpl @Inject constructor(
    private val httpClient: HttpClient
) : PlantoniamApiInterface {
    override suspend fun getPlantData(): PlantList {
        TODO("Not yet implemented")
    }

    override fun getPlantData2(): Flow<ApiResult<PlantList>> {
        return flow {
            emit(ApiResult.Loading())
            try {
                emit(ApiResult.Success(httpClient.get("/species-list?").body()))
            }
            catch (e : Exception){
                e.printStackTrace()
                emit(ApiResult.Error(e.message ?: "Something Went Wrong"))
            }
        }
    }

}