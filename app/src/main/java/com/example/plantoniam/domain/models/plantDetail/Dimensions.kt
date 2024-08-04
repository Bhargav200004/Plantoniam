package com.example.plantoniam.domain.models.plantDetail


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dimensions(
    @SerialName("max_value")
    val maxValue: Int?,
    @SerialName("min_value")
    val minValue: Int?,
    @SerialName("type")
    val type: String?,
    @SerialName("unit")
    val unit: String?
)