package com.example.quizzy.presentation.landing

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LandingScreenViewModel : ViewModel(){
     fun logout(){
        Firebase.auth.signOut()
    }
}