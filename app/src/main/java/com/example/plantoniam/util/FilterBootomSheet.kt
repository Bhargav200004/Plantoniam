package com.example.plantoniam.util

enum class Sunlight(val value : String){
    FULL_SHADE("full_shade"),
    PART_SHADE("part_shade"),
    SUN_PART_SHADE("sun-part_shade"),
    FULL_SUN("full_sun")
}

enum class Cycle(val value: String){
    PERENNIAL("perennial"),
    ANNUAL("annual"),
    BIENNIAL("biennial"),
    BIANNUAL("biannual")
}

enum class Watering(val value : String){
    FREQUENT("frequent"),
    AVERAGE("average"),
    MINIMUM("minimum"),
    NONE("none")
}