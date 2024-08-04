package com.example.plantoniam.domain.models.plantDetail


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WateringGeneralBenchmark(
    @SerialName("unit")
    val unit: String?,
    @SerialName("value")
    val value: String?
)