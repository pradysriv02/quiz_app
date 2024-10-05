package com.example.quizzy.presentation.aibot

import android.graphics.Bitmap


data class ChatState(
    val chatList: MutableList<Chat> = mutableListOf(),
    val prompt: String = "",
    val image: Bitmap? = null
)