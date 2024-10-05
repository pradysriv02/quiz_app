package com.example.lockerroom.data

data class RegistrationUIState(
    var firstName: String = "",
    var secondName: String = "",
    var email: String = "",
    var password: String = "",

    var errorFirstName:Boolean = false,
    var errorSecondName:Boolean = false,
    var errorEmail:Boolean = false,
    var errorPassword:Boolean = false,
)