package com.example.plantoniam.ui.homeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantoniam.domain.repository.PlantImageRepository
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
class HomeViewModel @Inject constructor(
    private val plantImageRepository: PlantImageRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeData())
    val state : StateFlow<HomeData> = _state.asStateFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue = HomeData()
    )


    init {
        getAllImage()
    }


    fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.OnEdibleImageClick -> onEdibleImageClick()
            is HomeEvent.OnPlaceImageClick -> onPlaceImageClick()
            is HomeEvent.OnClockImageClick -> {

            }
            is HomeEvent.OnCycleImageClick -> {}
            is HomeEvent.OnSunLightImageClick -> {}
            is HomeEvent.OnWaterImageClick -> {}
            is HomeEvent.OnToxicImageClick -> onToxicButtonClick()
            is HomeEvent.OnCountIndex -> {
                viewModelScope.launch {
                    _state.update { state->
                        state.copy(
                         index = event.index
                        )
                    }

                    if (state.value.index > 12 ) {
                        _state.update { state ->
                            state.copy(
                                isTopBarShowing = false
                            )
                        }
                    }
                    else {
                        _state.update { state ->
                            state.copy(
                                isTopBarShowing = true
                            )
                        }
                    }

                }
            }
        }
    }

    private fun getAllEdibleImage() {
      try {
          viewModelScope.launch {
              val response = plantImageRepository.getAllEdiblePlantList("1")
              Log.d(PLANTONIAM_LOGS,response.toString())
              _state.update { state ->
                  state.copy(
                      plantList = response
                  )
              }
          }
      }
      catch (e : Exception) {
          Log.e(PLANTONIAM_LOGS , e.message.toString())
      }
    }


    private fun getAllImage() {
        try {
            viewModelScope.launch {
                val response = plantImageRepository.getAllPlantList()
                Log.d(PLANTONIAM_LOGS,response.toString())
                _state.update { state ->
                    state.copy(
                        plantList = response
                    )
                }
            }

        } catch (e : Exception) {
            Log.e(PLANTONIAM_LOGS , e.message.toString())
        }
    }



    private fun onToxicButtonClick() {
        try {
            when (state.value.toxicImage) {
                FilterBarPictureComponents.TOXIC -> {
                    viewModelScope.launch {
                        _state.update { state ->
                            state.copy(
                                toxicImage = FilterBarPictureComponents.NON_TOXIC
                            )
                        }
                    }
                }

                FilterBarPictureComponents.NON_TOXIC -> {
                    viewModelScope.launch {
                        _state.update { state ->
                            state.copy(
                                toxicImage = FilterBarPictureComponents.TOXIC
                            )
                        }
                    }
                }

                else -> {}
            }
        } catch (e: Exception) {
            Log.e(PLANTONIAM_LOGS, e.message.toString())
        }
    }

    private fun onPlaceImageClick() {
        try {
            when (state.value.placeImage) {
                FilterBarPictureComponents.INDORE -> {
                    viewModelScope.launch {
                        _state.update { state ->
                            state.copy(
                                placeImage = FilterBarPictureComponents.OUTDOOR
                            )
                        }
                    }
                }

                FilterBarPictureComponents.OUTDOOR -> {
                    viewModelScope.launch {
                        _state.update { state ->
                            state.copy(
                                placeImage = FilterBarPictureComponents.INDORE
                            )
                        }
                    }
                }

                else -> {}
            }
        } catch (e: Exception) {
            Log.e(PLANTONIAM_LOGS, e.message.toString())
        }
    }

    private fun onEdibleImageClick() {
        try {
            when (state.value.edibleImage) {
                FilterBarPictureComponents.EDIBLE -> {
                    viewModelScope.launch {
                        _state.update { state ->
                            state.copy(
                                edibleImage = FilterBarPictureComponents.NON_EDIBLE
                            )
                        }
                    }

                }

                FilterBarPictureComponents.NON_EDIBLE -> {
                    viewModelScope.launch {
                        _state.update { state ->
                            state.copy(
                                edibleImage = FilterBarPictureComponents.EDIBLE
                            )
                        }
                    }
//                    getAllEdibleImage()
                }
                else -> {}
            }

        } catch (e: Exception) {
            Log.e(PLANTONIAM_LOGS, e.message.toString())
        }
    }


}

