package com.example.plantoniam.ui.homeScreen

sealed class HomeEvent {

    data class OnEdibleImageClick(val filterBarPictureComponents: FilterBarPictureComponents) :
        HomeEvent()

    data class OnPlaceImageClick(val filterBarPictureComponents: FilterBarPictureComponents) :
        HomeEvent()

    data class OnClockImageClick(val filterBarPictureComponents: FilterBarPictureComponents) :
        HomeEvent()

    data class OnCycleImageClick(val filterBarPictureComponents: FilterBarPictureComponents) :
        HomeEvent()

    data class OnSunLightImageClick(val filterBarPictureComponents: FilterBarPictureComponents) :
        HomeEvent()

    data class OnWaterImageClick(val filterBarPictureComponents: FilterBarPictureComponents) :
        HomeEvent()

    data class OnToxicImageClick(val filterBarPictureComponents: FilterBarPictureComponents) :
        HomeEvent()

    data class OnCountIndex(val index : Int) :
            HomeEvent()

}