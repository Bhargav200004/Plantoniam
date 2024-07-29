package com.example.plantoniam.domain.models.plantList


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlantData(
    @SerialName("common_name")
    val commonName: String?,
    @SerialName("cycle")
    val cycle: String?,
    @SerialName("default_image")
    val defaultImage: PlantDefaultImage?,
    @SerialName("id")
    val id: Int,
    @SerialName("other_name")
    val otherName: List<String?>,
    @SerialName("scientific_name")
    val scientificName: List<String?>,
    @SerialName("sunlight")
    val sunlight: List<String?>,
    @SerialName("watering")
    val watering: String?
)