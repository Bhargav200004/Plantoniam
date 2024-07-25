package com.example.plantoniam.ui.homeScreen

import com.example.plantoniam.util.Cycle
import com.example.plantoniam.util.Sunlight

sealed class HomeEvent {

    data class OnEdibleImageClick(val filterBarPictureComponents: FilterBarPictureComponents) :
        HomeEvent()

    data class OnPlaceImageClick(val filterBarPictureComponents: FilterBarPictureComponents) :
        HomeEvent()

    data class OnClockImageClick(val filterBarPictureComponents: FilterBarPictureComponents) :
        HomeEvent()

    data class OnCycleImageClick(val cycle: Cycle) :
        HomeEvent()

    data class OnSunLightImageClick(val sunlight: Sunlight) :
        HomeEvent()

    data class OnWaterImageClick(val filterBarPictureComponents: FilterBarPictureComponents) :
        HomeEvent()

    data class OnToxicImageClick(val filterBarPictureComponents: FilterBarPictureComponents) :
        HomeEvent()

    data class OnCountIndex(val index : Int) :
            HomeEvent()

    data object OnBottomSheetDismissClick :
            HomeEvent()

    data object OnBottomSheetClick :
            HomeEvent()

    data class OnSelectedChipClick(val selectedChip: SelectedChip) :
            HomeEvent()

}