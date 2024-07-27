package com.example.plantoniam.util

import com.example.plantoniam.util.Constant.BASEURL


fun addingParameterAtEnd(
    getMethods: String
): String {
    return BASEURL + getMethods + "?key=${Constant.APIKEY}"
}

fun ClosedFloatingPointRange<Float>.toInt() : Int {
    return this.toInt()
}
