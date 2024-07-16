package com.example.plantoniam.ui.homeScreen

data class HomeData(
    val edibleImage : FilterBarPictureComponents = FilterBarPictureComponents.EDIBLE,
    val placeImage : FilterBarPictureComponents = FilterBarPictureComponents.OUTDOOR,
    val timeImage : FilterBarPictureComponents = FilterBarPictureComponents.TIME,
    val cycleImage : FilterBarPictureComponents = FilterBarPictureComponents.CYCLE,
    val sunLightImage : FilterBarPictureComponents = FilterBarPictureComponents.SUNLIGHT,
    val waterImage : FilterBarPictureComponents = FilterBarPictureComponents.WATERING,
    val toxicImage : FilterBarPictureComponents = FilterBarPictureComponents.TOXIC,
)