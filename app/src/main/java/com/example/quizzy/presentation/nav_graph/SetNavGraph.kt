package com.example.quizzy.presentation.nav_graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quizzy.presentation.aibot.AIChatScreen
import com.example.quizzy.presentation.googlesignin.GoogleAuthUIClient
import com.example.quizzy.presentation.googlesignin.GoogleSignInViewModel
import com.example.quizzy.presentation.home.HomeScreen
import com.example.quizzy.presentation.home.HomeViewModel
import com.example.quizzy.presentation.landing.LandingScreen
import com.example.quizzy.presentation.loginpage.SignInScreen
import com.example.quizzy.presentation.quiz.QuizScreen
import com.example.quizzy.presentation.quiz.QuizViewModel
import com.example.quizzy.presentation.register.SignUpScreen
import com.example.quizzy.presentation.score.ScoreScreen


@Composable
fun SetNavGraph(navController: NavController.Companion,googleAuthUiClient : GoogleAuthUIClient) {
    val context = LocalContext.current
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.LoginScreen.route) {

        composable(route = Routes.HomeScreen.route){
            val viewModel : HomeViewModel = hiltViewModel()
            val state by viewModel.homeState.collectAsState()
            HomeScreen(
                state = state,
                event = viewModel::onEvent,
                navController = navController
            )
        }

        composable(
            route = Routes.QuizScreen.route,
            arguments = listOf(
                navArgument(ARG_KEY_QUIZ_NUMBER){type = NavType.IntType},
                navArgument(ARG_KEY_QUIZ_CATEGORY){type = NavType.StringType},
                navArgument(ARG_KEY_QUIZ_DIFFICULTY){type = NavType.StringType},
                navArgument(ARG_KEY_QUIZ_TYPE){type = NavType.StringType}

            )
        ){

            val numOfQuestions = it.arguments?.getInt(ARG_KEY_QUIZ_NUMBER)
            val category = it.arguments?.getString(ARG_KEY_QUIZ_CATEGORY)
            val difficulty = it.arguments?.getString(ARG_KEY_QUIZ_DIFFICULTY)
            val type = it.arguments?.getString(ARG_KEY_QUIZ_TYPE)


            val quizViewModel : QuizViewModel = hiltViewModel()
            val state by quizViewModel.quizList.collectAsState()
            QuizScreen(
                numOfQuiz = numOfQuestions!!,
                quizCategory = category!!,
                quizDifficulty = difficulty!!,
                navController=navController,
                quizType = type!!,
                event = quizViewModel::onEvent,
                state = state
                )
        }

        composable(
            route = Routes.LandingScreen.route
        ){
            LandingScreen(navController = navController)
        }

        composable(
            route = Routes.AIChatScreen.route
        ){
            AIChatScreen(navController=navController)
        }

        composable(
            route = Routes.ScoreScreen.route,
            arguments = listOf(
                navArgument(NOQ_KEY){ type = NavType.IntType},
                navArgument(CORRECT_ANS_KEY){ type = NavType.IntType}
            )
        ){
            val numOfQuestions = it.arguments?.getInt(NOQ_KEY)
            val correctAnswers = it.arguments?.getInt(CORRECT_ANS_KEY)
            ScoreScreen(numOfQuestions = numOfQuestions!!,
                numOfCorrectAnswers = correctAnswers!!,
                navController = navController
            )
        }
        composable(
            route = Routes.LoginScreen.route
        ){
            val googleSignInViewModel = GoogleSignInViewModel()

            SignInScreen(navController=navController,googleSignInViewModel,context)
        }

        composable(route = Routes.SignUpScreen.route){
            SignUpScreen(navController = navController)
        }
    }
}