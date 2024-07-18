package com.example.plantoniam.util

class helper {
    fun removeQuotesAndUnescape(uncleanJson: String): String {
        val noQuotes = uncleanJson.replace("^\"|\"$".toRegex(), "")

        return noQuotes
    }

}