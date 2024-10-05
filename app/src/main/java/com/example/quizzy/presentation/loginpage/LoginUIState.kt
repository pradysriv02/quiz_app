package com.example.lockerroom.data

data class SignInUIState(
    val email:String = "",
    val password:String ="",

    val errorEmail: Boolean = false,
    val errorPassword: Boolean = false
)