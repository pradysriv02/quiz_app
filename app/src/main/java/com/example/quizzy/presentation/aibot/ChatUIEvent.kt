package com.example.quizzy.presentation.aibot

import android.graphics.Bitmap

open class ChatUIEvent{
    data class UpdatePrompt(val newPrompt: String) : ChatUIEvent()
    data class SendPrompt(val prompt:String,val bitmap: Bitmap?) : ChatUIEvent()
}