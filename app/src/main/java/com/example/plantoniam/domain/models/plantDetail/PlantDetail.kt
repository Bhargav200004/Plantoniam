package com.example.plantoniam.domain.models.plantDetail


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlantDetail(
    @SerialName("care-guides")
    val careGuides: String?,
    @SerialName("care_level")
    val careLevel: String?,
    @SerialName("common_name")
    val commonName: String?,
    @SerialName("cones")
    val cones: Boolean?,
    @SerialName("cuisine")
    val cuisine: Boolean?,
    @SerialName("cycle")
    val cycle: String?,
    @SerialName("default_image")
    val defaultImage: DefaultImage?,
    @SerialName("description")
    val description: String?,
    @SerialName("dimension")
    val dimension: String?,
    @SerialName("dimensions")
    val dimensions: Dimensions?,
    @SerialName("drought_tolerant")
    val droughtTolerant: Boolean?,
    @SerialName("edible_fruit")
    val edibleFruit: Boolean?,
    @SerialName("edible_fruit_taste_profile")
    val edibleFruitTasteProfile: String?,
    @SerialName("edible_leaf")
    val edibleLeaf: Boolean?,
    @SerialName("family")
    val family: String?,
    @SerialName("flower_color")
    val flowerColor: String?,
    @SerialName("flowers")
    val flowers: Boolean?,
    @SerialName("fruit_nutritional_value")
    val fruitNutritionalValue: String?,
    @SerialName("fruits")
    val fruits: Boolean?,
    @SerialName("growth_rate")
    val growthRate: String?,
    @SerialName("hardiness")
    val hardiness: Hardiness?,
    @SerialName("hardiness_location")
    val hardinessLocation: HardinessLocation?,
    @SerialName("id")
    val id: Int?,
    @SerialName("indoor")
    val indoor: Boolean?,
    @SerialName("invasive")
    val invasive: Boolean?,
    @SerialName("leaf")
    val leaf: Boolean?,
    @SerialName("leaf_color")
    val leafColor: List<String?>?,
    @SerialName("maintenance")
    val maintenance: String?,
    @SerialName("medicinal")
    val medicinal: Boolean?,
    @SerialName("origin")
    val origin: List<String?>?,
    @SerialName("other_images")
    val otherImages: String?,
    @SerialName("other_name")
    val otherName: List<String?>?,
    @SerialName("pest_susceptibility")
    val pestSusceptibility: List<String?>?,
    @SerialName("pest_susceptibility_api")
    val pestSusceptibilityApi: String?,
    @SerialName("plant_anatomy")
    val plantAnatomy: List<PlantAnatomy?>?,
    @SerialName("poisonous_to_humans")
    val poisonousToHumans: Int?,
    @SerialName("poisonous_to_pets")
    val poisonousToPets: Int?,
    @SerialName("propagation")
    val propagation: List<String?>?,
    @SerialName("pruning_month")
    val pruningMonth: List<String?>?,
    @SerialName("salt_tolerant")
    val saltTolerant: Boolean?,
    @SerialName("scientific_name")
    val scientificName: List<String?>?,
    @SerialName("seeds")
    val seeds: Int?,
    @SerialName("soil")
    val soil: List<String?>?,
    @SerialName("sunlight")
    val sunlight: List<String?>?,
    @SerialName("thorny")
    val thorny: Boolean?,
    @SerialName("tropical")
    val tropical: Boolean?,
    @SerialName("type")
    val type: String?,
    @SerialName("watering")
    val watering: String?,
    @SerialName("watering_general_benchmark")
    val wateringGeneralBenchmark: WateringGeneralBenchmark?,
)