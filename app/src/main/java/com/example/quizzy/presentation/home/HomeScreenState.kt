package com.example.quizzy.presentation.home

data class HomeScreenState(
    val numberOfQuestions : Int = 10,
    val quizCategory : String = "General Knowledge",
    val difficulty : String = "Easy",
    val type : String = "Multiple Choice"
)
