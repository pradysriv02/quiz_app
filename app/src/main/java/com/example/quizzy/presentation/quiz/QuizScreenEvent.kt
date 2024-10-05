package com.example.quizzy.presentation.quiz

sealed class QuizScreenEvent{

    data class GetQuizzes(val numOfQuestions:Int,val category:Int,val difficulty:String, val type:String) : QuizScreenEvent()

    data class SetOptionSelected(val quizStateIndex : Int,val selectedOption : Int) : QuizScreenEvent()
}