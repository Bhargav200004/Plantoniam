package com.example.plantoniam.ui.imageScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.plantoniam.R

@Composable
fun ImageScreen(navController: NavHostController) {

    val viewModel: ImageViewModel = viewModel()

//    val uiState by viewModel.state.collectAsStateWithLifecycle()

    Text(text = viewModel.imageId )

    val otherName: List<String> = listOf(
        "Striped Maple",
        "Snakebark Maple",
        "Moose Maple",
        "Whistlewood",
        "Goosefoot Maple"
    )
    val sunlight: List<String> = listOf(
        "full sun",
        "part shade"
    )
    val scientificName : List<String> = listOf(

    )


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1 / 2.5f),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Common Name :- Moosewood",
                style = MaterialTheme.typography.titleLarge
            )
            if (scientificName.isNotEmpty()) {
                Text(
                    text = "Scientific Name",
                    style = MaterialTheme.typography.titleLarge
                )
                LazyRow {
                    items(otherName) { element ->
                        ElevatedAssistChip(
                            modifier = Modifier
                                .padding(end = 15.dp),
                            onClick = { /*TODO*/ },
                            label = { Text(text = element) }
                        )
                    }

                }
            }
            if (otherName.isNotEmpty()) {
                Text(
                    text = "Other Name",
                    style = MaterialTheme.typography.titleLarge
                )
                LazyRow {
                    items(otherName) { element ->
                        ElevatedAssistChip(
                            modifier = Modifier
                                .padding(end = 15.dp),
                            onClick = { /*TODO*/ },
                            label = { Text(text = element) }
                        )
                    }

                }
            }
            Text(
                text = "cycle: Perennial",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "watering: Average",
                style = MaterialTheme.typography.titleLarge
            )
            if (sunlight.isNotEmpty()) {
                Text(
                    text = "Sunlight",
                    style = MaterialTheme.typography.titleLarge
                )
                LazyRow{
                    items(sunlight) { element ->
                        ElevatedAssistChip(
                            modifier = Modifier
                                .padding(end = 15.dp),
                            onClick = { /*TODO*/ },
                            label = { Text(text = element) }
                        )
                    }
                }
            }
        }
    }
}