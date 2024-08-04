package com.example.plantoniam.domain.models.plantDetail


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HardinessLocation(
    @SerialName("full_iframe")
    val fullIframe: String?,
    @SerialName("full_url")
    val fullUrl: String?
)