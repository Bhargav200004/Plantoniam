package com.example.plantoniam.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.dp

@Composable
fun CustomFilterBar(
    modifier: Modifier = Modifier,
    color: Color
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height
        translate(top = width - height / 2.4f) {
            scale(scaleX = 1f , scaleY = 0.8f) {
                drawArc(
                    color = color,
                    startAngle =  180f,
                    sweepAngle = -180f,
                    useCenter = false,
                    size = Size(size.width, size.height / 5)
                )
            }
        }
        drawRect(
            color = color,
            size = Size(width = width , height = height / 4f)
        )
        translate (
            top = width - height / 1.52f
        ){
            scale(scaleX = 1f , scaleY = 0.9f){
                drawCircle(color = color , radius = 170f)
            }
        }
    }
}


