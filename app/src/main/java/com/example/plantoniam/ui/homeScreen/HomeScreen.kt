package com.example.plantoniam.ui.homeScreen

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.plantoniam.R
import com.example.plantoniam.ui.components.CanvasBackGround

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.45f)
                .padding(top = 40.dp),
            contentAlignment = Alignment.TopStart
        ) {
            CanvasBackGround(
                color = Color.Blue,
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
                repeat(3) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        repeat(3) {
                            IconButton(
                                modifier = Modifier
                                    .size(60.dp),
                                onClick = { /*TODO*/ }
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.co2),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
            IconButton(
                modifier = Modifier
                    .size(70.dp)
                    .align(Alignment.BottomCenter),
                onClick = { /*TODO*/ }
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(id = R.drawable.non_toxic),
                    contentDescription = null
                )
            }


        }

}


