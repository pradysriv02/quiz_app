package com.example.quizzy.domain.repository

import com.example.quizzy.domain.model.Quiz

interface QuizRepository {

    suspend fun getQuizzes(amount:Int,category:Int,difficulty:String,type:String) : List<Quiz>
}