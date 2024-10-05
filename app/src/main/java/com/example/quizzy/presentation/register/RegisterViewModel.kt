package com.example.quizzy.presentation.register

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lockerroom.data.RegistrationUIState
import com.example.lockerroom.data.UIEvent
import com.example.lockerroom.data.rules.RegistrationValidator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SignUpViewModel : ViewModel() {
    var allValidationsPassed = mutableStateOf(false)
    var signUpInProgress = mutableStateOf(false)
    var registrationUIState = mutableStateOf(RegistrationUIState())


    sealed class AuthState{
        object Authenticated : AuthState()
        object Unauthenticated : AuthState()
    }

    private val _authState =  MutableLiveData<AuthState>()
    val authState : LiveData<AuthState> = _authState
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    init {
        checkAuthStatus()
        getUserData()
    }
    fun checkAuthStatus(){
        if(auth.currentUser==null){
            _authState.value=AuthState.Unauthenticated
        }
        else _authState.value=AuthState.Authenticated
    }

    private val TAG = SignUpViewModel::class.simpleName
    fun onEvent(event: UIEvent){
        when(event){
            is UIEvent.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    firstName = event.firstName
                )
                validateDataWithRules()
                printState()
            }

            is UIEvent.SecondNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    secondName = event.secondName
                )
                validateDataWithRules()
            }
            is UIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                validateDataWithRules()
            }
            is UIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
                validateDataWithRules()
            }
            is UIEvent.RegisterButtonClicked -> {
                signUp()
            }
        }
    }
    private fun printState(){
        Log.d(TAG, registrationUIState.value.toString())
    }

    private fun signUp(){
        printState()
        createUserInFirebase(email = registrationUIState.value.email, password = registrationUIState.value.password)
    }

    private fun validateDataWithRules() {
        val firstNameResult = RegistrationValidator.validateFirstName(fName = registrationUIState.value.firstName)

        val secondNameResult = RegistrationValidator.validateSecondName(sName = registrationUIState.value.secondName)

        val emailResult = RegistrationValidator.validateEmail(email = registrationUIState.value.email)

        val passwordResult = RegistrationValidator.validatePassword(password = registrationUIState.value.password)

        registrationUIState.value = registrationUIState.value.copy(
            errorFirstName = firstNameResult.status,
            errorSecondName = secondNameResult.status,
            errorEmail = emailResult.status,
            errorPassword = passwordResult.status
        )

        if(firstNameResult.status && secondNameResult.status && emailResult.status && passwordResult.status){
            allValidationsPassed.value = true
        }
        else allValidationsPassed.value = false
    }

    private fun createUserInFirebase(email:String,password:String){
        signUpInProgress.value = true
        FirebaseAuth.
        getInstance()
            .createUserWithEmailAndPassword(email.trim(),password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside create")
                Log.d(TAG, "createUserInFirebase: ${it.isSuccessful}")
                if(it.isSuccessful) {
                    _authState.value=AuthState.Authenticated
                    Log.d("Reg","Success")
                    val db = Firebase.firestore

                    val storedUser = hashMapOf(
                        "firstName" to registrationUIState.value.firstName,
                        "secondName" to registrationUIState.value.secondName,
                        "email" to registrationUIState.value.email
                    )
                    var document = ""
                    db.collection("storedUsers")
                        .add(storedUser)
                        .addOnSuccessListener { documentReference ->
                            Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                            getUserData()
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error adding document", e)
                        }
                }
                signUpInProgress.value=false
            }
            .addOnFailureListener{
                Log.d(TAG, "createUserInFirebase: ${it.localizedMessage}")
            }
        signUpInProgress.value = false
    }
     fun getUserData(){
         var name = mutableStateOf("Hehehe")
        val db = Firebase.firestore
        val query = db.collection("storedUsers").whereEqualTo("email",
            registrationUIState.value.email
        )
        query.get().addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot.documents) {
                name.value = document.getString("firstName").toString()
                Log.d("Firestorm", "Document data: ${name.value}")
            }
        }.addOnFailureListener { exception ->
            Log.e("Firestorm", "Error getting documents: ", exception)
        }

    }

}