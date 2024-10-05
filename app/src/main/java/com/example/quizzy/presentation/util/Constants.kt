package com.example.quizzy.presentation.util

object Constants {
    val numbersAsString = listOf("10")+(1 until  50).map{it.toString()}
    val categories = listOf(
        "General Knowledge",
        "Science and Nature",
        "Science: Computers",
        "Science: Mathematics",
        "Sports",
        "Science: Gadgets"
    )

    val categoriesMap = mapOf(
        "General Knowledge" to 9,
        "Science and Nature" to 17,
        "Science: Computers" to 18,
        "Science: Mathematics" to 19,
        "Sports" to 21,
        "Science: Gadgets" to 30
    )

    val difficulty = listOf("Easy","Medium","Hard")
    val type = listOf("Multiple Choice","TrueORFalse")
}