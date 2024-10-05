package com.example.quizzy.presentation.googlesignin

data class SignInResult(
    val data : UserData?,
    val errorMessage : String?
)

data class UserData(
    val userId : String,
    val firstName : String?,
    val lastName : String?,
)
