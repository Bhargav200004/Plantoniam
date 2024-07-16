package com.example.plantoniam.ui.homeScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.plantoniam.ui.components.CanvasBackGround

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val viewModel : HomeViewModel = viewModel()

    val uiState by viewModel.state.collectAsStateWithLifecycle()


    HomeScreenTopAppBar(
        state = uiState,
        onEvent = viewModel::onEvent
    )

}


@Composable
fun HomeScreenTopAppBar(
    state : HomeData,
    onEvent: (HomeEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.45f)
            .padding(top = 40.dp),
        contentAlignment = Alignment.TopStart
    ) {

        CanvasBackGround(
            color = MaterialTheme.colorScheme.primary,
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
        IconButton(
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.BottomCenter),
            onClick = {onEvent(HomeEvent.OnPoisonousImageClick(state.toxicImage))}
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
    state : HomeData,
    onEvent: (HomeEvent) -> Unit
) {

    var isEdible by rememberSaveable { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        AnimatedVisibility(visible = isEdible) {
            FilterBarImages(
                image = state.edibleImage,
                onImageClick = {onEvent(HomeEvent.OnEdibleImageClick(it))}
            )
            isEdible = false
        }
        FilterBarImages(
            image = state.placeImage,
            onImageClick = {onEvent(HomeEvent.OnPlaceImageClick(it))}
        )
        FilterBarImages(
            image = state.timeImage,
            onImageClick = { onEvent(HomeEvent.OnClockImageClick(it)) }
        )
    }
}

@Composable
fun Column2(
    state : HomeData,
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
            onImageClick = {onEvent(HomeEvent.OnCycleImageClick(it))}
        )
        FilterBarImages(
            image = state.sunLightImage,
            onImageClick = { onEvent(HomeEvent.OnSunLightImageClick(it))}
        )
        FilterBarImages(
            image = state.waterImage,
            onImageClick = { onEvent(HomeEvent.OnWaterImageClick(it))}
        )
    }
}

@Composable
private fun FilterBarImages(
    image: FilterBarPictureComponents,
    onImageClick: (FilterBarPictureComponents) -> Unit
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
}


