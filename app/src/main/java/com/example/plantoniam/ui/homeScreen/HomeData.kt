package com.example.plantoniam.ui.homeScreen

import com.example.plantoniam.domain.models.plantList.PlantList

data class HomeData(
    val edibleImage : FilterBarPictureComponents = FilterBarPictureComponents.NON_EDIBLE,
    val placeImage : FilterBarPictureComponents = FilterBarPictureComponents.OUTDOOR,
    val timeImage : FilterBarPictureComponents = FilterBarPictureComponents.TIME,
    val cycleImage : FilterBarPictureComponents = FilterBarPictureComponents.CYCLE,
    val sunLightImage : FilterBarPictureComponents = FilterBarPictureComponents.SUNLIGHT,
    val waterImage : FilterBarPictureComponents = FilterBarPictureComponents.WATERING,
    val toxicImage : FilterBarPictureComponents = FilterBarPictureComponents.TOXIC,
    val index : Int = 1,
    val isTopBarShowing : Boolean = true,
    val plantList: PlantList? =  null
)