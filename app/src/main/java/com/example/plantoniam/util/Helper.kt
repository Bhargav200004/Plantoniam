package com.example.plantoniam.util

import androidx.core.util.toRange
import com.example.plantoniam.util.Constant.BASEURL


fun addingParameterAtEnd(
    getMethods: String
): String {
    return BASEURL + getMethods + "?key=${Constant.APIKEY}"
}

