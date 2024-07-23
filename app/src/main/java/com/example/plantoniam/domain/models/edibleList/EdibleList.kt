package com.example.plantoniam.domain.models.edibleList


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EdibleList(
    @SerialName("current_page")
    val currentPage: Int,
    @SerialName("data")
    val `data`: List<Data>,
    @SerialName("from")
    val from: Int,
    @SerialName("last_page")
    val lastPage: Int,
    @SerialName("per_page")
    val perPage: Int,
    @SerialName("to")
    val to: Int,
    @SerialName("total")
    val total: Int
)