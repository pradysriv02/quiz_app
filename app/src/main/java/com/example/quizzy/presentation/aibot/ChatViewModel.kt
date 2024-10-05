package com.example.quizzy.presentation.aibot

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val _chatState = MutableStateFlow(ChatState())
    val chatState = _chatState.asStateFlow()

    fun onEvent(event : ChatUIEvent){
        when(event){
            is ChatUIEvent.SendPrompt -> {
                if(event.prompt.isNotEmpty()){
                    addPrompt(event.prompt)

                    if(event.bitmap==null){
                        getResponse(event.prompt)
                    }
                    else getResponseWithImage(event.prompt,event.bitmap)

                }
            }
            is ChatUIEvent.UpdatePrompt -> {
                _chatState.update {
                    it.copy(prompt = event.newPrompt)
                }
            }
        }
    }

    private fun addPrompt(prompt:String){
        _chatState.update {
            it.copy(
                it.chatList.toMutableList().apply {
                    add(0,Chat(prompt,null,true))
                },
                prompt="",
                image =null
            )
        }
    }

    private fun getResponse(prompt: String){
        viewModelScope.launch {
            val chat = ChatData.getResponse(prompt)
            _chatState.update {
                it.copy(
                    it.chatList.toMutableList().apply {
                        add(0,chat)
                    }
                )
            }
        }
    }

    private fun getResponseWithImage(prompt: String,bitmap: Bitmap){
        viewModelScope.launch {
            val chat = ChatData.getResponseWithImage(prompt,bitmap)
            _chatState.update {
                it.copy(
                    it.chatList.toMutableList().apply {
                        add(0,chat)
                    }
                )
            }
        }
    }
}