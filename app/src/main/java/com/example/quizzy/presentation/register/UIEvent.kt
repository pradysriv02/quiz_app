package com.example.lockerroom.data



open class UIEvent {
    data class FirstNameChanged(val firstName:String) : UIEvent()
    data class SecondNameChanged(val secondName:String) : UIEvent()
    data class EmailChanged(val email: String) : UIEvent()
    data class PasswordChanged(val password: String) : UIEvent()

    object RegisterButtonClicked : UIEvent()
}