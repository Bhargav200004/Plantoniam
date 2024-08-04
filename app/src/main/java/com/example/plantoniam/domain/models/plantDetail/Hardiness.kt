package com.example.plantoniam.domain.models.plantDetail


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hardiness(
    @SerialName("max")
    val max: String?,
    @SerialName("min")
    val min: String?
)