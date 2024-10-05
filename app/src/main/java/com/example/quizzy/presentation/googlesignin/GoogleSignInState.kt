package com.example.quizzy.presentation.googlesignin

data class GoogleSignInState(
    val isSignInSuccessfull : Boolean = false,
    val signInError : String? = null
)
