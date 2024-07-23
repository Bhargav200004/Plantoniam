package com.example.plantoniam.domain.models.edibleList


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DefaultImage(
    @SerialName("license")
    val license: Int,
    @SerialName("license_name")
    val licenseName: String,
    @SerialName("license_url")
    val licenseUrl: String,
    @SerialName("medium_url")
    val mediumUrl: String?,
    @SerialName("original_url")
    val originalUrl: String,
    @SerialName("regular_url")
    val regularUrl: String?,
    @SerialName("small_url")
    val smallUrl: String?,
    @SerialName("thumbnail")
    val thumbnail: String?
)