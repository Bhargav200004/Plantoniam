package com.example.plantoniam.domain.models.plantList


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("common_name")
    val commonName: String,
    @SerializedName("cycle")
    val cycle: String,
    @SerializedName("default_image")
    val defaultImage: DefaultImage?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("other_name")
    val otherName: List<String>,
    @SerializedName("scientific_name")
    val scientificName: List<String>,
    @SerializedName("sunlight")
    val sunlight: List<String>,
    @SerializedName("watering")
    val watering: String
)