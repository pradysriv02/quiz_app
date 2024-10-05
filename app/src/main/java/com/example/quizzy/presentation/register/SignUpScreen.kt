package com.example.quizzy.presentation.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.compose.greenish
import com.example.lockerroom.data.UIEvent
import com.example.quizzy.presentation.components.CheckBoxComponent
import com.example.quizzy.presentation.components.ClickableLoginRegisterTextComponent
import com.example.quizzy.presentation.components.DividerLines
import com.example.quizzy.presentation.components.HeadingTextComponent
import com.example.quizzy.presentation.components.MyTextComponent
import com.example.quizzy.presentation.components.NormalTextComponent
import com.example.quizzy.presentation.components.PasswordComponent
import com.example.quizzy.presentation.components.SignUpButton
import com.example.quizzy.presentation.nav_graph.Routes

//@Preview
//@Composable
//fun PrevSignUpScreen(){
//    SignUpScreen()
//}
@Composable
fun SignUpScreen(navController: NavController){
    val signUpViewModel = viewModel<SignUpViewModel>()
    val authState = signUpViewModel.authState.observeAsState()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if(signUpViewModel.signUpInProgress.value){
            CircularProgressIndicator()
        }
        else{
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(greenish)
                    .padding(30.dp)

            ) {
                Column(modifier = Modifier.background(greenish),
                    verticalArrangement = Arrangement.Center
                ) {
                    NormalTextComponent(value = "Hello,")
                    HeadingTextComponent(value = "Create Account")
                    Spacer(modifier = Modifier.height(18.dp))
                    MyTextComponent(labelValue = "First Name",
                        imageVector = Icons.Filled.Person,
                        onTextChanged = {
                            signUpViewModel.onEvent(UIEvent.FirstNameChanged(it))
                        },
                        errorStatus = signUpViewModel.registrationUIState.value.errorFirstName
                    )
                    MyTextComponent(labelValue = "Second Name",
                        imageVector = Icons.Filled.Person,
                        onTextChanged = {
                            signUpViewModel.onEvent(UIEvent.SecondNameChanged(it))
                        },
                        errorStatus = signUpViewModel.registrationUIState.value.errorSecondName)
                    MyTextComponent(labelValue = "Email",
                        imageVector = Icons.Filled.Mail,
                        onTextChanged = {
                            signUpViewModel.onEvent(UIEvent.EmailChanged(it))
                        },
                        errorStatus = signUpViewModel.registrationUIState.value.errorEmail)
                    PasswordComponent(labelValue = "Password",
                        imageVector = Icons.Filled.Lock,
                        onTextChanged = {
                            signUpViewModel.onEvent(UIEvent.PasswordChanged(it))
                        },
                        errorStatus = signUpViewModel.registrationUIState.value.errorPassword)
                    CheckBoxComponent(onTextSelected = {

                    })
                    Spacer(modifier = Modifier.height(30.dp))
                    SignUpButton("Sign Up", onButtonClicked = {
                        signUpViewModel.onEvent(UIEvent.RegisterButtonClicked)
                        when(authState.value){
                            is SignUpViewModel.AuthState.Authenticated -> {
                                navController.navigate(Routes.LandingScreen.route)
                            }
                            else -> {}
                        }
                    },
                        isEnabled = signUpViewModel.allValidationsPassed.value)
                    Spacer(modifier = Modifier.height(10.dp))
                    DividerLines()
                    Spacer(modifier = Modifier.height(15.dp))
                    ClickableLoginRegisterTextComponent(val1 = "Already have an account?", val2 = " Login", onTextSelected = {
                        navController.navigate(Routes.LoginScreen.route)
                    })
                }
            }

        }

    }
}


