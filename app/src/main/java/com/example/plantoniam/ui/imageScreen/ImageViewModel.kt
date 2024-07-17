package com.example.plantoniam.ui.imageScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.plantoniam.ui.navigation.Route

class ImageViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val imageId = savedStateHandle.toRoute<Route.ImageScreenNavigation>().id

}