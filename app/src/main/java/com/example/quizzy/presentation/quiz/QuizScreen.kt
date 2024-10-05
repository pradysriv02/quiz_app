package com.example.quizzy.presentation.quiz

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Help
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.darkGreenish
import com.example.compose.greenish
import com.example.compose.newGray
import com.example.compose.surfaceLight
import com.example.quizzy.presentation.nav_graph.Routes
import com.example.quizzy.presentation.quiz.component.QuizButtonBox
import com.example.quizzy.presentation.quiz.component.QuizInterface
import com.example.quizzy.presentation.quiz.component.ShimmerEffect
import com.example.quizzy.presentation.util.Constants
import kotlinx.coroutines.launch


//@Preview
//@Composable
//fun PrevQuizScreen(){
//    QuizScreen(
//        numOfQuiz = 12,
//        quizCategory = "GK",
//        quizDifficulty = "Easy",
//    )
//}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizScreen(
    numOfQuiz:Int,
    quizCategory:String,
    quizDifficulty:String,
    navController: NavController,
    quizType:String,
    event:(QuizScreenEvent) -> Unit,
    state: QuizScreenState
){
    LaunchedEffect(key1 = Unit) {

        val difficulty = when(quizDifficulty){
            "Medium" -> "medium"
            "Hard" -> "hard"
            else -> "easy"
        }
        val type = when(quizType){
            "Multiple Choice" -> "multiple"
            else -> "boolean"
        }
        event(QuizScreenEvent.GetQuizzes(numOfQuiz,Constants.categoriesMap[quizCategory]!!,difficulty,type))
    }
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
                            navController.navigate(route = Routes.HomeScreen.route)
                        },
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "Back",
                        tint = darkGreenish)
                    Text(
                        text = quizCategory,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600,
                        color = darkGreenish,
                        overflow = TextOverflow.Ellipsis
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
                .background(greenish)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Questions: $numOfQuiz",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W600,
                    color = darkGreenish,
                )
                Text(
                    text = "Difficulty: $quizDifficulty",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W600,
                    color = darkGreenish,
                )
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                color = darkGreenish,
                thickness = 1.dp
            )

            Spacer(modifier = Modifier.height(25.dp))
            if(quizFetched(state)){
                val pagerState = rememberPagerState() {state.quizState.size}
                HorizontalPager(state = pagerState) {index ->
                    QuizInterface(
                        onOptionSelected = {selectedIndex ->
                            event(QuizScreenEvent.SetOptionSelected(index,selectedIndex))
                        },
                        qNumber = index+1,
                        quizState = state.quizState[index] ,
                        modifier = Modifier.weight(1f)
                    )
                }

                val buttonText by remember {
                    derivedStateOf {
                        when(pagerState.currentPage){
                            0 -> {
                                listOf("","Next")
                            }
                            state.quizState.size-1 -> {
                                listOf("Previous","Submit")
                           }
                            else -> {
                                listOf("Previous","Next")
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                        .navigationBarsPadding(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    val scope = rememberCoroutineScope()
                    if(buttonText[0].isNotEmpty()){
                        QuizButtonBox(text = "Prev", padding = 10.dp) {
                            scope.launch { pagerState.animateScrollToPage(pagerState.currentPage-1) }
                        }
                    }
                    QuizButtonBox(text = buttonText[1], padding = 10.dp) {
                        if(pagerState.currentPage == state.quizState.size-1){
                                navController.navigate(Routes.ScoreScreen.passNumOfQuestionsAndCorrectAns(state.quizState.size,state.score))

                        }
                        else{
                            scope.launch { pagerState.animateScrollToPage(pagerState.currentPage+1) }
                        }
                    }

                }
            }
            
        }
    }
}

@Composable
fun quizFetched(state: QuizScreenState): Boolean {

    return when{
        state.isLoading -> {
            ShimmerEffect()
            false
        }
        state.quizState?.isNotEmpty() == true -> {
            true
        }
        else -> {
            Text(text = state.error.toString(), color = darkGreenish)
            false
        }
    }
}
