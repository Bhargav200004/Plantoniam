package com.example.plantoniam.ui.imageScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.plantoniam.domain.repository.PlantImageRepository
import com.example.plantoniam.ui.navigation.Route
import com.example.plantoniam.util.Constant.PLANTONIAM_LOGS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val plantImageRepository: PlantImageRepository
) : ViewModel() {

//    val _state = MutableStateFlow()

    val imageId = savedStateHandle.toRoute<Route.ImageScreenNavigation>().id

    var StateValue : String = ""



    fun getImageById() {
        viewModelScope.launch {
            val response = plantImageRepository.getPlantDetailById(imageId).toString()
            StateValue = response
            Log.e(PLANTONIAM_LOGS , "plant detail value : -$StateValue")
        }
    }


}