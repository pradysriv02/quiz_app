package com.example.quizzy.presentation.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Help
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.compose.darkGreenish
import com.example.compose.grayValour
import com.example.compose.greenish
import com.example.compose.newGray
import com.example.compose.onBackgroundLight
import com.example.compose.primaryLight
import com.example.compose.surfaceLight
import com.example.quizzy.presentation.home.component.QuizButtonGenerate
import com.example.quizzy.presentation.home.component.QuizScreenDropDownMenu
import com.example.quizzy.presentation.nav_graph.Routes
import com.example.quizzy.presentation.util.Constants
import com.google.rpc.Help




@Composable
fun HomeScreen(
    state: HomeScreenState,
    event: (HomeScreenEvent) -> Unit,
    navController: NavController
){
    Scaffold(
        topBar = {
            Box(modifier =
            Modifier
                .fillMaxWidth()
                .background(
                    color = greenish
                )
                .height(150.dp)
                .padding(30.dp))
            {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(modifier = Modifier
                        .clickable {
                            navController.navigate(route = Routes.LandingScreen.route)
                        },
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "Back",
                        tint = darkGreenish)
                    Text(
                        text = "Quiz",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.W600,
                        color = darkGreenish
                    )
                    Icon(imageVector = Icons.Outlined.Help,
                        contentDescription = "Help",
                        tint = darkGreenish)
                }

            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
                .background(color = greenish)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            QuizScreenDropDownMenu(menuName = "Number of Questions", menuList = Constants.numbersAsString, text = state.numberOfQuestions.toString(), onDropDownClick = {event(HomeScreenEvent.SetNumberOfQuestions(it.toInt()))})

            Spacer(modifier = Modifier.height(30.dp))
            QuizScreenDropDownMenu(menuName = "Select Category", menuList = Constants.categories, text = state.quizCategory, onDropDownClick = {event(HomeScreenEvent.SetSelectCategory(it))})

            Spacer(modifier = Modifier.height(30.dp))
            QuizScreenDropDownMenu(menuName = "Select Difficulty", menuList = Constants.difficulty, text = state.difficulty, onDropDownClick = {event(HomeScreenEvent.SetDifficulty(it))})

            Spacer(modifier = Modifier.height(30.dp))
            QuizScreenDropDownMenu(menuName = "Select Type", menuList = Constants.type, text = state.type, onDropDownClick = {event(HomeScreenEvent.SetSelectType(it))})

            Spacer(modifier = Modifier.height(25.dp))

            QuizButtonGenerate(text = "Generate Quiz", padding = 10.dp){
                navController.navigate(route = Routes.QuizScreen.passQuizParams(state.numberOfQuestions,state.quizCategory,state.difficulty,state.type))
            }
        }
    }
}



