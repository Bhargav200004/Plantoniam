package com.example.plantoniam.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.translate

@Composable
fun CanvasBackGround(
    modifier: Modifier = Modifier,
    color: Color,
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height  - 200f


        val filterBarPath = Path().apply {
            moveTo(
                x = 0f,
                y = 0f
            )
            lineTo(
                x = 0f,
                y = canvasHeight / 1.5f
            )
            quadraticTo(
                x1 = canvasWidth * 0.1f,
                y1 = canvasHeight * 0.89f,
                x2 = canvasWidth * 0.5f - 120f,
                y2 = canvasHeight
            )
            lineTo(
                x = canvasWidth * 0.5f - 100f,
                y = canvasHeight
            )
            lineTo(
                x = canvasWidth * 0.5f + 100f,
                y = canvasHeight
            )
            quadraticTo(
                x1 = canvasWidth * 0.89f ,
                y1 = canvasHeight * 0.9f,
                x2 = canvasWidth,
                y2 = canvasHeight / 1.5f
            )
            lineTo(
                x = canvasWidth,
                y = canvasHeight / 1.5f
            )
            lineTo(
                x = canvasWidth,
                y = 0f
            )
            close()
        }

        translate(
            top = canvasHeight  / 2f
        ) {
            drawCircle(
                color = color,
                radius = 160f,
                center = center
            )
        }

        clipPath(
            path = filterBarPath,
        ) {
            drawRect(color = color, size = size)
        }
    }

}






