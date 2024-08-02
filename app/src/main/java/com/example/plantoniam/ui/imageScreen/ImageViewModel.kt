package com.example.plantoniam.ui.imageScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.plantoniam.ui.navigation.Route
import kotlinx.coroutines.flow.MutableStateFlow

class ImageViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

//    val _state = MutableStateFlow()

    val imageId = savedStateHandle.toRoute<Route.ImageScreenNavigation>().id


}