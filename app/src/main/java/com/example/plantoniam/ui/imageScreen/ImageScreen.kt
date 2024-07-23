package com.example.plantoniam.ui.imageScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.plantoniam.R

@Composable
fun ImageScreen() {

    val viewModel: ImageViewModel = viewModel()

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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1 / 2.5f),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Text(text = "Common Name :- Moosewood")
            Text(text = "scientific Name :- Acer pensylvanicum")
            if (otherName.isNotEmpty()) {
                Text(text = "Other Name")
                LazyRow {
                    items(otherName) { element ->
                        ElevatedAssistChip(
                            onClick = { /*TODO*/ },
                            label = { Text(text = element) }
                        )
                    }

                }
            }
            Text(text = "cycle: Perennial")
            Text(text = "watering: Average")
            if (otherName.isNotEmpty()) {
                Text(text = "Sunlight")
                LazyRow {
                    items(sunlight) { element ->
                        ElevatedAssistChip(
                            onClick = { /*TODO*/ },
                            label = { Text(text = element) }
                        )
                    }
                }
            }
        }
    }
}