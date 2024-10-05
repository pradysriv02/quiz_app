package com.example.quizzy.presentation.nav_graph

const val ARG_KEY_QUIZ_NUMBER = "ak_quiz_number"
const val ARG_KEY_QUIZ_CATEGORY = "ak_quiz_category"
const val ARG_KEY_QUIZ_DIFFICULTY= "ak_quiz_difficulty"
const val ARG_KEY_QUIZ_TYPE = "ak_quiz_type"
const val NOQ_KEY = "noq_key"
const val CORRECT_ANS_KEY = "correct_ans_key"
sealed class Routes(val route:String){

//    object SignUpScreen : Screen(screen = "sign_up_screen")
//    object SignInScreen : Screen(screen = "sign_in_screen")
//    object TermsAndConditionsScreen : Screen(screen = "tnc_screen")
//    object AIChatScreen : Screen(screen = "ai_chat_screen")
//    object QuizHomeScreen : Screen(screen = "quiz_home_screen")
    object SignUpScreen : Routes(route = "signup_screen")
    object LoginScreen : Routes(route = "login_screen")
    object AIChatScreen : Routes(route = "ai_screen")
    object LandingScreen : Routes(route = "landing_screen")
    object HomeScreen : Routes(route = "home_screen")
    object QuizScreen : Routes(route = "quiz_screen/{$ARG_KEY_QUIZ_NUMBER}/{$ARG_KEY_QUIZ_CATEGORY}/{$ARG_KEY_QUIZ_DIFFICULTY}/{$ARG_KEY_QUIZ_TYPE}"){
        fun passQuizParams(numOfQuizzes:Int, category:String, difficulty:String, type:String) : String{
            return "quiz_screen/{$ARG_KEY_QUIZ_NUMBER}/{$ARG_KEY_QUIZ_CATEGORY}/{$ARG_KEY_QUIZ_DIFFICULTY}/{$ARG_KEY_QUIZ_TYPE}"
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_NUMBER}",
                    newValue = numOfQuizzes.toString()
                )
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_CATEGORY}",
                    newValue = category.toString()
                )
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_DIFFICULTY}",
                    newValue = difficulty.toString()
                )
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_TYPE}",
                    newValue = type.toString()
                )
        }
    }
    object ScoreScreen : Routes(route = "score_screen/{$NOQ_KEY}/{$CORRECT_ANS_KEY}"){
        fun passNumOfQuestionsAndCorrectAns(questions:Int,correctAnswers:Int) : String{
            return "score_screen/{$NOQ_KEY}/{$CORRECT_ANS_KEY}"
                .replace("{$NOQ_KEY}",questions.toString())
                .replace("{$CORRECT_ANS_KEY}",correctAnswers.toString())
        }
    }
}