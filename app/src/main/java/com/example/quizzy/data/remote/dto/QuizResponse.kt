package com.example.quizzy.data.remote.dto

import com.example.quizzy.domain.model.Quiz

data class QuizResponse(
    val response_code: Int,
    val results: List<Quiz>
)