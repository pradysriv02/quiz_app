package com.example.lockerroom.data.rules

object RegistrationValidator {

    fun validateFirstName(fName:String): ValidationResult{
        return ValidationResult(
            (!fName.isNullOrEmpty() && fName.length>=2)
        )
    }
    fun validateSecondName(sName:String): ValidationResult{
        return ValidationResult(
            (!sName.isNullOrEmpty() && sName.length>=2)
        )
    }
    fun validateEmail(email:String): ValidationResult{
        return ValidationResult(
            (!email.isNullOrEmpty())
        )
    }
    fun validatePassword(password:String): ValidationResult{
        return ValidationResult(
            (!password.isNullOrEmpty() && password.length>=4)
        )
    }

}

data class ValidationResult(
    val status: Boolean = false
)