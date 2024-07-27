package com.example.plantoniam.ui.homeScreen

import com.example.plantoniam.R

enum class FilterBarPictureComponents(val image : Int){
    CYCLE(image = R.drawable.icons8_cycle_64),
    EDIBLE(image = R.drawable.icons8_edible_64),
    NON_EDIBLE(image = R.drawable.icons8_inedible_64),
    OUTDOOR(image = R.drawable.icons8_field_64),
    INDORE(image = R.drawable.icons8_indoor_64 ),
    SUNLIGHT(image = R.drawable.icons8_sunlight_64),
    TEMPERATURE(image = R.drawable.icons_temperature_64),
    WATERING(image = R.drawable.icons8_watering_can_64),
    TOXIC(image = R.drawable.poisonous),
    NON_TOXIC(image = R.drawable.non_toxic)
}

enum class SelectedChip{
    SUNLIGHT,
    CYCLE,
    WATERING,
    TEMPERATURE
}