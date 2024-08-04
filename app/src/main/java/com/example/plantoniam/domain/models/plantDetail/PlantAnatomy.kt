package com.example.plantoniam.domain.models.plantDetail


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlantAnatomy(
    @SerialName("color")
    val color: List<String?>?,
    @SerialName("part")
    val part: String?
)