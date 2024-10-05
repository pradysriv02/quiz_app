package com.example.lockerroom.data

import android.media.metrics.Event
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lockerroom.data.rules.RegistrationValidator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SigninViewModel:ViewModel() {
    var signInUIState = mutableStateOf(SignInUIState())
    var allValidationsPassed = mutableStateOf(false)
    var signInProgress = mutableStateOf(false)
    var name = ""
    sealed class AuthState{
        object Authenticated : AuthState()
        object Unauthenticated : AuthState()
    }
    private val _authState =  MutableLiveData<AuthState>()
    val authState : LiveData<AuthState> = _authState
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    val db = Firebase.firestore
    init {
        checkAuthStatus()
    }
    fun checkAuthStatus(){
        if(auth.currentUser==null){
            _authState.value=AuthState.Unauthenticated
        }
        else _authState.value=AuthState.Authenticated
    }
    private val TAG = SigninViewModel::class.simpleName
    fun onEvent(event: SignInUIEvent){
        when(event){
            is SignInUIEvent.EmailChanged -> {
                signInUIState.value = signInUIState.value.copy(
                    email = event.email
                )
            }
            is SignInUIEvent.PasswordChanged ->{
                signInUIState.value = signInUIState.value.copy(
                    password = event.password
                )
            }
            is SignInUIEvent.SignInButtonClicked ->{
                login(email = signInUIState.value.email, password = signInUIState.value.password)
            }
        }
        validateDataWithRules()
    }
    val emailForScreen = signInUIState.value.email

    private fun validateDataWithRules() {

        val emailResult = RegistrationValidator.validateEmail(email = signInUIState.value.email)

        val passwordResult = RegistrationValidator.validatePassword(password = signInUIState.value.password)

        signInUIState.value = signInUIState.value.copy(
            errorEmail = emailResult.status,
            errorPassword = passwordResult.status
        )

        if(emailResult.status && passwordResult.status){
            allValidationsPassed.value = true
        }
        else allValidationsPassed.value = false
    }
    private fun login(email:String,password:String){
         auth
            .signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    _authState.value=AuthState.Authenticated
                    Log.d("success", "login")
                }
            }
            .addOnFailureListener {
                Log.d("failed", "login: ${it.localizedMessage}")
            }
    }
}
