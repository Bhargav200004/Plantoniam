package com.example.plantoniam.domain.models.plantList


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DefaultImage(
    val license: Int,
    val licenseName: String,
    val licenseUrl: String,
    val mediumUrl: String,
    val originalUrl: String,
    val regularUrl: String,
    val smallUrl: String,
    val thumbnail: String
)