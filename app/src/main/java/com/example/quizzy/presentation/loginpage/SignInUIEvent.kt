package com.example.lockerroom.data

open class SignInUIEvent {
    data class EmailChanged(val email:String):SignInUIEvent()
    data class PasswordChanged(val password:String) :SignInUIEvent()

    object SignInButtonClicked: SignInUIEvent()
}