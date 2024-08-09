package com.example.plantoniam.ui.imageScreen

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageScreen(navController: NavHostController) {

    val viewModel: ImageViewModel = hiltViewModel()

    val uiState by viewModel.state.collectAsStateWithLifecycle()

    val request = ImageRequest.Builder(context = LocalContext.current)
        .data(data = uiState.plantDetail?.defaultImage?.regularUrl)
        .crossfade(true)
        .build()

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

            ImageScreenTopBar (
                onBackButtonClick = {navController.popBackStack()}
            )
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1 / 2.5f),
                model = request,
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Common Name :- ${uiState.plantDetail?.commonName}",
                style = MaterialTheme.typography.titleLarge
            )
            if (uiState.plantDetail?.scientificName?.isNotEmpty() == true) {
                Text(
                    text = "Scientific Name",
                    style = MaterialTheme.typography.titleLarge
                )
                LazyRow {
                    items(uiState.plantDetail!!.otherName) { element ->
                        ElevatedAssistChip(
                            modifier = Modifier
                                .padding(end = 15.dp),
                            onClick = { },
                            label = { Text(text = element ?: "empty") }
                        )
                    }

                }
            }
            if (uiState.otherName.isNotEmpty()) {
                Text(
                    text = "Other Name",
                    style = MaterialTheme.typography.titleLarge
                )
                LazyRow {
                    items(uiState.plantDetail!!.otherName) { element ->
                        ElevatedAssistChip(
                            modifier = Modifier
                                .padding(end = 15.dp),
                            onClick = { /*TODO*/ },
                            label = { Text(text = element ?: "empty") }
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
            if (uiState.plantDetail?.sunlight?.isNotEmpty() == true) {
                Text(
                    text = "Sunlight",
                    style = MaterialTheme.typography.titleLarge
                )
                LazyRow {
                    items(uiState.plantDetail!!.sunlight) { element ->
                        ElevatedAssistChip(
                            modifier = Modifier
                                .padding(end = 15.dp),
                            onClick = { /*TODO*/ },
                            label = { Text(text = element ?: "empty") }
                        )
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ImageScreenTopBar(modifier: Modifier = Modifier, onBackButtonClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = "PLANTONIAM") },
        navigationIcon = {
            IconButton(onClick = onBackButtonClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )
}