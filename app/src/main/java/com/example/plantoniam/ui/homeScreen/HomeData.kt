package com.example.plantoniam.ui.homeScreen

import com.example.plantoniam.domain.models.plantList.PlantList

data class HomeData(
    val edibleImage : FilterBarPictureComponents = FilterBarPictureComponents.NON_EDIBLE,
    val placeImage : FilterBarPictureComponents = FilterBarPictureComponents.OUTDOOR,
    val temperatureImage : FilterBarPictureComponents = FilterBarPictureComponents.TEMPERATURE,
    val cycleImage : FilterBarPictureComponents = FilterBarPictureComponents.CYCLE,
    val sunLightImage : FilterBarPictureComponents = FilterBarPictureComponents.SUNLIGHT,
    val waterImage : FilterBarPictureComponents = FilterBarPictureComponents.WATERING,
    val toxicImage : FilterBarPictureComponents = FilterBarPictureComponents.NON_TOXIC,
    val index : Int = 1,
    val isTopBarShowing : Boolean = true,
    val plantList: PlantList? = null,
    val showModalBottomSheet : Boolean = false,
    val selectedChip : SelectedChip = SelectedChip.SUNLIGHT,
    val sliderPosition : ClosedFloatingPointRange<Float> = 1f..13f,
    val startRange : Int = 1,
    val endRange : Int = 13
)