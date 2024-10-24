package com.example.quizzy.data.repository

import com.example.quizzy.data.remote.QuizApi
import com.example.quizzy.domain.model.Quiz
import com.example.quizzy.domain.repository.QuizRepository

class QuizRepositoryImpl(
    private val quizApi:QuizApi
) : QuizRepository{
    override suspend fun getQuizzes(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ): List<Quiz> {
        return quizApi.getQuizzes(amount, category, difficulty, type).results
    }

}