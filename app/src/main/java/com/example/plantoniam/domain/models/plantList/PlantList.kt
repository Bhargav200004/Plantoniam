package com.example.plantoniam.domain.models.plantList


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class PlantList(
    val currentPage: Int,
    val data: List<Data>,
    val from: Int,
    val lastPage: Int,
    val perPage: Int,
    val to: Int,
    val total: Int
)