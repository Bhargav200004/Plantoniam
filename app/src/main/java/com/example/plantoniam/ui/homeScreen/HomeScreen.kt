package com.example.plantoniam.ui.homeScreen

import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.plantoniam.R
import com.example.plantoniam.ui.components.CanvasBackGround
import com.example.plantoniam.util.Constant.PLANTORIAM_LOGS

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    HomeScreenTopAppBar(
        onImageClick = { image ->
            Log.d(PLANTORIAM_LOGS , image.toString())
        }
    )

}


@Composable
fun HomeScreenTopAppBar(
    onImageClick: (FilterBarPictureComponents) -> Unit
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

            Column1(onImageClick = onImageClick)

            Column2(onImageClick = onImageClick)
        }
        IconButton(
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.BottomCenter),
            onClick = {onImageClick(FilterBarPictureComponents.TOXIC)}
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = FilterBarPictureComponents.TOXIC.image),
                contentDescription = null
            )
        }

    }
}


@Composable
private fun Column1(
    onImageClick: (FilterBarPictureComponents) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        FilterBarImages(
            image = FilterBarPictureComponents.EDIBLE,
            onImageClick = {onImageClick(FilterBarPictureComponents.EDIBLE)}
        )
        FilterBarImages(
            image = FilterBarPictureComponents.OUTDOOR,
            onImageClick = {onImageClick(FilterBarPictureComponents.OUTDOOR)}
        )
        FilterBarImages(
            image = FilterBarPictureComponents.TIME,
            onImageClick = { onImageClick(FilterBarPictureComponents.TIME) }
        )
    }
}

@Composable
fun Column2(
    onImageClick: (FilterBarPictureComponents) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        FilterBarImages(
            image = FilterBarPictureComponents.CYCLE,
            onImageClick = {onImageClick(FilterBarPictureComponents.CYCLE)}
        )
        FilterBarImages(
            image = FilterBarPictureComponents.SUNLIGHT,
            onImageClick = { onImageClick(FilterBarPictureComponents.SUNLIGHT)}
        )
        FilterBarImages(
            image = FilterBarPictureComponents.WATERING,
            onImageClick = { onImageClick(FilterBarPictureComponents.WATERING)}
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


