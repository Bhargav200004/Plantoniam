package com.example.plantoniam.domain.models.plantList


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val commonName: String,
    val cycle: String,
    val defaultImage: DefaultImage?,
    val id: Int,
    val otherName: List<String>,
    val scientificName: List<String>,
    val sunlight: List<String>,
    val watering: String
)