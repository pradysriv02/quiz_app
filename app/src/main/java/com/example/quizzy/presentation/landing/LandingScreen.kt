package com.example.quizzy.presentation.landing

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Computer
import androidx.compose.material.icons.outlined.Flight
import androidx.compose.material.icons.outlined.Science
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.compose.bluish
import com.example.compose.cardLandingColor
import com.example.compose.darkBluish
import com.example.compose.darkGreenish
import com.example.compose.errorContainerDarkMediumContrast
import com.example.compose.golden
import com.example.compose.greenish
import com.example.compose.newGray
import com.example.compose.onSurfaceVariantDarkHighContrast
import com.example.compose.orange
import com.example.compose.placeColor
import com.example.compose.placeLandingColor
import com.example.compose.surfaceLight
import com.example.lockerroom.data.SigninViewModel
import com.example.quizzy.presentation.components.SignUpButton
import com.example.quizzy.presentation.landing.components.DrawerItems
import com.example.quizzy.presentation.landing.components.HomeToolBar
import com.example.quizzy.presentation.landing.components.NavigationDrawerHeader
import com.example.quizzy.presentation.landing.components.NavigationDrawerOneItem
import com.example.quizzy.presentation.landing.components.NewQuizButton
import com.example.quizzy.presentation.landing.components.Placeholder
import com.example.quizzy.presentation.nav_graph.Routes
import com.example.quizzy.presentation.register.SignUpViewModel
import com.example.quizzy.ui.theme.fontFamily
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import com.example.quizzy.R
import com.example.quizzy.presentation.googlesignin.GoogleSignInViewModel
import com.google.firebase.database.DatabaseReference

@Composable
fun LandingScreen(
     navController: NavController,
     ){
        val landingScreenViewModel = viewModel<LandingScreenViewModel>()
        val googleSignInViewModel = viewModel<GoogleSignInViewModel>()
        val user by googleSignInViewModel.user.observeAsState()
        Scaffold(
            topBar = { HomeToolBar(toolBarTitle = "Quiz", logoutButtonCLicked =
            {
                //landingScreenViewModel.logout()
                navController.navigate(Routes.LoginScreen.route)
            },
                postButtonClicked = {

                },
                aiChatButtonClicked = {
                     navController.navigate(Routes.AIChatScreen.route)
                }
            ) }
        ) {
                PaddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = PaddingValues.calculateTopPadding())
                    .background(greenish)
            ) {

                Column(
                    modifier = Modifier
                        .padding(20.dp)
                ) {
                    Text(text = "Hello,", color = Color.Black, fontSize = 20.sp)
                    user?.name?.let { Text(text = it, color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold) }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "What Subjects do you", color = darkGreenish, fontSize = 30.sp, fontWeight = FontWeight.Bold, fontFamily = fontFamily)
                    Text(text = "want to improve today ?", color = darkGreenish, fontSize = 30.sp, fontWeight = FontWeight.Bold, fontFamily = fontFamily)
                    Spacer(modifier = Modifier.height(15.dp))
                    Placeholder()
                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        modifier = Modifier
                            .padding(10.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.
                            fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                modifier = Modifier
                                    .height(170.dp)
                                    .width(150.dp)
                                    .clip(RoundedCornerShape(20.dp))
                                    .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
                                    .background(placeLandingColor)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth()
                                        ,
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(imageVector = Icons.Outlined.Computer, contentDescription = "gk", modifier = Modifier.size(height = 60.dp, width = 60.dp))
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(text = "Computer")
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .height(170.dp)
                                    .width(150.dp)
                                    .clip(RoundedCornerShape(20.dp))
                                    .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
                                    .background(placeLandingColor)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth()
                                    ,
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(imageVector = Icons.Outlined.Flight, contentDescription = "gk", modifier = Modifier.size(height = 60.dp, width = 60.dp))
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(text = "General Studies")
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                        Row(
                            modifier = Modifier.
                            fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                modifier = Modifier
                                    .height(170.dp)
                                    .width(150.dp)
                                    .clip(RoundedCornerShape(20.dp))
                                    .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
                                    .background(placeLandingColor)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth()
                                    ,
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(imageVector = Icons.Outlined.Add, contentDescription = "gk", modifier = Modifier.size(height = 60.dp, width = 60.dp))
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(text = "Mathematics")
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .height(170.dp)
                                    .width(150.dp)
                                    .clip(RoundedCornerShape(20.dp))
                                    .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
                                    .background(placeLandingColor)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth()
                                    ,
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(imageVector = Icons.Outlined.Science, contentDescription = "gk", modifier = Modifier.size(height = 60.dp, width = 60.dp))
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(text = "Science")
                                }
                            }
                        }
                    }
                }
                NewQuizButton(text = "New Quiz", padding = 10.dp, onButtonClicked = {
                    navController.navigate(Routes.HomeScreen.route)
                })
            }
        }
    }


//@Preview
//@Composable
//fun prevLandingScreen(){
//    LandingScreen()
//}