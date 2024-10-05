package com.example.quizzy.presentation.googlesignin

import android.content.Context
import android.widget.Toast
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.quizzy.R
import com.example.quizzy.presentation.nav_graph.Routes
import com.example.quizzy.presentation.register.UserSchema
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class GoogleSignInViewModel : ViewModel(){

    val user = MutableLiveData<GoogleSignInUser>(null)
    fun handleGoogleSignIn(context: Context,navController: NavController){
        viewModelScope.launch {
            googleSignIn(context).collect{result ->
                result.fold(
                    onSuccess = {authResult ->
                        val currentUser = authResult.user
                        if(currentUser!=null){
                            user.value = GoogleSignInUser(
                                currentUser.uid,
                                currentUser.displayName!!,
                                currentUser.email!!
                            )
                            Toast.makeText(
                                context,
                                "Account created Successfully",
                                Toast.LENGTH_LONG
                            )
                                .show()
                            navController.navigate(Routes.LandingScreen.route)
                        }
                    },
                    onFailure = {e ->
                        Toast.makeText(
                            context,
                            "Something went Wrong: ${e.message}",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                )

            }
        }
    }
    private suspend fun googleSignIn(context : Context) : Flow<Result<AuthResult>>{
        val fireBaseAuth = FirebaseAuth.getInstance()

        return callbackFlow {
            try {
                val credentialManager : CredentialManager = CredentialManager.create(context)

                val googleIdOption : GetGoogleIdOption = GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .setAutoSelectEnabled(true)
                    .build()

                val request: GetCredentialRequest = GetCredentialRequest.Builder()
                    .addCredentialOption(googleIdOption)
                    .build()

                val result = credentialManager.getCredential(context,request)
                val credential = result.credential

                if(credential is CustomCredential && credential.type==GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL){
                    val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                    val authCredential = GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken,null)
                    val authResult = fireBaseAuth.signInWithCredential(authCredential).await()
                    trySend(Result.success(authResult))
                }
                else{
                    throw RuntimeException("Invalid credentials")
                }
            }catch (e:GetCredentialCancellationException){
                trySend(Result.failure(Exception("Sign-in aborted")))
            }catch (e:Exception){
                trySend(Result.failure(e))
            }
            awaitClose {  }
        }
    }
}