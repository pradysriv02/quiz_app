package com.example.quizzy.presentation.home

sealed class HomeScreenEvent {

    data class SetNumberOfQuestions(val numberOfQuestions: Int) : HomeScreenEvent()
    data class SetSelectCategory(val category: String) : HomeScreenEvent()
    data class SetDifficulty(val difficulty: String) : HomeScreenEvent()
    data class SetSelectType(val type: String) : HomeScreenEvent()
}