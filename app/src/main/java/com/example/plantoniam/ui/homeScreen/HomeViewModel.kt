package com.example.plantoniam.ui.homeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantoniam.domain.repository.PlantImageRepository
import com.example.plantoniam.util.Constant.PLANTONIAM_LOGS
import com.example.plantoniam.util.Cycle
import com.example.plantoniam.util.SnackBarEvent
import com.example.plantoniam.util.Sunlight
import com.example.plantoniam.util.Watering
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.round

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

    private val _snackBarEventFlow: MutableSharedFlow<SnackBarEvent> = MutableSharedFlow()
    val snackBarEventFlow = _snackBarEventFlow.asSharedFlow()


    init {
        getAllImage()
    }


    fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.OnEdibleImageClick ->  onEdibleImageClick()
            is HomeEvent.OnPlaceImageClick -> onPlaceImageClick()
            is HomeEvent.OnTemperatureImageClick -> {}
            is HomeEvent.OnCycleImageClick -> {
                viewModelScope.launch {
                    _state.update { state ->
                        state.copy(
                           selectedChip = SelectedChip.CYCLE
                        )
                    }
                    if (event.cycle != Cycle.BIENNIAL && event.cycle != Cycle.BIANNUAL ){
                        getAllImage(cycle = event.cycle.value)
                    }
                }
                Log.d(PLANTONIAM_LOGS , event.cycle.value)
                onBottomSheetDismissClick()
            }
            is HomeEvent.OnSunLightImageClick -> {
                viewModelScope.launch {
                    _state.update { state ->
                        state.copy(
                            selectedChip = SelectedChip.SUNLIGHT
                        )
                    }
                    if (event.sunlight != Sunlight.SUN_PART_SHADE){
                        getAllImage(sunlight = event.sunlight.value)
                    }
                }
                Log.d(PLANTONIAM_LOGS , event.sunlight.value)
                onBottomSheetDismissClick()
            }
            is HomeEvent.OnWaterImageClick -> {
                viewModelScope.launch {
                    if (event.watering != Watering.NONE){
                        getAllImage(watering = event.watering.value)
                    }
                }
                Log.d(PLANTONIAM_LOGS , event.watering.value)
                onBottomSheetDismissClick()
            }
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

            HomeEvent.OnBottomSheetDismissClick -> onBottomSheetDismissClick()
            HomeEvent.OnBottomSheetClick -> onBottomSheetClick()
            is HomeEvent.OnSelectedChipClick -> onSelectedChipClick(event)
            is HomeEvent.OnSliderValueChange -> onValueChange(event.range)
            HomeEvent.OnSliderValueChangeFinished -> onBottomSheetDismissClick()
        }
    }

    private fun onSliderValueChange() {
        viewModelScope.launch {
            getAllImage(startRange = state.value.startRange.toString() , endRange = state.value.endRange.toString())
        }
    }

    private fun onValueChange(range: ClosedFloatingPointRange<Float>) {
        viewModelScope.launch {
            val start = round(range.start * 2) / 2
            val end = round(range.endInclusive * 2) /2

            if (end - start >= 1f) {
                _state.update {state->
                    state.copy(
                        sliderPosition = start..end,
                        startRange = start.toInt(),
                        endRange = end.toInt()
                    )
                }
            }
            onSliderValueChange()
        }

    }

    private fun onSelectedChipClick(event: HomeEvent.OnSelectedChipClick) {
        viewModelScope.launch {
            when (event.selectedChip) {
                SelectedChip.SUNLIGHT -> selectedChipChange(event.selectedChip)
                SelectedChip.CYCLE -> selectedChipChange(event.selectedChip)
                SelectedChip.WATERING -> selectedChipChange(event.selectedChip)
                SelectedChip.TEMPERATURE -> selectedChipChange(event.selectedChip)
            }
        }
    }

    private fun selectedChipChange( selectedChip: SelectedChip) {
        _state.update { state->
            state.copy(
                selectedChip = selectedChip
            )
        }
    }

    private fun onBottomSheetDismissClick() {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(
                    showModalBottomSheet = false
                )
            }
        }
    }

    private fun onBottomSheetClick() {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(
                    showModalBottomSheet = true
                )
            }
        }
    }

    private fun getAllImage(edible : String? = null , indoor: String? = null , startRange : String = "1", endRange : String = "13" , cycle : String = "" , sunlight : String = "" , watering : String = "" , poisonous : String = "0") {
        try {
            viewModelScope.launch {
                val response = plantImageRepository.getAllPlantList(
                    edible = edible,
                    indoor = indoor,
                    startRange = startRange,
                    endRange = endRange,
                    cycle = cycle,
                    sunlight = sunlight,
                    watering = watering,
                    poisonous = poisonous
                )
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
                       getAllImage(poisonous = "0")
                    }
                }

                FilterBarPictureComponents.NON_TOXIC -> {
                    viewModelScope.launch {
                        _state.update { state ->
                            state.copy(
                                toxicImage = FilterBarPictureComponents.TOXIC
                            )
                        }
                        getAllImage(poisonous = "1")
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
                        getAllImage(indoor = "0")
                    }
                }

                FilterBarPictureComponents.OUTDOOR -> {
                    viewModelScope.launch {
                        _state.update { state ->
                            state.copy(
                                placeImage = FilterBarPictureComponents.INDORE
                            )
                        }
                        getAllImage(indoor = "1")
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
                    getAllImage()
                }

                FilterBarPictureComponents.NON_EDIBLE -> {
                    viewModelScope.launch {
                        _state.update { state ->
                            state.copy(
                                edibleImage = FilterBarPictureComponents.EDIBLE
                            )
                        }
                    }
                    getAllImage(edible = "1")
                }
                else -> {}
            }

        } catch (e: Exception) {
            Log.e(PLANTONIAM_LOGS, e.message.toString())
        }
    }


}