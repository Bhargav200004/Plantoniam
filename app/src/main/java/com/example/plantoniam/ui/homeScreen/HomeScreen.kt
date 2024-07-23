package com.example.plantoniam.ui.homeScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {

    val viewModel: HomeViewModel = hiltViewModel()

    val uiState by viewModel.state.collectAsStateWithLifecycle()

    val density = LocalDensity.current

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {

        AnimatedVisibility(
            visible = uiState.isTopBarShowing,
            enter = slideInVertically {
                with( density) { -40.dp.roundToPx() }
            } + expandVertically(
                expandFrom = Alignment.Top
            ) + fadeIn(
                initialAlpha = 0.3f
            ) ,
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        )   {
            HomeScreenTopAppBar(
                state = uiState,
                onEvent = viewModel::onEvent
            )
        }

        PhotoSection(
            uiState,
            viewModel::onEvent,
            navController
        )

    }

}

@Composable
private fun PhotoSection(
    uiState: HomeData,
    onEvent: (HomeEvent) -> Unit,
    navController: NavHostController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp),
    ) {

        uiState.plantList?.let { plantList ->
            itemsIndexed(plantList.data) { index, data ->
                LaunchedEffect(key1 = index) {
                    onEvent(HomeEvent.OnCountIndex(index = index))
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
                            .height(200.dp)
                            ,
                        onClick = { navController.navigate(Route.ImageScreenNavigation(data.id.toString())) },
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                    ) {

                        val requestImage = ImageRequest.Builder(LocalContext.current)
                            .data(null) //data.defaultImage?.regularUrl
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
                        text = data.commonName,
                        fontSize = 18.sp,
                        color = Color.White
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
            onImageClick = { onEvent(HomeEvent.OnEdibleImageClick(it)) }
        )
        FilterBarImages(
            image = state.placeImage,
            onImageClick = { onEvent(HomeEvent.OnPlaceImageClick(it)) }
        )
        FilterBarImages(
            image = state.timeImage,
            onImageClick = { onEvent(HomeEvent.OnClockImageClick(it)) }
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
            onImageClick = { onEvent(HomeEvent.OnCycleImageClick(it)) }
        )
        FilterBarImages(
            image = state.sunLightImage,
            onImageClick = { onEvent(HomeEvent.OnSunLightImageClick(it)) }
        )
        FilterBarImages(
            image = state.waterImage,
            onImageClick = { onEvent(HomeEvent.OnWaterImageClick(it)) }
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


