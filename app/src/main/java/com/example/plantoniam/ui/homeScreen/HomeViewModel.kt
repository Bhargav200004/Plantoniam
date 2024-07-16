package com.example.plantoniam.ui.homeScreen

import android.util.Log
import androidx.compose.ui.util.trace
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.plantoniam.util.Constant.PLANTORIAM_LOGS
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(HomeData())
    val state : StateFlow<HomeData> = _state.asStateFlow()

    fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.OnEdibleImageClick -> {
                try {
                    when(state.value.edibleImage){
                        FilterBarPictureComponents.EDIBLE -> {
                            viewModelScope.launch {
                                _state.update { state->
                                    state.copy(
                                        edibleImage = FilterBarPictureComponents.NON_EDIBLE
                                    )
                                }
                            }
                        }
                        FilterBarPictureComponents.NON_EDIBLE -> {
                            viewModelScope.launch {
                                _state.update { state->
                                    state.copy(
                                        edibleImage = FilterBarPictureComponents.EDIBLE
                                    )
                                }
                            }
                        }
                        else -> {}
                    }

                }catch (e : Exception){
                    Log.e(PLANTORIAM_LOGS , e.message.toString())
                }
            }
            is HomeEvent.OnPlaceImageClick -> {
                try {
                    when(state.value.placeImage){
                        FilterBarPictureComponents.INDORE -> {
                            viewModelScope.launch {
                                _state.update {state ->
                                    state.copy(
                                        placeImage = FilterBarPictureComponents.OUTDOOR
                                    )
                                }
                            }
                        }
                        FilterBarPictureComponents.OUTDOOR -> {
                            viewModelScope.launch {
                                _state.update {state ->
                                    state.copy(
                                        placeImage = FilterBarPictureComponents.INDORE
                                    )
                                }
                            }
                        }
                        else -> {}
                    }
                }catch (e : Exception){
                    Log.e(PLANTORIAM_LOGS , e.message.toString())
                }
            }
            is HomeEvent.OnClockImageClick -> {

            }
            is HomeEvent.OnCycleImageClick -> {

            }
            is HomeEvent.OnSunLightImageClick -> {

            }
            is HomeEvent.OnWaterImageClick -> {

            }
            is HomeEvent.OnPoisonousImageClick -> {
                try {
                    when(state.value.toxicImage){
                        FilterBarPictureComponents.TOXIC -> {
                            viewModelScope.launch {
                                _state.update {state ->
                                    state.copy(
                                        toxicImage = FilterBarPictureComponents.NON_TOXIC
                                    )
                                }
                            }
                        }
                        FilterBarPictureComponents.NON_TOXIC -> {
                            viewModelScope.launch {
                                _state.update {state ->
                                    state.copy(
                                        toxicImage = FilterBarPictureComponents.TOXIC
                                    )
                                }
                            }
                        }
                        else -> {}
                    }
                }catch (e : Exception){
                    Log.e(PLANTORIAM_LOGS , e.message.toString())
                }
            }
        }
    }


}

