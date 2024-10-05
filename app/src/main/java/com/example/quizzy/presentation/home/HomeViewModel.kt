package com.example.quizzy.presentation.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel : ViewModel() {


    private val _homeState = MutableStateFlow(HomeScreenState())
    val homeState = _homeState

    fun onEvent(event: HomeScreenEvent){
        when(event){
            is HomeScreenEvent.SetNumberOfQuestions -> {
                _homeState.value = homeState.value.copy(
                    numberOfQuestions = event.numberOfQuestions
                )
            }
            is HomeScreenEvent.SetSelectCategory -> {
                _homeState.value = homeState.value.copy(
                    quizCategory = event.category
                )
            }
            is HomeScreenEvent.SetDifficulty -> {
                _homeState.value = homeState.value.copy(
                    difficulty = event.difficulty
                )
            }
            is HomeScreenEvent.SetSelectType -> {
                _homeState.value = homeState.value.copy(
                    type = event.type
                )
            }
            else -> {}
        }
    }
}