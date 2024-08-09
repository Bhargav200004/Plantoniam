package com.example.plantoniam.ui.imageScreen

import com.example.plantoniam.domain.models.plantDetail.PlantDetail

data class ImageData(
    val plantDetail : PlantDetail? = null,
    val otherName : List<String?> = emptyList()
)
