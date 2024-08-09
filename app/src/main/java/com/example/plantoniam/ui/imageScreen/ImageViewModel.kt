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
import com.example.plantoniam.ui.homeScreen.HomeData
import com.example.plantoniam.ui.navigation.Route
import com.example.plantoniam.util.Constant.PLANTONIAM_LOGS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val plantImageRepository: PlantImageRepository
) : ViewModel() {


    private val imageId = savedStateHandle.toRoute<Route.ImageScreenNavigation>().id

    private val _state = MutableStateFlow(ImageData())
    val state: StateFlow<ImageData> = _state.asStateFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue = ImageData()
    )

    init {
        getImageById(imageId = imageId)
    }

    private fun getImageById(imageId: String) {
        try {
            viewModelScope.launch {
                val response = plantImageRepository.getPlantDetailById(id = imageId)
                _state.update { state ->
                    state.copy(
                        plantDetail = response,
                        otherName = response.otherName
                    )
                }
            }
        } catch (e: Exception) {
            Log.e(PLANTONIAM_LOGS, e.message.toString())
        }
    }

}