package com.example.plantoniam.ui.homeScreen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.plantoniam.ui.components.CanvasBackGround
import com.example.plantoniam.ui.navigation.Route
import com.example.plantoniam.util.Constant.PLANTONIAM_LOGS
import com.example.plantoniam.util.Cycle
import com.example.plantoniam.util.SnackBarEvent
import com.example.plantoniam.util.Sunlight
import com.example.plantoniam.util.Watering
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest


@Composable
fun HomeScreen(navController: NavHostController) {

    val viewModel: HomeViewModel = hiltViewModel()

    val uiState by viewModel.state.collectAsStateWithLifecycle()

    val density = LocalDensity.current

    val snackBarState = remember { SnackbarHostState() }


    if (uiState.showModalBottomSheet) {
        BottomSheet(
            uiState = uiState,
            viewModel::onEvent,
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        AnimatedVisibility(
            visible = uiState.isTopBarShowing,
            enter = slideInVertically {
                with(density) { -40.dp.roundToPx() }
            } + expandVertically(
                animationSpec = tween(durationMillis = 1000),
                expandFrom = Alignment.Top
            ) + fadeIn(
                initialAlpha = 0.3f
            ),
            exit = slideOutVertically(
                animationSpec = tween(durationMillis = 1000)
            ) + shrinkVertically() + fadeOut()
        ) {
            HomeScreenTopAppBar(
                state = uiState,
                onEvent = viewModel::onEvent
            )
        }

        PhotoSection(
            uiState,
            viewModel::onEvent,
            navController,
            viewModel.snackBarEventFlow,
            snackBarState
        )

    }
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun BottomSheet(
    uiState: HomeData,
    onEvent: (HomeEvent) -> Unit,
) {

    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onEvent(HomeEvent.OnBottomSheetDismissClick) },
        sheetState = sheetState
    ) {
        when (uiState.selectedChip) {
            SelectedChip.SUNLIGHT -> {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        onClick = { onEvent(HomeEvent.OnSunLightImageClick(sunlight = Sunlight.FULL_SUN)) }
                    ) {
                        Text(text = "FULL SUNLIGHT")
                    }
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        onClick = { onEvent(HomeEvent.OnSunLightImageClick(sunlight = Sunlight.SUN_PART_SHADE)) }
                    ) {
                        Text(text = "SUN_PART_SHADE")
                    }
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        onClick = { onEvent(HomeEvent.OnSunLightImageClick(sunlight = Sunlight.FULL_SHADE)) }
                    ) {
                        Text(text = "FULL SHADE")
                    }
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        onClick = { onEvent(HomeEvent.OnSunLightImageClick(sunlight = Sunlight.PART_SHADE)) }
                    ) {
                        Text(text = "PART SUNLIGHT")
                    }
                }
            }

            SelectedChip.CYCLE -> {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        onClick = { onEvent(HomeEvent.OnCycleImageClick(cycle = Cycle.ANNUAL)) }
                    ) {
                        Text(text = "ANNUAL")
                    }
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        onClick = { onEvent(HomeEvent.OnCycleImageClick(cycle = Cycle.BIANNUAL)) }
                    ) {
                        Text(text = "BIANNUAL")
                    }
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        onClick = { onEvent(HomeEvent.OnCycleImageClick(cycle = Cycle.BIENNIAL)) }
                    ) {
                        Text(text = "BIENNIAL")
                    }
                    Button(modifier = Modifier
                        .fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        onClick = { onEvent(HomeEvent.OnCycleImageClick(cycle = Cycle.PERENNIAL)) }
                    ) {
                        Text(text = "PERENNIAL")
                    }
                }
            }

            SelectedChip.WATERING -> {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        onClick = { onEvent(HomeEvent.OnWaterImageClick(watering = Watering.FREQUENT)) }
                    ) {
                        Text(text = "FREQUENT")
                    }
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        onClick = { onEvent(HomeEvent.OnWaterImageClick(watering = Watering.AVERAGE)) }
                    ) {
                        Text(text = "AVERAGE")
                    }
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        onClick = { onEvent(HomeEvent.OnWaterImageClick(watering = Watering.MINIMUM)) }
                    ) {
                        Text(text = "MINIMUM")
                    }
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            onEvent(HomeEvent.OnWaterImageClick(watering = Watering.NONE))
                        }
                    ) {
                        Text(text = "NONE")
                    }
                }
            }

            SelectedChip.TEMPERATURE -> {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                ) {
                    RangeSlider(
                        value = uiState.sliderPosition,
                        steps = 12,
                        onValueChange = { onEvent(HomeEvent.OnSliderValueChange(range = it)) },
                        valueRange = 1f..13f,
                        onValueChangeFinished = { onEvent(HomeEvent.OnSliderValueChangeFinished) },
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = uiState.startRange.toString())
                        Text(text = uiState.endRange.toString())
                    }
                }
            }
        }


    }
}

@Composable
private fun PhotoSection(
    uiState: HomeData,
    onEvent: (HomeEvent) -> Unit,
    navController: NavHostController,
    snackBarEventFlow: SharedFlow<SnackBarEvent>,
    snackBarState: SnackbarHostState
) {

    LaunchedEffect(key1 = true) {
        snackBarEventFlow.collectLatest { event ->
            snackBarState.showSnackbar(
                message = event.message,
                duration = event.duration
            )
        }
    }


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(top = 20.dp),
    ) {
        itemsIndexed(uiState.plantData) { index, data ->
            LaunchedEffect(key1 = index) {
                onEvent(HomeEvent.OnCountIndex(index = index))
                Log.d(PLANTONIAM_LOGS, uiState.index.toString())
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    onClick = { navController.navigate(Route.ImageScreenNavigation(data.id.toString())) },
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                ) {

                    val requestImage = ImageRequest.Builder(LocalContext.current)
                        .data(data.defaultImage?.regularUrl)
                        .crossfade(true)
                        .build()

                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize(),
                        model = requestImage,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )


                }
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = data.commonName ?: "",
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = { onEvent(HomeEvent.OnLeftArrowButtonClick(uiState.plantList?.currentPage)) },
                ) {
                    Icon(
                        modifier = Modifier
                            .size(60.dp)
                            ,
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null
                    )
                }
                Text(
                    text = "Pa",
                    style = MaterialTheme.typography.titleLarge
                )

            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "ge:-${uiState.plantList?.currentPage}",
                    style = MaterialTheme.typography.titleLarge
                )
                IconButton(onClick = { onEvent(HomeEvent.OnRightArrowButtonClick(uiState.plantList?.currentPage)) }) {
                    Icon(
                        modifier = Modifier
                            .size(60.dp),
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null
                    )
                }
            }
        }

    }


}


@Composable
fun HomeScreenTopAppBar(
    state: HomeData,
    onEvent: (HomeEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.45f),
        contentAlignment = Alignment.TopStart
    ) {
        CanvasBackGround(
            color = MaterialTheme.colorScheme.onBackground,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(bottom = 100.dp)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {

            Column1(
                state = state,
                onEvent = onEvent
            )

            Column2(
                state = state,
                onEvent = onEvent
            )
        }

        Text(
            text = "${state.toxicImage}", color = Color.Black,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 80.dp)
        )


        IconButton(
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.BottomCenter),
            onClick = { onEvent(HomeEvent.OnToxicImageClick(state.toxicImage)) }
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = state.toxicImage.image),
                contentDescription = null
            )
        }


    }
}


@Composable
private fun Column1(
    state: HomeData,
    onEvent: (HomeEvent) -> Unit
) {


    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        FilterBarImages(
            image = state.edibleImage,
            onImageClick = { onEvent(HomeEvent.OnEdibleImageClick(it)) },
            text = "${state.edibleImage}"
        )
        FilterBarImages(
            image = state.placeImage,
            onImageClick = { onEvent(HomeEvent.OnPlaceImageClick(it)) },
            text = "${state.placeImage}"
        )
        FilterBarImages(
            image = state.temperatureImage,
            onImageClick = {
                onEvent(HomeEvent.OnSelectedChipClick(SelectedChip.TEMPERATURE))
                onEvent(HomeEvent.OnBottomSheetClick)
            },
            text = "tempreature"
        )
    }
}

@Composable
fun Column2(
    state: HomeData,
    onEvent: (HomeEvent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        FilterBarImages(
            image = state.cycleImage,
            onImageClick = {
                onEvent(HomeEvent.OnSelectedChipClick(SelectedChip.CYCLE))
                onEvent(HomeEvent.OnBottomSheetClick)
            },
            text = "cycle"
        )
        FilterBarImages(
            image = state.sunLightImage,
            onImageClick = {
                onEvent(HomeEvent.OnSelectedChipClick(SelectedChip.SUNLIGHT))
                onEvent(HomeEvent.OnBottomSheetClick)
            },
            text = "sunlight"
        )
        FilterBarImages(
            image = state.waterImage,
            onImageClick = {
                onEvent(HomeEvent.OnSelectedChipClick(SelectedChip.WATERING))
                onEvent(HomeEvent.OnBottomSheetClick)
            },
            text = "watering"
        )
    }
}

@Composable
private fun FilterBarImages(
    image: FilterBarPictureComponents,
    onImageClick: (FilterBarPictureComponents) -> Unit,
    text: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(
            modifier = Modifier

                .size(60.dp),
            onClick = { onImageClick(image) }
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = image.image),
                contentDescription = null
            )

        }
        Text(text = text, color = Color.Black)
    }
}


