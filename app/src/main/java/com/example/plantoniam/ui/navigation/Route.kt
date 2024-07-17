package com.example.plantoniam.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {

    @Serializable
    data object HomeScreenNavigation : Route()

    @Serializable
    data class ImageScreenNavigation(val id : String) : Route()

}