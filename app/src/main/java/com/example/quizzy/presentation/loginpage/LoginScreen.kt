package com.example.quizzy.presentation.loginpage

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import com.example.lockerroom.data.SignInUIEvent
import com.example.lockerroom.data.SigninViewModel



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.compose.greenish
import com.example.quizzy.presentation.components.ClickableLoginRegisterTextComponent
import com.example.quizzy.presentation.components.DividerLines
import com.example.quizzy.presentation.components.ForgotPasswordText
import com.example.quizzy.presentation.components.HeadingTextComponent
import com.example.quizzy.presentation.components.MyTextComponent
import com.example.quizzy.presentation.components.NormalTextComponent
import com.example.quizzy.presentation.components.PasswordComponent
import com.example.quizzy.presentation.components.SignUpButton
import com.example.quizzy.presentation.googlesignin.GoogleSignInViewModel
import com.example.quizzy.presentation.nav_graph.Routes


@Composable
fun SignInScreen(
    navController: NavController,
    googleSignInViewModel: GoogleSignInViewModel,
    context: Context
){
    val signInViewModel = viewModel<SigninViewModel>()
    val authState = signInViewModel.authState.observeAsState()
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(greenish)
            .padding(30.dp),
        color = greenish
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            NormalTextComponent(value = "Hello,")
            HeadingTextComponent(value = "Welcome Back!")
            Spacer(modifier = Modifier.height(18.dp))
            MyTextComponent(labelValue = "Email",
                imageVector = Icons.Filled.Mail,
                onTextChanged = {
                    signInViewModel.onEvent(SignInUIEvent.EmailChanged(it))
                },
                errorStatus = signInViewModel.signInUIState.value.errorEmail
            )
            PasswordComponent(labelValue = "Password",
                imageVector = Icons.Filled.Lock,
                onTextChanged = {
                    signInViewModel.onEvent(SignInUIEvent.PasswordChanged(it))
                },
                errorStatus = signInViewModel.signInUIState.value.errorPassword)
            Spacer(modifier = Modifier.height(10.dp))
            ForgotPasswordText()
            Spacer(modifier = Modifier.height(30.dp))
            SignUpButton("Sign In", onButtonClicked = {

                signInViewModel.onEvent(SignInUIEvent.SignInButtonClicked)
                when(authState.value){
                    is SigninViewModel.AuthState.Authenticated -> {
                        navController.navigate(Routes.LandingScreen.route)
                    }
                    else -> {}
                }

            },
                isEnabled = signInViewModel.allValidationsPassed.value)
            Spacer(modifier = Modifier.height(10.dp))
            DividerLines()
            Spacer(modifier = Modifier.height(15.dp))
            ClickableLoginRegisterTextComponent(val1 = "Don't have an account yet?", val2 = " Register", onTextSelected = {
                navController.navigate(Routes.SignUpScreen.route)
            })
            Spacer(modifier = Modifier.height(20.dp))
        }

    }
}

//@Preview
//@Composable
//fun DefaultPreviewOfSignInScreen(){
//    SignInScreen()
//}